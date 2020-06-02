package com.fzb.massage.mapper;

import com.fzb.massage.entity.AdminRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Mapper
public interface AdminRoleMapper {

    public void removeByAdminId(Long adminId);

    public void insertList(List<AdminRole> adminRoles);

    public List<AdminRole> listAdminRoleByAdminId(Long adminId);
}
