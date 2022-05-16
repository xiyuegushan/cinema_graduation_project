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

import cn.hutool.core.util.StrUtil;
import cn.lzh.cinema.common.core.util.R;
import cn.lzh.cinema.common.security.util.SecurityUtils;
import cn.lzh.cinema.manage.entity.ScreeningSeat;
import cn.lzh.cinema.manage.entity.UserOrder;
import cn.lzh.cinema.manage.entity.UserOrderSeat;
import cn.lzh.cinema.manage.mapper.ScreeningSeatMapper;
import cn.lzh.cinema.manage.mapper.UserOrderMapper;
import cn.lzh.cinema.manage.mapper.UserOrderSeatMapper;
import cn.lzh.cinema.manage.service.ScreeningSeatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 影院管理座位表
 *
 * @author pig code generator
 * @date 2022-03-13 23:46:48
 */
@Service
public class ScreeningSeatServiceImpl extends ServiceImpl<ScreeningSeatMapper, ScreeningSeat> implements ScreeningSeatService {

    final
    ScreeningSeatMapper screeningSeatMapper;

    final
    UserOrderSeatMapper userOrderSeatMapper;

    final
    UserOrderMapper userOrderMapper;

    public ScreeningSeatServiceImpl(ScreeningSeatMapper screeningSeatMapper, UserOrderSeatMapper userOrderSeatMapper, UserOrderMapper userOrderMapper) {
        this.screeningSeatMapper = screeningSeatMapper;
        this.userOrderSeatMapper = userOrderSeatMapper;
        this.userOrderMapper = userOrderMapper;
    }

    @Override
    public R updateGroups(Boolean buy, Long screeningId, List<ScreeningSeat> screeningSeatList) {
        if (buy) {
            for (ScreeningSeat screeningSeat : screeningSeatList) {
                if (screeningSeat.getStatus() == 2) {
                    // 判断想要购买的座位是否有座位已经被其他人购买
                    ScreeningSeat screeningSeatById = screeningSeatMapper.selectById(screeningSeat.getId());
                    if (screeningSeatById.getStatus() == 2) {
                        return R.failed(false, StrUtil.format("{}排{}号座位已被购买,请重新选择", screeningSeatById.getSiteX(), screeningSeatById.getSiteY()));
                    }
                }
                screeningSeatMapper.updateById(screeningSeat);
            }
            // 新增订单
            UserOrder userOrder = new UserOrder();
            userOrder.setScreeningId(screeningId);
            userOrder.setUserId(SecurityUtils.getUser().getId());
            userOrder.setStatus(0);
            userOrderMapper.insert(userOrder);
            // 新增订单与座位关联
            for (ScreeningSeat screeningSeat : screeningSeatList) {
                UserOrderSeat userOrderSeat = new UserOrderSeat();
                userOrderSeat.setUserOrderId(userOrder.getId());
                userOrderSeat.setScreeningSeatId(screeningSeat.getId());
                userOrderSeatMapper.insert(userOrderSeat);
            }
        }
        for (ScreeningSeat screeningSeat : screeningSeatList) {
            ScreeningSeat screeningSeatById = screeningSeatMapper.selectById(screeningSeat.getId());
            if (screeningSeatById.getStatus() == 1 || screeningSeatById.getStatus() == 2) {
                if (!screeningSeatById.getUpdateBy().equals(SecurityUtils.getUser().getUsername())) {
                    return R.failed(false, StrUtil.format("{}排{}号座位不是你预定的,无法取消预定", screeningSeatById.getSiteX(), screeningSeatById.getSiteY()));
                }
            }
            screeningSeatMapper.updateById(screeningSeat);
        }
        return R.ok(true, "更改座位状态成功");
    }
}
