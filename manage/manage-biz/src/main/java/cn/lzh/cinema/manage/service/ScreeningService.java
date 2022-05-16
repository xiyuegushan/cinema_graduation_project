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
import cn.lzh.cinema.manage.entity.Screening;

/**
 * 影院管理电影排片表
 *
 * @author pig code generator
 * @date 2022-03-13 23:51:12
 */
public interface ScreeningService extends IService<Screening> {
    /**
     * 新增排片并且按照座位布局拷贝座位
     *
     * @param screening 排片信息
     * @return boolean
     */
    boolean saveScreeningWithSeat(Screening screening);
}
