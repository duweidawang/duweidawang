package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 店铺名
     */
    private String name;

    /**
     * 店铺地址
     */
    private String address;

    /**
     * 封面缩略图
     */
    private String thumbnail;

    /**
     * 店铺头像
     */
    private String avatarUrl;

    /**
     * 店铺简介
     */
    private String summary;

    /**
     * 店铺描述
     */
    private String description;

    /**
     * 评分
     */
    private String star;

    /**
     * 状态（0-营业 1-休息)
     */
    private Integer status;


}

