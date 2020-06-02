package com.fzb.massage.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Data
public class AdminRole implements Serializable {
    private Long id;
    private Long adminId;
    private Long roleId;
}
