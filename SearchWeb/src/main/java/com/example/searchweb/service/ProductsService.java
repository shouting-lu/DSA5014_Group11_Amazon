package com.example.searchweb.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.searchweb.entity.Products;
import com.example.searchweb.mapper.ProductsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService extends ServiceImpl<ProductsMapper, Products> {

    public List<Products> searchProductsByDescription(String keyword) {
        LambdaQueryWrapper<Products> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Products::getDescription, keyword);
        queryWrapper.orderByDesc(Products::getRate);
        queryWrapper.last("LIMIT 3");
        return list(queryWrapper);
    }

}
