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

import cn.lzh.cinema.common.core.util.R;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.lzh.cinema.manage.entity.ScreeningSeat;

import java.util.List;

/**
 * 影院管理座位表
 *
 * @author pig code generator
 * @date 2022-03-13 23:46:48
 */
public interface ScreeningSeatService extends IService<ScreeningSeat> {
    /**
     * 更新座椅组状态
     *
     * @param screeningSeatList 座椅List
     * @param buy               是否为购买
     * @param screeningId       排片id
     * @return {@link R}
     */
    R updateGroups(Boolean buy, Long screeningId, List<ScreeningSeat> screeningSeatList);
}
