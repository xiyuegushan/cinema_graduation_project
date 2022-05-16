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

import cn.lzh.cinema.manage.entity.Movie;
import cn.lzh.cinema.manage.vo.MovieScoreVO;
import cn.lzh.cinema.manage.vo.MovieWantsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 影院管理电影信息表
 *
 * @author pig code generator
 * @date 2022-03-10 21:34:57
 */
public interface MovieService extends IService<Movie> {

    /**
     * 分页获取最近热映电影
     *
     * @param page 分页参数
     * @return {@link Page}<{@link Movie}>
     */
    Page<Movie> getShowingMovie(Page<Movie> page);

    /**
     * 分页获取即将上映电影
     *
     * @param page 分页参数
     * @return {@link Page}<{@link Movie}>
     */
    Page<Movie> getComingSoonMovie(Page<Movie> page);

    /**
     * 获取最多想看数电影列表
     *
     * @param page 分页参数
     * @return {@link Page}<{@link Movie}>
     */
    Page<Movie> getMostWantsMovie(Page<Movie> page);

    /**
     * 获取评分最高电影列表
     *
     * @param page 分页参数
     * @return {@link Page}<{@link Movie}>
     */
    Page<Movie> getHighScoreMovie(Page<Movie> page);


    /**
     * 得到电影平均分
     *
     * @param movieId 电影id
     * @return {@link MovieScoreVO}
     */
    MovieScoreVO getMovieScore(Long movieId);

    /**
     * 得到电影想看数
     *
     * @param movieId 电影id
     * @return {@link MovieWantsVO}
     */
    MovieWantsVO getMovieWants(Long movieId);

    /**
     * 根据电影信息模糊查询电影列表
     *
     * @param page  分页参数
     * @param movie 电影
     * @return {@link Page}<{@link Movie}>
     */
    Page<Movie> searchMovie(Page<Movie> page,Movie movie);
}
