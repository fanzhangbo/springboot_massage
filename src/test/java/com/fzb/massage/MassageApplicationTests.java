package com.fzb.massage;

import com.fzb.massage.entity.Admin;
import com.fzb.massage.entity.Permission;
import com.fzb.massage.mapper.AdminMapper;
import com.fzb.massage.service.PermissionService;
import com.fzb.massage.utils.encrypt.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class MassageApplicationTests {

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private PermissionService permissionService;

	@Test
	void contextLoads() {
		String encrypt = PasswordUtil.encrypt("123");
		System.out.println(encrypt);
	}

	@Test
	void listPermission() {
		List<Permission> permissions = permissionService.listPermissionByAdminId(1L);
		System.out.println(permissions);
	}

}
