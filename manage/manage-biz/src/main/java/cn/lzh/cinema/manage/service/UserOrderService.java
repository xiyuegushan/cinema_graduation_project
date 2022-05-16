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

package cn.lzh.cinema.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.lzh.cinema.manage.entity.UserOrder;

/**
 * 影院管理订单表
 *
 * @author pig code generator
 * @date 2022-03-18 15:28:47
 */
public interface UserOrderService extends IService<UserOrder> {
    /**
     * 取消订单
     *
     * @param userOrder 用户订单
     * @return boolean
     */
    boolean cancelOrder(UserOrder userOrder);
}
