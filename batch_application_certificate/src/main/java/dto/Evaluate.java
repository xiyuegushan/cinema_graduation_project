package dto;

import lombok.Data;

/**
 * @author Liangzhaohao
 * @date 2022/5/6 16:14
 */
@Data
public class Evaluate {
    private String jobIdent;
    private String busiId;
    private String userCode;
    private String satisfactionLevel;
    private String satisfactionTimely;
    private String satisfactionDescription;
}
