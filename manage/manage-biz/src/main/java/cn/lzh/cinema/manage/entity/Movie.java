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

import java.time.LocalDate;

/**
 * 影院管理电影信息表
 *
 * @author pig code generator
 * @date 2022-03-10 21:34:57
 */
@Data
@TableName("movie")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "影院管理电影信息表")
public class Movie extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value="主键")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value="名称")
    private String name;

    /**
     * 封面
     */
    @ApiModelProperty(value="封面")
    private String img;

    /**
     * 简介
     */
    @ApiModelProperty(value="简介")
    private String introduction;

    /**
     * 主演
     */
    @ApiModelProperty(value="主演")
    private String star;

    /**
     * 制片国家/地区
     */
    @ApiModelProperty(value="制片国家/地区")
    private String producer;

    /**
     * 导演
     */
    @ApiModelProperty(value="导演")
    private String director;

    /**
     * 编剧
     */
    @ApiModelProperty(value="编剧")
    private String screenwriter;

    /**
     * 语言
     */
    @ApiModelProperty(value="语言")
    private String language;

    /**
     * 片长(分钟)
     */
    @ApiModelProperty(value="片长(分钟)")
    private String length;

    /**
     * 类别
     */
    @ApiModelProperty(value="类别")
    private String category;

    /**
     * 上映日期
     */
    @ApiModelProperty(value="上映日期")
    private LocalDate releaseDate;


}
