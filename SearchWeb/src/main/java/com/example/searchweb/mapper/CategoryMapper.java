package com.example.searchweb.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.searchweb.entity.Category;
import com.example.searchweb.entity.Products;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("SELECT p.* FROM category c, product p WHERE c.asin = p.asin AND c.cname LIKE CONCAT('%',#{keyword},'%') ORDER BY p.rate DESC LIMIT 3")
    List<Products> getProductsByCategoryKeyword(@Param("keyword") String keyword);
}
