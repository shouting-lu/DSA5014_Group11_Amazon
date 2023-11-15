package com.example.searchweb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.searchweb.entity.Review;
import com.example.searchweb.mapper.ReviewMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ReviewService extends ServiceImpl<ReviewMapper, Review> {

    public List<Review> getReviewsByAsin(@RequestParam String asin) {
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("asin", asin);
        queryWrapper.last("LIMIT 20"); // Limit the result to 20 rows

        return list(queryWrapper);
    }

}
