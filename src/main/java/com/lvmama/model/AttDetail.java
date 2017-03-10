package com.lvmama.model;

import java.io.Serializable;
import java.util.Date;

public class AttDetail implements Serializable {
    private static final long serialVersionUID = 8671770986067328177L;

    private long id;
    private String name;
    private Date startTime;
    private Date endTime;
    private Date tempTime;
    private long minute;
    private int month;
    /**
     * 用户编号
     */
    private String userNo;
    private long cardNo;
    private long doorNo;
    private String doorName;
    private Date createTime;
    private Date updateTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getTempTime() {
        return tempTime;
    }

    public void setTempTime(Date tempTime) {
        this.tempTime = tempTime;
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public long getCardNo() {
        return cardNo;
    }

    public void setCardNo(long cardNo) {
        this.cardNo = cardNo;
    }

    public long getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(long doorNo) {
        this.doorNo = doorNo;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AttDetail{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", minute=" + minute +
                ", month=" + month +
                ", userNo='" + userNo + '\'' +
                ", cardNo=" + cardNo +
                ", doorNo=" + doorNo +
                ", doorName='" + doorName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
