package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Dai
 * @Date: 2024/07/18 10:41
 * @Description: SetmealDishMapper
 * @Version: 1.0
 */
@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品Id查询对应的套餐Id
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishId(List<Long> dishIds);

    /**
     * 批量插入套餐和菜品的关联数据
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐Id删除套餐和菜品的关联数据
     * @param setmealId
     */
    @Delete("delete from setmeal_dish where setmeal_id = ${setmealId}")
    void deleteBySetmealId(Long setmealId);


    /**
     * 根据套餐Id查询套餐和菜品的关联数据
     * @param setmealId
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getSetmealId(Long setmealId);
}
