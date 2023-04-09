package com.example.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author zn
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVO {

    private Long id;


    private String name;


    private String address;


    private String thumbnail;


    private String avatarUrl;


    private String summary;


    private String description;


    private String star;


    private Integer status;

    private List<TagVO> tagList;

    private List<String> imgUrlList;
}
