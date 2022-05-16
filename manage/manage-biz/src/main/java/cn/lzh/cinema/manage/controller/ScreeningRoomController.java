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
import cn.lzh.cinema.manage.entity.ScreeningRoom;
import cn.lzh.cinema.manage.service.ScreeningRoomService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 影院管理影厅表
 *
 * @author pig code generator
 * @date 2022-03-13 23:46:17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/screeningroom" )
@Api(value = "screeningroom", tags = "影院管理影厅表管理")
public class ScreeningRoomController {

    private final  ScreeningRoomService screeningRoomService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param screeningRoom 影院管理影厅表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getScreeningRoomPage(Page page, ScreeningRoom screeningRoom) {
        return R.ok(screeningRoomService.page(page, Wrappers.query(screeningRoom)));
    }


    /**
     * 通过id查询影院管理影厅表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(screeningRoomService.getById(id));
    }

    /**
     * 新增影院管理影厅表
     * @param screeningRoom 影院管理影厅表
     * @return R
     */
    @ApiOperation(value = "新增影院管理影厅表", notes = "新增影院管理影厅表")
    @SysLog("新增影院管理影厅表" )
    @PostMapping
    public R save(@RequestBody ScreeningRoom screeningRoom) {
        return R.ok(screeningRoomService.save(screeningRoom));
    }

    /**
     * 修改影院管理影厅表
     * @param screeningRoom 影院管理影厅表
     * @return R
     */
    @ApiOperation(value = "修改影院管理影厅表", notes = "修改影院管理影厅表")
    @SysLog("修改影院管理影厅表" )
    @PutMapping
    public R updateById(@RequestBody ScreeningRoom screeningRoom) {
        return R.ok(screeningRoomService.updateById(screeningRoom));
    }

    /**
     * 通过id删除影院管理影厅表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除影院管理影厅表", notes = "通过id删除影院管理影厅表")
    @SysLog("通过id删除影院管理影厅表" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Long id) {
        return R.ok(screeningRoomService.removeById(id));
    }

}
