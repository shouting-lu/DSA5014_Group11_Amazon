package com.example.searchweb.controller;


import com.example.searchweb.entity.Products;
import com.example.searchweb.entity.Review;
import com.example.searchweb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ReviewController {
    @Autowired ReviewService reviewService;

    @GetMapping("/getreview")
    public List<Review> dessearchProducts(@RequestParam String asin){
        List<Review> res = reviewService.getReviewsByAsin(asin);

        for (Review review : res) {
            String imageJSON = review.getImage();
            if (imageJSON != null && !imageJSON.isEmpty()) {
                // 使用字符串分割方法提取第一个URL
                String[] imageArray = imageJSON.split(",");
                if (imageArray.length > 0) {
                    String firstImageURL = imageArray[0].trim();
                    // 构建新的JSON属性
                    firstImageURL = firstImageURL.replace("['", "").replace("'", "");
                    String newImageJSON = firstImageURL;
                    // 更新产品的image属性
                    review.setImage(newImageJSON);
                }
            }
        }

        return res;
    }
}
