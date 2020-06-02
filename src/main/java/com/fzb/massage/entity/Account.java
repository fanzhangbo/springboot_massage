package com.fzb.massage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public class Account {

    private Long id;
    private Long userId;
    private Integer type;
    private BigDecimal money;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    private String remark;

    public Account() {
    }

    public Account(Long id, Long userId, Integer type, BigDecimal money, Date createTime, String remark) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.money = money;
        this.createTime = createTime;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", money=" + money +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
