package com.wchb.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    //属性名和数据库表的字段对应
    private int id;
    private String username;
    private String sex;
    private Date birthday;
    private String address;

}
