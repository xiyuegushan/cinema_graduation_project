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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.lzh.cinema.common.mybatis.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 影院管理座位表
 *
 * @author pig code generator
 * @date 2022-03-13 23:46:48
 */
@Data
@TableName("screening_seat")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "影院管理座位表")
public class ScreeningSeat extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value="主键")
    private Long id;

    /**
     * 影厅id
     */
    @ApiModelProperty(value="影厅id")
    private Long screeningRoomId;

    /**
     * 影厅id
     */
    @ApiModelProperty(value="场次id")
    private Long screeningId;

    /**
     * 横坐标
     */
    @ApiModelProperty(value="横坐标")
    private Integer siteX;

    /**
     * 纵坐标
     */
    @ApiModelProperty(value="纵坐标")
    private Integer siteY;

    /**
     * 状态：-1:不可用/0:可购买/1:已预定/2:已付款
     */
    @ApiModelProperty(value="状态：-1:不可用/0:可购买/1:已预定/2:已付款")
    private Integer status;


}
