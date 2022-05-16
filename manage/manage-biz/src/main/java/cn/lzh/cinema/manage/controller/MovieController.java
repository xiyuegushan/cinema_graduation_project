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

package cn.lzh.cinema.manage.controller;

import cn.lzh.cinema.common.core.util.R;
import cn.lzh.cinema.common.log.annotation.SysLog;
import cn.lzh.cinema.manage.entity.Movie;
import cn.lzh.cinema.manage.service.MovieService;
import cn.lzh.cinema.manage.vo.MovieScoreVO;
import cn.lzh.cinema.manage.vo.MovieWantsVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 影院管理电影信息表
 *
 * @author pig code generator
 * @date 2022-03-10 21:34:57
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
@Api(value = "movie", tags = "影院管理电影信息表管理")
public class MovieController {

    private final MovieService movieService;

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param movie 影院管理电影信息表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R getMoviePage(Page page, Movie movie) {
        return R.ok(movieService.page(page, Wrappers.query(movie)));
    }


    /**
     * 通过id查询影院管理电影信息表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(movieService.getById(id));
    }

    /**
     * 新增影院管理电影信息表
     *
     * @param movie 影院管理电影信息表
     * @return R
     */
    @ApiOperation(value = "新增影院管理电影信息表", notes = "新增影院管理电影信息表")
    @SysLog("新增影院管理电影信息表")
    @PostMapping
    public R save(@RequestBody Movie movie) {
        return R.ok(movieService.save(movie));
    }

    /**
     * 修改影院管理电影信息表
     *
     * @param movie 影院管理电影信息表
     * @return R
     */
    @ApiOperation(value = "修改影院管理电影信息表", notes = "修改影院管理电影信息表")
    @SysLog("修改影院管理电影信息表")
    @PutMapping
    public R updateById(@RequestBody Movie movie) {
        return R.ok(movieService.updateById(movie));
    }

    /**
     * 通过id删除影院管理电影信息表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除影院管理电影信息表", notes = "通过id删除影院管理电影信息表")
    @SysLog("通过id删除影院管理电影信息表")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Long id) {
        return R.ok(movieService.removeById(id));
    }

    /**
     * 获取最近热映的电影列表
     *
     * @param page 分页对象
     * @return R<Page < Movie>>
     */
    @ApiOperation(value = "获取最近热映的电影列表", notes = "获取最近热映的电影列表")
    @GetMapping("/showing")
    public R<Page<Movie>> getShowingMovie(Page<Movie> page) {
        return R.ok(movieService.getShowingMovie(page));
    }

    /**
     * 获取即将上映的电影列表
     *
     * @param page 分页对象
     * @return R<List < Movie>>
     */
    @ApiOperation(value = "获取即将上映的电影列表", notes = "获取即将上映的电影列表")
    @GetMapping("/comingsoon")
    public R<Page<Movie>> getComingSoonMovie(Page<Movie> page) {
        return R.ok(movieService.getComingSoonMovie(page));
    }

    /**
     * 获取最多想看数电影列表
     *
     * @param page 分页对象
     * @return R<List < Movie>>
     */
    @ApiOperation(value = "获取最多想看数电影列表", notes = "获取最多想看数电影列表")
    @GetMapping("/mostwants")
    public R<Page<Movie>> getMostWantsMovie(Page<Movie> page) {
        return R.ok(movieService.getMostWantsMovie(page));
    }

    /**
     * 获取评分最高电影列表
     *
     * @param page 分页对象
     * @return R<List < Movie>>
     */
    @ApiOperation(value = "获取评分最高电影列表", notes = "获取评分最高电影列表")
    @GetMapping("/highscore")
    public R<Page<Movie>> getHighScoreMovie(Page<Movie> page) {
        return R.ok(movieService.getHighScoreMovie(page));
    }

    /**
     * 获取当前电影平均分
     *
     * @param movieId 电影id
     * @return R<MovieScoreVO>
     */
    @ApiOperation(value = "获取当前电影平均分", notes = "获取当前电影平均分")
    @GetMapping("/score/{movieId}")
    public R<MovieScoreVO> getMovieScore(@PathVariable Long movieId) {
        return R.ok(movieService.getMovieScore(movieId));
    }

    /**
     * 获取当前电影想看数
     *
     * @param movieId 电影id
     * @return R<MovieWantsVO>
     */
    @ApiOperation(value = "获取当前电影想看数", notes = "获取当前电影想看数")
    @GetMapping("/wants/{movieId}")
    public R<MovieWantsVO> getMovieWants(@PathVariable Long movieId) {
        return R.ok(movieService.getMovieWants(movieId));
    }

    /**
     * 根据电影信息模糊查询电影列表
     *
     * @param page  分页对象
     * @param movie 电影
     * @return R<Page < Movie>>
     */
    @ApiOperation(value = "根据电影信息模糊查询电影列表", notes = "根据电影信息模糊查询电影列表")
    @PostMapping("/search")
    public R<Page<Movie>> searchMovie(Page<Movie> page, @RequestBody Movie movie) {
        return R.ok(movieService.searchMovie(page, movie));
    }
}
