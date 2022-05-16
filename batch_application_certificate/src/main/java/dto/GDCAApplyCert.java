package dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Liangzhaohao
 * @date 2021/12/16 9:26
 */
@Data
public class GDCAApplyCert implements Serializable {
    /**
     * 名称
     */
    private String name;
    /**
     * 证书类型：1.企业,2.政府机构,3.个人
     */
    private Byte type;
    /**
     * 证件号
     */
    private String idNum;
}
