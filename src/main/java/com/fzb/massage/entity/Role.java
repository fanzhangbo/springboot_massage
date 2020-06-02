package com.fzb.massage.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Data
public class Role implements Serializable {

    private Long roleId;
    private String roleName;
    private String roleCode;



}
