package com.fzb.massage.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Data
public class RolePermission implements Serializable {
    private Long id;
    private Long roleId;
    private Long permissionId;
}
