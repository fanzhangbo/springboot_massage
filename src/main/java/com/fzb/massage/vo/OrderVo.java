package com.fzb.massage.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzb.massage.entity.Massage;
import com.fzb.massage.entity.User;
import com.fzb.massage.entity.Worker;

import java.util.Date;

/**
 * TODO
 * 预约表
 * @author Fan Zhangbo
 */
public class OrderVo {

    private Long id;
    private User user;
    private Worker worker;
    private Massage massage;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date editTime;

    public OrderVo(Long id, User user, Worker worker, Massage massage, Date editTime) {
        this.id = id;
        this.user = user;
        this.worker = worker;
        this.massage = massage;
        this.editTime = editTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Massage getMassage() {
        return massage;
    }

    public void setMassage(Massage massage) {
        this.massage = massage;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "id=" + id +
                ", user=" + user +
                ", worker=" + worker +
                ", massage=" + massage +
                ", editTime=" + editTime +
                '}';
    }
}
