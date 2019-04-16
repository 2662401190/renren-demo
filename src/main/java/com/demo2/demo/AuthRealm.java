package com.demo2.demo;

import com.demo2.demo.model.Permission;
import com.demo2.demo.model.Role;
import com.demo2.demo.model.User;
import com.demo2.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 贺威
 * @create 2019-04-12 15:12
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权 使用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //  从session  里面获取用户
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList=new ArrayList<>();
        List<String> releNameList=new ArrayList<>();
        //  拿到所有用户的角色
        Set<Role> roleSet=user.getRoles();
        if (CollectionUtils.isEmpty(roleSet)) {
            //  角色不为空
            for (Role role : roleSet) {
                releNameList.add(role.getName());
                Set<Permission> permissionSet=role.getPermissions();
                if(CollectionUtils.isEmpty(permissionSet)){
                    for (Permission permission : permissionSet) {
                        //  根据 permissionName 来判断一个用户是否具有某个权限
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(releNameList);
        info.addStringPermissions(permissionList);
        return info;
    }

    /**
     * 认证登陆
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        String username=usernamePasswordToken.getUsername();
        //  从数据库中取出用户
        User user=userService.findByUsername(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
