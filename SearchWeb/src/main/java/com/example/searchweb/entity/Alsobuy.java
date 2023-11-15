package com.example.searchweb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "alsobuy")
public class Alsobuy {
    private String asin;
    private String asinB;
}
