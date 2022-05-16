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

import cn.lzh.cinema.manage.vo.MovieScoreVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.lzh.cinema.manage.entity.MovieScore;
import org.apache.ibatis.annotations.Mapper;

/**
 * 影院管理电影评分表
 *
 * @author pig code generator
 * @date 2022-03-29 09:15:59
 */
@Mapper
public interface MovieScoreMapper extends BaseMapper<MovieScore> {
    /**
     * 得到电影平均分
     *
     * @param movieId 电影id
     * @return {@link MovieScoreVO}
     */
    MovieScoreVO getMovieScore(Long movieId);
}
