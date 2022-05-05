package com.akokko.service;

import com.akokko.dao.UserMapper;
import com.akokko.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Integer regist(String name, String email, String password, String code) {
        User registUser = new User(name, email, password, code, 0);
        int count = userMapper.insertSelective(registUser);
        return count;
    }

    public User login(String email, String password) {
        User loginUser = new User(email, password);
        User user = userMapper.selectOne(loginUser);
        return user;
    }

    public boolean verify(String code) {
        User codeUser = new User();
        codeUser.setCode(code);
        User user = userMapper.selectOne(codeUser);
        if (user == null) {
            return false;
        }
        user.setVerify(1);
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    public boolean isVerify(String name, String email) {
        User emailUser = new User();
        emailUser.setName(name);
        emailUser.setEmail(email);
        User user = userMapper.selectOne(emailUser);
        if (!"1".equals(user.getVerify().toString())) {
            return false;
        }
        return true;
    }

    public boolean checkEmail(String email) {
        List<User> userList = userMapper.checkEmail(email);
        if (userList == null || userList.size() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public User forgetPassword(String name, String email) {
        User forgetUser = new User();
        forgetUser.setName(name);
        forgetUser.setEmail(email);
        User user = userMapper.selectOne(forgetUser);
        return user;
    }

    public void insertPasswordCode(String email, String code) {
        User codeUser = new User();
        codeUser.setEmail(email);
        User user = userMapper.selectOne(codeUser);
        user.setCode(code);
        userMapper.updateByPrimaryKeySelective(user);
    }

    public boolean changePassword(String email, String password, String code) {
        int count = userMapper.changePassword(email, password, code);
        if (count <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public int findUserCount() {

        List list = userMapper.selectAll();
        return list.size();

    }

}
