package com.lvmama.model;

import java.util.List;

/**
 * Created by wangchongbei on 15-12-30.
 */
public class QueryVo {

    private List<Integer> ids;//传入多个id

    private String name;
    private String sex;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
