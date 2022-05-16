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
import cn.lzh.cinema.manage.entity.UserOrderSeat;
import cn.lzh.cinema.manage.service.UserOrderSeatService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 影院管理订单座位关联表
 *
 * @author pig code generator
 * @date 2022-04-09 18:11:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/userorderseat" )
@Api(value = "userorderseat", tags = "影院管理订单座位关联表管理")
public class UserOrderSeatController {

    private final  UserOrderSeatService userOrderSeatService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param userOrderSeat 影院管理订单座位关联表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getUserOrderSeatPage(Page page, UserOrderSeat userOrderSeat) {
        return R.ok(userOrderSeatService.page(page, Wrappers.query(userOrderSeat)));
    }


    /**
     * 通过id查询影院管理订单座位关联表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(userOrderSeatService.getById(id));
    }

    /**
     * 新增影院管理订单座位关联表
     * @param userOrderSeat 影院管理订单座位关联表
     * @return R
     */
    @ApiOperation(value = "新增影院管理订单座位关联表", notes = "新增影院管理订单座位关联表")
    @SysLog("新增影院管理订单座位关联表" )
    @PostMapping
    public R save(@RequestBody UserOrderSeat userOrderSeat) {
        return R.ok(userOrderSeatService.save(userOrderSeat));
    }

    /**
     * 修改影院管理订单座位关联表
     * @param userOrderSeat 影院管理订单座位关联表
     * @return R
     */
    @ApiOperation(value = "修改影院管理订单座位关联表", notes = "修改影院管理订单座位关联表")
    @SysLog("修改影院管理订单座位关联表" )
    @PutMapping
    public R updateById(@RequestBody UserOrderSeat userOrderSeat) {
        return R.ok(userOrderSeatService.updateById(userOrderSeat));
    }

    /**
     * 通过id删除影院管理订单座位关联表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除影院管理订单座位关联表", notes = "通过id删除影院管理订单座位关联表")
    @SysLog("通过id删除影院管理订单座位关联表" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Long id) {
        return R.ok(userOrderSeatService.removeById(id));
    }

}
