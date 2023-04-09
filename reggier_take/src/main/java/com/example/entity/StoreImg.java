package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 店铺图片(StoreImg)实体类
 *
 * @author makejava
 * @since 2023-04-09 20:44:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreImg implements Serializable {



    private Long id;

    /**
     * 店铺id
     */
    private Long storeId;

    /**
     * 图片地址
     */
    private String imgUrl;
}

