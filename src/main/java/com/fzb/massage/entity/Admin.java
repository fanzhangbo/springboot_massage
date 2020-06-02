package com.fzb.massage.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Data
public class Admin implements Serializable {
    private Long adminId;
    private String name;
    private String password;
    private String perms;
    private List<Role> roles;
}
