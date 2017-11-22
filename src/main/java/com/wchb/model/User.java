package com.wchb.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    //属性名和数据库表的字段对应
    private int id;
    private String name;
    private String mobile;
    private String idNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
