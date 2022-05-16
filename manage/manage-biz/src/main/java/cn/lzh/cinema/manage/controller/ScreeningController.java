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
import cn.lzh.cinema.manage.entity.Screening;
import cn.lzh.cinema.manage.service.ScreeningService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 影院管理电影排片表
 *
 * @author pig code generator
 * @date 2022-03-13 23:51:12
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/screening")
@Api(value = "screening", tags = "影院管理电影排片表管理")
public class ScreeningController {

    private final ScreeningService screeningService;

    /**
     * 分页查询
     *
     * @param page      分页对象
     * @param screening 影院管理电影排片表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R getScreeningPage(Page page, Screening screening) {
//        QueryWrapper<Screening> queryWrapper = new QueryWrapper<>(screening);
//        queryWrapper.select().orderByAsc("screening_start_time");
//        return R.ok(screeningService.page(page, queryWrapper));
        return R.ok(screeningService.page(page, Wrappers.query(screening)));
    }


    /**
     * 通过id查询影院管理电影排片表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(screeningService.getById(id));
    }

    /**
     * 新增影院管理电影排片表
     *
     * @param screening 影院管理电影排片表
     * @return R
     */
    @ApiOperation(value = "新增影院管理电影排片表", notes = "新增影院管理电影排片表")
    @SysLog("新增影院管理电影排片表")
    @PostMapping
    public R save(@RequestBody Screening screening) {
//        return R.ok(screeningService.save(screening));
        return R.ok(screeningService.saveScreeningWithSeat(screening));
    }

    /**
     * 修改影院管理电影排片表
     *
     * @param screening 影院管理电影排片表
     * @return R
     */
    @ApiOperation(value = "修改影院管理电影排片表", notes = "修改影院管理电影排片表")
    @SysLog("修改影院管理电影排片表")
    @PutMapping
    public R updateById(@RequestBody Screening screening) {
        return R.ok(screeningService.updateById(screening));
    }

    /**
     * 通过id删除影院管理电影排片表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除影院管理电影排片表", notes = "通过id删除影院管理电影排片表")
    @SysLog("通过id删除影院管理电影排片表")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Long id) {
        return R.ok(screeningService.removeById(id));
    }

    /**
     * 分页查询(只查询开场时间在现在及之后的)
     *
     * @param page      分页对象
     * @param screening 影院管理电影排片表
     * @return
     */
    @ApiOperation(value = "分页查询(只查询开场时间在现在及之后的)", notes = "分页查询(只查询开场时间在现在及之后的)")
    @GetMapping("/page/fromnow")
    public R getScreeningPageFromNow(Page page, Screening screening) {
        QueryWrapper<Screening> queryWrapper = new QueryWrapper<>(screening);
        queryWrapper.select().orderByAsc("screening_start_time");
        queryWrapper.lambda().ge(Screening::getScreeningStartTime, LocalDateTime.now());
        return R.ok(screeningService.page(page, queryWrapper));
//        return R.ok(screeningService.page(page, Wrappers.query(screening)));
    }
}
