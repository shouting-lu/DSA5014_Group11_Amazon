package com.example.searchweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.searchweb.entity.Alsobuy;
import com.example.searchweb.entity.Products;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AlsobuyMapper extends BaseMapper<Alsobuy> {


    List<Products> findProductsByAsinList(@Param("asin1") String asin1, @Param("asin2") String asin2, @Param("asin3") String asin3);


}
