package com.example.searchweb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "category")
public class Category {
    @TableField(value = "cID")
    private String cID;
    private String asin;
    private String cname;
}
