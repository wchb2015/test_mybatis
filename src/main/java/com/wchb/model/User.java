package com.wchb.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
public class User {

    //属性名和数据库表的字段对应
    private int id;
    private String username;
    private String sex;
    private Date birthday;
    private String address;

}
