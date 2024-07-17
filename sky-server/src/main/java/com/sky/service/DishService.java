package com.sky.service;

import com.sky.dto.DishDTO;

/**
 * @Author: Dai
 * @Date: 2024/07/17 10:47
 * @Description: DishService
 * @Version: 1.0
 */

public interface DishService {

    /**
     * 新增菜品和对应的口味
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
