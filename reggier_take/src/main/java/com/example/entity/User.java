package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String account;
    private String password;
    private String email;
    private String roleid;
    private String img;
    private String username;
    private String address;
    private String photonumber;


}
