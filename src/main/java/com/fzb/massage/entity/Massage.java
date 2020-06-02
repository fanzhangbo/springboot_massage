package com.fzb.massage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */

@Data
public class Massage {

    private Long id;
    private String name;
    private String img;
    private String description;
    private Integer duration;
    private BigDecimal price;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


}
