package dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Liangzhaohao
 * @date 2021/12/16 9:26
 */
@Data
public class GDCAApplyAgent implements Serializable {
    /**
     * 经办人姓名
     */
    private String name;
    /**
     * 经办人证件号
     */
    private String idNum;
    /**
     * 经办人联系电话
     */
    private String contactPhone;
}
