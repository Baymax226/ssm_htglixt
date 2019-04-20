package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domian.Role;
import com.itheima.ssm.domian.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //间接的实现了UserDetailsService方法，进行登录验证
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
        //其实就是使用UserDetails的实现类User的构造函数来创建UserDetails
//        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getStatus()==0?false:true, true, true, true,
                getAuthority(userInfo.getRoles()));
        return user;
    }

    //该方法作用就是返回一个List集合，集合中装入的是角色权限的描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception{
        //spring security框架中bCryptPasswordEncoder类的encode方法可以给用户密码加密
        //需要先在配置文件中注入加密类 打开
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        UserInfo userInfo = userDao.findById(id);
        return userInfo;
    }

    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
