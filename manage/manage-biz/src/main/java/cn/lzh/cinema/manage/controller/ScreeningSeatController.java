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
import cn.lzh.cinema.manage.entity.ScreeningSeat;
import cn.lzh.cinema.manage.service.ScreeningSeatService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 影院管理座位表
 *
 * @author pig code generator
 * @date 2022-03-13 23:46:48
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/screeningseat")
@Api(value = "screeningseat", tags = "影院管理座位表管理")
public class ScreeningSeatController {

    private final ScreeningSeatService screeningSeatService;

    /**
     * 分页查询
     *
     * @param page          分页对象
     * @param screeningSeat 影院管理座位表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R getScreeningSeatPage(Page page, ScreeningSeat screeningSeat) {
        return R.ok(screeningSeatService.page(page, Wrappers.query(screeningSeat)));
    }


    /**
     * 通过id查询影院管理座位表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(screeningSeatService.getById(id));
    }

    /**
     * 新增影院管理座位表
     *
     * @param screeningSeat 影院管理座位表
     * @return R
     */
    @ApiOperation(value = "新增影院管理座位表", notes = "新增影院管理座位表")
    @SysLog("新增影院管理座位表")
    @PostMapping
    public R save(@RequestBody ScreeningSeat screeningSeat) {
        return R.ok(screeningSeatService.save(screeningSeat));
    }

    /**
     * 修改影院管理座位表
     *
     * @param screeningSeat 影院管理座位表
     * @return R
     */
    @ApiOperation(value = "修改影院管理座位表", notes = "修改影院管理座位表")
    @SysLog("修改影院管理座位表")
    @PutMapping
    public R updateById(@RequestBody ScreeningSeat screeningSeat) {
        return R.ok(screeningSeatService.updateById(screeningSeat));
    }

    /**
     * 通过id删除影院管理座位表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除影院管理座位表", notes = "通过id删除影院管理座位表")
    @SysLog("通过id删除影院管理座位表")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Long id) {
        return R.ok(screeningSeatService.removeById(id));
    }

    /**
     * 修改影院管理座位表
     *
     * @param screeningSeatList 影院管理座位List
     * @return R
     */
    @ApiOperation(value = "修改影院管理座位表", notes = "修改影院管理座位表")
    @SysLog("修改影院管理座位表")
    @PutMapping("/choose/{buy}/{screeningId}")
    public R updateGroups(@PathVariable("buy") Boolean buy, @PathVariable("screeningId") Long screeningId, @RequestBody List<ScreeningSeat> screeningSeatList) {
        return screeningSeatService.updateGroups(buy, screeningId, screeningSeatList);
    }
}
