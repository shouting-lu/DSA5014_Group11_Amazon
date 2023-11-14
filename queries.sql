/*1. Query products according to the entered description, sort them in descending order by rating and take the top three.*/
SELECT *
FROM (
	SELECT *
	FROM product p
	WHERE description LIKE '%leather%'
	ORDER BY rate DESC
	LIMIT 3
) AS descrip_asin
LEFT JOIN product_image ON descrip_asin.asin = product_image.asin;


/* 2. Query based on the entered category keywords, sort in descending order based on ratings, and take the top three */
SELECT *
FROM (
	SELECT p.*
	FROM product p
	JOIN category c ON p.asin = c.asin
	WHERE c.cname LIKE '%leather%'
	ORDER BY p.rate DESC
	LIMIT 3
) AS cate_asin
LEFT JOIN product_image ON cate_asin.asin = product_image.asin;

/* 3. Query review content based on asin value.*/
SELECT * FROM review
LEFT JOIN review_image ON review.reviewID = review_image.reviewID
WHERE asin = '0204444403';

/*4. Query the products in also\_buy based on the three queried asin values, sort according to the product's score, and take the top six */
SELECT sub_query.*, pi.each_image
FROM 
(
	SELECT p2.*
	FROM product p2
	WHERE p2.asin IN (
		SELECT ab.asinB
		FROM alsobuy ab
		WHERE ab.asin IN ('B01DLVYKDC', 'B00OO05ZHK', 'B01HHLHG88')
	)
	ORDER BY p2.rate DESC
	LIMIT 6;

/*5. Find all the transactions given the customer's ID.*/

SELECT *
FROM transaction
JOIN TransAsin ON transaction.transID = TransAsin.transID
WHERE customerID = '00000000000008';


/*6. Find the most bought product of a customer given the ID.*/
SELECT asin, total_quantity
FROM (
	SELECT asin, 
		   SUM(quantity) as total_quantity,
		   DENSE_RANK() OVER (ORDER BY SUM(quantity) DESC) as entry_rank
	FROM transaction
	JOIN TransAsin ON transaction.transID = TransAsin.transID
	WHERE customerID = '00000000000008'
	GROUP BY asin
) as rank_query
WHERE entry_rank = 1;

/*7. Identify the brands that a user frequently purchases base on the historical transaction data. For each of these brands, recommends the top rate products to the user.*/
SELECT brand, asin, title, rate
FROM (
	SELECT 
		p.brand, p.asin, p.title, p.rate,
		ROW_NUMBER() OVER (PARTITION BY p.brand ORDER BY p.rate DESC) as product_rank
	FROM
		product p
	WHERE
		p.brand IN (
			SELECT brand
			FROM product p
			JOIN (
				SELECT asin
				FROM transaction
				JOIN TransAsin ON transaction.transID = TransAsin.transID
				WHERE customerID = '00000000000008'
			) as histories ON p.asin = histories.asin
			WHERE brand <> ''
		)
) as ranked_products
WHERE product_rank = 1
LIMIT 10;