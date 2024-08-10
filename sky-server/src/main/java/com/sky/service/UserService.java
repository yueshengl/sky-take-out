package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * @Author: Dai
 * @Date: 2024/07/25 21:04
 * @Description: UserService
 * @Version: 1.0
 */
public interface UserService {
    /**
     * 根据微信授权码实现微信登陆
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
