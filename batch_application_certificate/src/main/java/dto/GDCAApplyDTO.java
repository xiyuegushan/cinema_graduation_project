package dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Liangzhaohao
 * @date 2021/12/16 9:24
 */
@Data
public class GDCAApplyDTO implements Serializable {
    /**
     * 如果调用本接口失败并返回了orderNo,请在未改变任何数据的情况下的再次调用时传回
     */
    private String orderNo;
    /**
     * 申请人信息
     */
    private GDCAApplyCert cert;
    /**
     * 经办人信息
     */
    private GDCAApplyAgent agent;
}
