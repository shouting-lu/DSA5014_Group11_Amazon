package com.example.searchweb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName(value="review")
public class Review {
    @TableField(value = "reviewID")
    private Integer reviewID;
    private String asin;
    @TableField(value = "customerID")
    private String customerID;
    private Integer overall;
    private BigDecimal vote;
    @TableField(value = "reviewTime")
    private LocalDateTime reviewTime;
    private String style;
    private String summary;
    @TableField(value = "reviewTime")
    private String reviewText;
    private String image;
}
