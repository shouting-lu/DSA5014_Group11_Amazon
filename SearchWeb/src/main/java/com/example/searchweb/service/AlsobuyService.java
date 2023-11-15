package com.example.searchweb.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.searchweb.entity.Alsobuy;
import com.example.searchweb.entity.Products;
import com.example.searchweb.mapper.AlsobuyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlsobuyService extends ServiceImpl<AlsobuyMapper, Alsobuy> {

    @Autowired
    private AlsobuyMapper alsobuyMapper;

    public List<Products> findProductsByAsinList(String asin1, String asin2, String asin3) {
        return alsobuyMapper.findProductsByAsinList(asin1, asin2, asin3);
    }
}

