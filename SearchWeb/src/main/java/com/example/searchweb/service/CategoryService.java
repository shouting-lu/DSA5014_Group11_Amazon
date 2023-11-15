package com.example.searchweb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.searchweb.entity.Category;
import com.example.searchweb.entity.Products;
import com.example.searchweb.mapper.AlsobuyMapper;
import com.example.searchweb.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Products> getProductsByCategoryKeyword(String keyword) {
        return categoryMapper.getProductsByCategoryKeyword(keyword);
    }
}



