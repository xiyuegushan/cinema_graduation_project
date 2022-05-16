/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the cinema4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package cn.lzh.cinema.manage.service.impl;

import cn.lzh.cinema.manage.entity.ScreeningSeat;
import cn.lzh.cinema.manage.entity.UserOrder;
import cn.lzh.cinema.manage.entity.UserOrderSeat;
import cn.lzh.cinema.manage.mapper.ScreeningSeatMapper;
import cn.lzh.cinema.manage.mapper.UserOrderMapper;
import cn.lzh.cinema.manage.mapper.UserOrderSeatMapper;
import cn.lzh.cinema.manage.service.UserOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 影院管理订单表
 *
 * @author pig code generator
 * @date 2022-03-18 15:28:47
 */
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {

    final
    UserOrderMapper userOrderMapper;

    final
    ScreeningSeatMapper screeningSeatMapper;

    final
    UserOrderSeatMapper userOrderSeatMapper;

    public UserOrderServiceImpl(UserOrderMapper userOrderMapper, ScreeningSeatMapper screeningSeatMapper, UserOrderSeatMapper userOrderSeatMapper) {
        this.userOrderMapper = userOrderMapper;
        this.screeningSeatMapper = screeningSeatMapper;
        this.userOrderSeatMapper = userOrderSeatMapper;
    }

    @Override
    public boolean cancelOrder(UserOrder userOrder) {
        try {
            // 先改变订单状态为-1
            userOrderMapper.updateById(userOrder);
            // 改变订单对应的座位状态,恢复为可购买
            QueryWrapper<UserOrderSeat> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(UserOrderSeat::getUserOrderId, userOrder.getId());
            List<UserOrderSeat> userOrderSeats = userOrderSeatMapper.selectList(queryWrapper);
            for(UserOrderSeat userOrderSeat : userOrderSeats){
                ScreeningSeat screeningSeat = screeningSeatMapper.selectById(userOrderSeat.getScreeningSeatId());
                screeningSeat.setStatus(0);
                screeningSeatMapper.updateById(screeningSeat);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
