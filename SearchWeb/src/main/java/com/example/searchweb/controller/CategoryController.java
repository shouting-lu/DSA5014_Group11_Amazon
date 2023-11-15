package com.example.searchweb.controller;

import com.example.searchweb.entity.Products;
import com.example.searchweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/ctsearch")
    public List<Products> ctsearchProducts(@RequestParam String keyword){
        List<Products> res = categoryService.getProductsByCategoryKeyword(keyword);

        for (Products product : res) {
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

        return res;
    }
}
