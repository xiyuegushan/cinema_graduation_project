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

package cn.lzh.cinema.manage.mapper;

import cn.lzh.cinema.manage.entity.Movie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 影院管理电影信息表
 *
 * @author pig code generator
 * @date 2022-03-10 21:34:57
 */
@Mapper
public interface MovieMapper extends BaseMapper<Movie> {
    /**
     * 获取最多想看数电影列表
     *
     * @return {@link List}<{@link Movie}>
     */
    List<Movie> getMostWantsMovie();

    /**
     * 获取评分最高电影列表
     *
     * @return {@link List}<{@link Movie}>
     */
    List<Movie> getHighScoreMovie();
}
