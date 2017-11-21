package com.wchb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class QueryVo {

    private List<Integer> ids;//传入多个id

    private String name;

    private String sex;

}
