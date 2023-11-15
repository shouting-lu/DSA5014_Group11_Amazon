package com.example.searchweb.controller;

import com.example.searchweb.entity.Products;
import com.example.searchweb.service.AlsobuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alsobuy")
public class AlsobuyController {

    @Autowired
    private AlsobuyService alsobuyService;

    @GetMapping("/products")
    public List<Products> findProductsByAsinList(
            @RequestParam(value = "asin1", required = false) String asin1,
            @RequestParam(value = "asin2", required = false) String asin2,
            @RequestParam(value = "asin3", required = false) String asin3
    ) {
        List<Products> products = alsobuyService.findProductsByAsinList(asin1, asin2, asin3);

        for (Products product : products) {
            String imageJSON = product.getImage();
            if (imageJSON != null && !imageJSON.isEmpty()) {
                // 使用字符串分割方法提取第一个URL
                String[] imageArray = imageJSON.split(",");
                if (imageArray.length > 0) {
                    String firstImageURL = imageArray[0].trim();
                    // 构建新的JSON属性
                    firstImageURL = firstImageURL.replace("['", "").replace("'", "");
                    String newImageJSON = firstImageURL;
                    // 更新产品的image属性
                    product.setImage(newImageJSON);
                }
            }
        }

        return products;
    }

}
