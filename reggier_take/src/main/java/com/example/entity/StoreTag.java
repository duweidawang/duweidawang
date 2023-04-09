package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreTag implements Serializable{

    /**
     * 标签id
     */
private Long tagId;
    /**
     * 店铺id
     */
private Long storeId;
}

