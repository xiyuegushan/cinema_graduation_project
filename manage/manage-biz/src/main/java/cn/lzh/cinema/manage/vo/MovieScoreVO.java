package cn.lzh.cinema.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Liangzhaohao
 * @date 2022/4/7 13:54
 */
@Data
@ApiModel(value = "电影分数展示对象")
public class MovieScoreVO {
    /**
     * 电影id
     */
    @ApiModelProperty(value = "电影id")
    private Long movieId;

    /**
     * 电影平均分
     */
    @ApiModelProperty(value = "电影平均分")
    private Float score;
}
