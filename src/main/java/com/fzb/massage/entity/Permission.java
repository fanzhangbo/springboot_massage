package com.fzb.massage.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Data
public class Permission implements Serializable {

    private Long permissionId;
    private String permissionName;
    private Long pid;
    private String perms;
    private String url;
    private List<Permission> child = new LinkedList<Permission>();



}
