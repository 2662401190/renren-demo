package com.demo2.demo;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author 贺威
 * @create 2019-04-12 15:59
 * 核心配置
 */
@Configuration
public class ShiroConfigration {


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager){

        ShiroFilterFactoryBean filterFactoryBean=new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        /// 定义 登录的 URL
        filterFactoryBean.setLoginUrl("/login");
        //  登录成功的跳转的页面
        filterFactoryBean.setSuccessUrl("/index");
        //  权限不通过返回的url
        filterFactoryBean.setUnauthorizedUrl("/unauthorized");
        //  key 是访问的请求  value 是使用什么样的拦截器
        LinkedHashMap<String,String> filterChainDefinitonMap=new LinkedHashMap<>();
        filterChainDefinitonMap.put("/index", "authc");
        filterChainDefinitonMap.put("/login", "anon");
        //  当前 请求不被拦截
        filterChainDefinitonMap.put("/loginUser", "anon");
        //  只有拿到edit 权限的人  才能访问
       filterChainDefinitonMap.put("/edit", "perms[edit]");
        // 设置什么角色访问什么路劲
       filterChainDefinitonMap.put("/admin", "roles[admin]");
        filterChainDefinitonMap.put("/**", "user");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitonMap);
        return filterFactoryBean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return  manager;
    }

    @Bean("authRealm")
    public AuthRealm  authRealm(@Qualifier("credentialMatcher") CredentialMatcher credentialMatcher){
        AuthRealm authRealm=new AuthRealm();
        authRealm.setCredentialsMatcher(credentialMatcher);
        return  authRealm;
    }
    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        return  new CredentialMatcher();
    }

    /**
     *  spring  与 shiro 相关联
     * @param securityManager
     * @return
     */
    @Bean()
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);

        return creator;
    }
}
