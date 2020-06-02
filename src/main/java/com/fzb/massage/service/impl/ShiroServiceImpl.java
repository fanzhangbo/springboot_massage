package com.fzb.massage.service.impl;

import com.fzb.massage.entity.Permission;
import com.fzb.massage.service.PermissionService;
import com.fzb.massage.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private PermissionService permissionService;


    @Override
    public Map<String, String> loadFilterChainDefinitions() {

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/doLogin", "anon");
        filterChainDefinitionMap.put("/getVerify", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/uploads/**", "anon");

        List<Permission> permissions = this.permissionService.listPermsAndUrl();


        for (Permission permission : permissions) {
            if (!StringUtils.isEmpty(permission.getPerms())
                    && !StringUtils.isEmpty(permission.getUrl())) {
                filterChainDefinitionMap.put(permission.getUrl(),
                        "perms[" + permission.getPerms() + "]");
            }
        }


        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }
}
