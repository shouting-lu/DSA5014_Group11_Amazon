package com.example.searchweb.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "product")
public class Products {
    private String asin;
    private String brand;
    private String title;
    private String price;
    @TableField(value = "p_rank")
    private String pRank;
    private BigDecimal rate;
    private String description;
    private String image;
}
