package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.entity.Orders;
import com.sky.mapper.DishMapper;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Dai
 * @Date: 2024/08/16 9:19
 * @Description: WorkspaceServiceImpl
 * @Version: 1.0
 */
@Service
public class WorkspaceServiceImpl implements WorkspaceService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 工作台今日数据查询
     * @param begin
     * @param end
     * @return
     */
    @Override
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        Map map = new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);
        //查询总订单数
        Integer totalOrder = orderMapper.countByMap(map);
        //营业额
        map.put("status", Orders.COMPLETED);
        Double turnover = orderMapper.sumByMap(map);
        turnover = turnover == null ? 0.0 : turnover;
        //有效订单数
        Integer validOrder = orderMapper.countByMap(map);

        Double unitPrice = 0.0;
        Double orderCompletionRate = 0.0;
        if(totalOrder != 0){
            //平均客单价
            unitPrice = turnover / validOrder;
            //订单完成率
            orderCompletionRate = validOrder.doubleValue() / totalOrder;
        }
        //新增用户数
        Integer newUsers = userMapper.countByMap(map);
        return BusinessDataVO
                .builder()
                .turnover(turnover)
                .validOrderCount(validOrder)
                .orderCompletionRate(orderCompletionRate)
                .unitPrice(unitPrice)
                .newUsers(newUsers)
                .build();
    }

    /**
     * 查询订单管理数据
     * @return
     */
    @Override
    public OrderOverViewVO getOrderOverView() {
        Map map = new HashMap();
        map.put("begin",LocalDateTime.now().with(LocalDateTime.MIN));
        map.put("status",Orders.TO_BE_CONFIRMED);
        //待接单
        Integer waitingOrders = orderMapper.countByMap(map);
        //待派送
        map.put("status",Orders.CONFIRMED);
        Integer deliveredOrders = orderMapper.countByMap(map);
        //已完成
        map.put("status",Orders.COMPLETED);
        Integer completedOrders = orderMapper.countByMap(map);
        //已取消
        map.put("status",Orders.CANCELLED);
        Integer cancelledOrders = orderMapper.countByMap(map);
        //全部订单
        map.put("status",null);
        Integer allOrders = orderMapper.countByMap(map);
        return OrderOverViewVO
                .builder()
                .waitingOrders(waitingOrders)
                .deliveredOrders(deliveredOrders)
                .completedOrders(completedOrders)
                .cancelledOrders(cancelledOrders)
                .allOrders(allOrders)
                .build();
    }

    /**
     * 查询菜品总览
     * @return
     */
    @Override
    public DishOverViewVO getDishOverView() {
        Map map = new HashMap();
        map.put("status", StatusConstant.ENABLE);
        Integer sold = dishMapper.countByMap(map);
        map.put("status",StatusConstant.DISABLE);
        Integer discontinued = dishMapper.countByMap(map);
        return DishOverViewVO
                .builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }


    /**
     * 查询套餐总览
     * @return
     */
    @Override
    public SetmealOverViewVO getSetmealOverView() {
        Map map = new HashMap();
        map.put("status", StatusConstant.ENABLE);
        Integer sold = setmealMapper.countByMap(map);
        map.put("status",StatusConstant.DISABLE);
        Integer discontinued = setmealMapper.countByMap(map);
        return SetmealOverViewVO
                .builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }
}
