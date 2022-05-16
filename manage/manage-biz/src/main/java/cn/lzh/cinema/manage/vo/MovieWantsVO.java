package cn.lzh.cinema.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Liangzhaohao
 * @date 2022/4/7 13:59
 */
@Data
@ApiModel(value = "电影想看数展示对象")
public class MovieWantsVO {
    /**
     * 电影id
     */
    @ApiModelProperty(value = "电影id")
    private Long movieId;

    /**
     * 电影想看数
     */
    @ApiModelProperty(value = "电影想看数")
    private Integer count;
}
