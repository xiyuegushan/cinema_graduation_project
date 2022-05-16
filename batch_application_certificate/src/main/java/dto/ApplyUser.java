package dto;

import lombok.Data;

/**
 * 申请证书用户信息
 *
 * @author caizp
 * @date 2021-12-28 15:53:02
 */
@Data
public class ApplyUser {

    /**
     * 姓名
     */
    private String name;

    /**
     * 证件号
     */
    private String idNum;

    /**
     * 手机号
     */
    private String phone;
}
