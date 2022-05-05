package com.akokko.service;

import com.akokko.dao.BackUserMapper;
import com.akokko.pojo.BackUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackUserService {

    @Autowired
    private BackUserMapper backUserMapper;

    public BackUser login(String name, String password) {

        BackUser loginUser = backUserMapper.backUserLogin(name, password);

        return loginUser;

    }

}
