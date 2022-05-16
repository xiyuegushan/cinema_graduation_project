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

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.lzh.cinema.manage.entity.Movie;
import cn.lzh.cinema.manage.mapper.MovieMapper;
import cn.lzh.cinema.manage.mapper.MovieScoreMapper;
import cn.lzh.cinema.manage.mapper.MovieWantsMapper;
import cn.lzh.cinema.manage.service.MovieService;
import cn.lzh.cinema.manage.utils.ListUtils;
import cn.lzh.cinema.manage.vo.MovieScoreVO;
import cn.lzh.cinema.manage.vo.MovieWantsVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 影院管理电影信息表
 *
 * @author pig code generator
 * @date 2022-03-10 21:34:57
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    final
    MovieMapper movieMapper;

    final
    MovieScoreMapper movieScoreMapper;

    final
    MovieWantsMapper movieWantsMapper;

    public MovieServiceImpl(MovieMapper movieMapper, MovieScoreMapper movieScoreMapper, MovieWantsMapper movieWantsMapper) {
        this.movieMapper = movieMapper;
        this.movieScoreMapper = movieScoreMapper;
        this.movieWantsMapper = movieWantsMapper;
    }

    @Override
    public Page<Movie> getShowingMovie(Page<Movie> page) {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().between(Movie::getReleaseDate, DateUtil.offsetDay(new Date(), -7), new Date());
        return movieMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<Movie> getComingSoonMovie(Page<Movie> page) {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().between(Movie::getReleaseDate, new Date(), DateUtil.offsetDay(new Date(), 7));
        return movieMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<Movie> getMostWantsMovie(Page<Movie> page) {
        List<Movie> mostWantsMovie = movieMapper.getMostWantsMovie();
        return ListUtils.toPage((int) page.getCurrent(), (int) page.getSize(), mostWantsMovie);
    }

    @Override
    public Page<Movie> getHighScoreMovie(Page<Movie> page) {
        List<Movie> highScoreMovie = movieMapper.getHighScoreMovie();
        return ListUtils.toPage((int) page.getCurrent(), (int) page.getSize(), highScoreMovie);
    }

    @Override
    public MovieScoreVO getMovieScore(Long movieId) {
        return movieScoreMapper.getMovieScore(movieId);
    }

    @Override
    public MovieWantsVO getMovieWants(Long movieId) {
        return movieWantsMapper.getMovieWants(movieId);
    }

    @Override
    public Page<Movie> searchMovie(Page<Movie> page, Movie movie) {
        boolean isParamExist = false;
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().orderByDesc("create_time");
        // 若电影名称不为空则模糊查询电影名称,其次类型,影星
        if (ObjectUtil.isNotNull(movie.getName())) {
            queryWrapper.lambda().like(Movie::getName, movie.getName());
            isParamExist = true;
        }
        // 若电影类型不为空则模糊查询电影类型
        if (ObjectUtil.isNotNull(movie.getCategory())) {
            queryWrapper.lambda().like(Movie::getCategory, movie.getCategory());
            isParamExist = true;
        }
        // 若影星不为空则模糊查询影星
        if (ObjectUtil.isNotNull(movie.getStar())) {
            queryWrapper.lambda().like(Movie::getStar, movie.getStar());
            isParamExist = true;
        }
        // 若产地不为空则模糊查询产地
        if (ObjectUtil.isNotNull(movie.getProducer())) {
            queryWrapper.lambda().like(Movie::getProducer, movie.getProducer());
            isParamExist = true;
        }
        // 若导演不为空则模糊查询导演
        if (ObjectUtil.isNotNull(movie.getDirector())) {
            queryWrapper.lambda().like(Movie::getDirector, movie.getDirector());
            isParamExist = true;
        }
        // 查询信息传入为空则返回全部电影列表
        if (isParamExist) {
            return movieMapper.selectPage(page, queryWrapper);
        }
        return page(page, Wrappers.query(movie));

    }

}
