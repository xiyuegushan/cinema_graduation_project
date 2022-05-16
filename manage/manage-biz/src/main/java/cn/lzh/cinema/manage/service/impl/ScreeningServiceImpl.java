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

import cn.lzh.cinema.manage.entity.Screening;
import cn.lzh.cinema.manage.entity.ScreeningRoom;
import cn.lzh.cinema.manage.entity.ScreeningSeat;
import cn.lzh.cinema.manage.mapper.ScreeningMapper;
import cn.lzh.cinema.manage.mapper.ScreeningRoomMapper;
import cn.lzh.cinema.manage.mapper.ScreeningSeatMapper;
import cn.lzh.cinema.manage.service.ScreeningService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 影院管理电影排片表
 *
 * @author pig code generator
 * @date 2022-03-13 23:51:12
 */
@Service
public class ScreeningServiceImpl extends ServiceImpl<ScreeningMapper, Screening> implements ScreeningService {

    final
    ScreeningSeatMapper screeningSeatMapper;

    final
    ScreeningRoomMapper screeningRoomMapper;

    final
    ScreeningMapper screeningMapper;

    public ScreeningServiceImpl(ScreeningSeatMapper screeningSeatMapper, ScreeningRoomMapper screeningRoomMapper, ScreeningMapper screeningMapper) {
        this.screeningSeatMapper = screeningSeatMapper;
        this.screeningRoomMapper = screeningRoomMapper;
        this.screeningMapper = screeningMapper;
    }

    @Override
    public boolean saveScreeningWithSeat(Screening screening) {
        try {
            // 先保存排片数据
            screeningMapper.insert(screening);
            // 找到排片对应的影厅
            ScreeningRoom screeningRoom = screeningRoomMapper.selectById(screening.getScreeningRoomId());
            // 找到影厅对应的模板座位List
            QueryWrapper<ScreeningSeat> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(ScreeningSeat::getScreeningRoomId, screeningRoom.getId()).isNull(ScreeningSeat::getScreeningId);
            List<ScreeningSeat> screeningSeats = screeningSeatMapper.selectList(queryWrapper);
            for (ScreeningSeat screeningSeat : screeningSeats) {
                // 以模板座位List插入对应排片的座位以使用
                screeningSeat.setId(null);
                screeningSeat.setCreateTime(LocalDateTime.now());
                screeningSeat.setUpdateTime(LocalDateTime.now());
                screeningSeat.setScreeningId(screening.getId());
                screeningSeatMapper.insert(screeningSeat);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
