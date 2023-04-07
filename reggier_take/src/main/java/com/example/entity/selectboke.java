package com.example.entity;


import lombok.Data;

import java.util.List;

@Data
public class selectboke {
    private Integer userid;
    private String username;
    private String time;
    private String content;
    private Integer urlid;
    private  String headurl;
    private List<String> urllist;
    private String returncontent;
    private String returntime;
    private String img;
    private int id;
}
