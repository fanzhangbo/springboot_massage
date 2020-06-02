package com.fzb.massage.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.fzb.massage.service.ShiroService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private ShiroService shiroService;


    // ShiroFilterFactoryBean

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 添加shiro内置过滤器

        /*
            anon: 无需认证 就可访问
            authc:  必须认证才能访问
            user: 必须拥有 记住我 功能才能用
            perms: 拥有对某个资源的权限才能访问
            roles: 拥有对某个角色的权限才能访问
         */



        // 授权
        shiroFilterFactoryBean.setFilterChainDefinitionMap(
                this.shiroService.loadFilterChainDefinitions());

        shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置未授权跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");



        return shiroFilterFactoryBean;
    }

    // DefaultWebSecurityManager

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    // 自定义Realm
    @Bean
    public UserRealm userRealm(@Qualifier("credentialsMatcher") CredentialsMatcher credentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }


    /**
     * 密码加密 验证
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }





    // 配置thymeleaf shiro
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
