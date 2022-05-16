package dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Liangzhaohao
 * @date 2021/12/16 9:14
 */
@Data
public class GDCARequest<T> implements Serializable {
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 访问令牌，用来验证客户端身份，可调申请token接口获取,根据分配的secretKey用sha256算法计算出来的hmac
     */
    private String token;
    /**
     * 应用ID，固定用citizenSign
     */
    private String appId;
    /**
     * 项目ID，固定用trustSign
     */
    private String projectId;
    /**
     * 业务数据内容
     */
    private T datas;
    /**
     * 未知，暂时未启用
     */
    private String signValue;
}
