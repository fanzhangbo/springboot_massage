package com.fzb.massage.mapper;

import com.fzb.massage.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Mapper
public interface RolePermissionMapper {

    public void removeByRoleId(Long adminId);

    public void insertList(List<RolePermission> adminRoles);

    List<RolePermission> listRolePermissionByRoleId(Long roldId);
}
