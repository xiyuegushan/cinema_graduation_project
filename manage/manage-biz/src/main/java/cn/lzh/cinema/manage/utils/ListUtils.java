package cn.lzh.cinema.manage.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liangzhaohao
 * @date 2022/4/7 11:42
 */
public class ListUtils {
    /**
     * List根据分页参数封装在Page中
     *
     * @param current 当前页面
     * @param size    页面大小
     * @param list    需要分页的List
     * @return {@link Page}<{@link T}>
     */
    public static <T> Page<T> toPage(Integer current, Integer size, List<T> list) {
        Page<T> page = new Page<>();
        int listSize = list.size();
        if (size > listSize) {
            size = listSize;
        }
        //求出最大页数，防止currentPage越界
        int maxPage = listSize % size == 0 ? listSize / size : listSize / size + 1;
        if (current > maxPage) {
            current = maxPage;
        }
        //当前页第一条数据下标
        int curIds = current > 1 ? (current - 1) * size : 0;
        List<T> pageList = new ArrayList<>();
        //将当前页的数据放进pageList
        for (int i = 0; i < size && curIds + i < listSize; i++) {
            pageList.add(list.get(curIds + i));
        }
        page.setCurrent(current).setSize(size).setTotal(list.size()).setRecords(pageList);
        return page;
    }
}
