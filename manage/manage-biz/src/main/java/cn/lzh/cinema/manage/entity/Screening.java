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
package cn.lzh.cinema.manage.entity;

import cn.lzh.cinema.common.mybatis.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 影院管理电影排片表
 *
 * @author pig code generator
 * @date 2022-03-13 23:51:12
 */
@Data
@TableName("screening")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "影院管理电影排片表")
public class Screening extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value="主键")
    private Long id;

    /**
     * 电影id
     */
    @ApiModelProperty(value="电影id")
    private Long movieId;

    /**
     * 影厅id
     */
    @ApiModelProperty(value="影厅id")
    private Long screeningRoomId;

    /**
     * 放映方式: 0:2D/1:3D/2:IMAX
     */
    @ApiModelProperty(value="放映方式: 0:2D/1:3D/2:IMAX")
    private Integer type;

    /**
     * 影票价格
     */
    @ApiModelProperty(value="影票价格")
    private Integer price;

    /**
     * 放映开始时间
     */
    @ApiModelProperty(value="放映开始时间")
    private LocalDateTime screeningStartTime;

    /**
     * 放映结束时间
     */
    @ApiModelProperty(value="放映结束时间")
    private LocalDateTime screeningEndTime;


}
