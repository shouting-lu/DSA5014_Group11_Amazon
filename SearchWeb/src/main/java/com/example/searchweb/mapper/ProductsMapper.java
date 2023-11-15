package com.example.searchweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.searchweb.entity.Products;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductsMapper extends BaseMapper<Products> {


}
