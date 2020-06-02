package com.fzb.massage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Data
public class MassageOrder {

    private Long id;
    private Long userId;
    private Long workerId;
    private Long massageId;
    private Integer status;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date orderTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date editTime;

    // 不在数据库中的字段
    private String username;
    private String workerName;
    private String massageName;
    private String massageImg;



}
