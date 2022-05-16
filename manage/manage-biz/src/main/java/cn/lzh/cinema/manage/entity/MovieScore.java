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
 * 影院管理电影评分表
 *
 * @author pig code generator
 * @date 2022-03-29 09:15:59
 */
@Data
@TableName("movie_score")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "影院管理电影评分表")
public class MovieScore extends BaseEntity {

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
     * 用户id
     */
    @ApiModelProperty(value="用户id")
    private Long userId;

    /**
     * 评分：1~5
     */
    @ApiModelProperty(value="评分：1~5")
    private Integer score;


}
