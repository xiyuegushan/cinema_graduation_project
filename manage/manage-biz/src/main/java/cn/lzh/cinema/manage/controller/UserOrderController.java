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

package cn.lzh.cinema.manage.controller;

import cn.lzh.cinema.common.core.util.R;
import cn.lzh.cinema.common.log.annotation.SysLog;
import cn.lzh.cinema.manage.entity.UserOrder;
import cn.lzh.cinema.manage.service.UserOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 影院管理订单表
 *
 * @author pig code generator
 * @date 2022-03-18 15:28:47
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/userorder")
@Api(value = "userorder", tags = "影院管理订单表管理")
public class UserOrderController {

    private final UserOrderService userOrderService;

    /**
     * 分页查询
     *
     * @param page      分页对象
     * @param userOrder 影院管理订单表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R getUserOrderPage(Page page, UserOrder userOrder) {
        QueryWrapper<UserOrder> queryWrapper = new QueryWrapper<>(userOrder);
        queryWrapper.select().orderByDesc("create_time");
        return R.ok(userOrderService.page(page, queryWrapper));
//        return R.ok(userOrderService.page(page, Wrappers.query(userOrder)));
    }


    /**
     * 通过id查询影院管理订单表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(userOrderService.getById(id));
    }

    /**
     * 新增影院管理订单表
     *
     * @param userOrder 影院管理订单表
     * @return R
     */
    @ApiOperation(value = "新增影院管理订单表", notes = "新增影院管理订单表")
    @SysLog("新增影院管理订单表")
    @PostMapping
    public R save(@RequestBody UserOrder userOrder) {
        return R.ok(userOrderService.save(userOrder));
    }

    /**
     * 修改影院管理订单表
     *
     * @param userOrder 影院管理订单表
     * @return R
     */
    @ApiOperation(value = "修改影院管理订单表", notes = "修改影院管理订单表")
    @SysLog("修改影院管理订单表")
    @PutMapping
    public R updateById(@RequestBody UserOrder userOrder) {
        if (userOrder.getStatus() == -1) {
            return R.ok(userOrderService.cancelOrder(userOrder));
        }
        return R.ok(userOrderService.updateById(userOrder));
    }

    /**
     * 通过id删除影院管理订单表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除影院管理订单表", notes = "通过id删除影院管理订单表")
    @SysLog("通过id删除影院管理订单表")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Long id) {
        return R.ok(userOrderService.removeById(id));
    }
}
