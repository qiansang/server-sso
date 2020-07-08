package com.hongtao.serversso.config;

import com.hongtao.serversso.bean.User;
import com.hongtao.serversso.dao.UserMapper;
import com.hongtao.serversso.utils.Md5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author : wanghongtao
 * @Date : 2020/7/7 20:46
 */
public class UserRealm  extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger("sso");

    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        logger.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        // TODO Auto-generated method stub
        logger.info("认证");
        //shiro判断逻辑
        UsernamePasswordToken user = (UsernamePasswordToken) arg0;
        User newUser = userMapper.selectUserByUsernameAndPassword(user.getUsername(),Md5Utils.getMd5(String.valueOf(user.getPassword())));
        if(newUser == null){
            //用户名错误
            return null;
        }
        return new SimpleAuthenticationInfo(newUser,String.valueOf(user.getPassword()),"");
    }

}
