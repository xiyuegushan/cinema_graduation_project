import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import dto.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liangzhaohao
 * @date 2022/4/24 11:23
 */
public class BatchApplyCert {

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(Files.newOutputStream(Paths.get("C:\\Users\\Liangzhaohao\\Desktop\\output.log"))));
        int successApplyTotal = 0;
        List<ApplyUser> applyUserList;
        try {
            applyUserList = excel();
            System.out.println("待申请证书的用户信息列表=" + applyUserList);
            for (ApplyUser applyUser : applyUserList) {
                int result = apply("B2Kg5CN5Pw5LA9VJ8aw4XK1ui2La4LP8za4Fz0bv0HE8Zt2Gy0rs2Cz9cB2pv9FJ", applyUser);
                successApplyTotal = successApplyTotal + result;
            }
            System.out.println("批量申请证书成功数量=" + successApplyTotal);
        } catch (Exception e) {
            System.out.println("批量申请证书异常：" + e.getMessage());
        }
//        List<Evaluate> evaluateList = evaluateExcelInput();
//        for (Evaluate evaluate : evaluateList){
//            if(evaluate.getSatisfactionDescription() == null){
//                evaluate.setSatisfactionDescription("");
//            }
//        }
//        System.out.println(JSONUtil.toJsonStr(evaluateList));
    }

    public static List<ApplyUser> excel() throws Exception {
        List<ApplyUser> applyUserList = new ArrayList<>();
        File file = new File("C:\\Users\\Liangzhaohao\\Desktop\\批量申请证书用户信息.xlsx");
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        InputStream inputStream = Files.newInputStream(file.toPath());
        // 读取整个Excel
        XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
        // 获取第一个表单Sheet
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        // 默认第一行为标题行，i = 0
        XSSFRow titleRow = sheetAt.getRow(0);
        // 循环获取每一行数据
        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheetAt.getRow(i);
            // 读取每一格内容
            ApplyUser applyUser = new ApplyUser();
            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                XSSFCell cell = row.getCell(index);
                cell.setCellType(CellType.STRING);
                if ("".equals(cell.getStringCellValue())) {
                    continue;
                }
                if (index == 0) {
                    applyUser.setName(String.valueOf(cell));
                }
                if (index == 1) {
                    applyUser.setIdNum(String.valueOf(cell));
                }
                if (index == 2) {
                    applyUser.setPhone(String.valueOf(cell));
                }
            }
            applyUserList.add(applyUser);
        }
        return applyUserList;
    }

    public static List<Evaluate> evaluateExcelInput() throws Exception {
        List<Evaluate> evaluateList = new ArrayList<>();
        File file = new File("C:\\Users\\Liangzhaohao\\Desktop\\evaluate.xlsx");
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        InputStream inputStream = Files.newInputStream(file.toPath());
        // 读取整个Excel
        XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
        // 获取第一个表单Sheet
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        // 默认第一行为标题行，i = 0
        XSSFRow titleRow = sheetAt.getRow(0);
        // 循环获取每一行数据
        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheetAt.getRow(i);
            // 读取每一格内容
            Evaluate evaluate = new Evaluate();
            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                XSSFCell cell = row.getCell(index);
                cell.setCellType(CellType.STRING);
                if ("".equals(cell.getStringCellValue())) {
                    continue;
                }
                if (index == 0) {
                    evaluate.setJobIdent(String.valueOf(cell));
                }
                if (index == 1) {
                    evaluate.setBusiId(String.valueOf(cell));
                }
                if (index == 2) {
                    evaluate.setUserCode(String.valueOf(cell));
                }
                if (index == 3) {
                    evaluate.setSatisfactionLevel(String.valueOf(cell));
                }
                if (index == 4) {
                    evaluate.setSatisfactionTimely(String.valueOf(cell));
                }
                if (index == 5) {
                    evaluate.setSatisfactionDescription(String.valueOf(cell));
                }
            }
            evaluateList.add(evaluate);
        }
        return evaluateList;
    }

    public static int apply(String token, ApplyUser applyUser) {
        // 设置请求体
        GDCARequest<GDCAApplyDTO> request = new GDCARequest<>();
        request.setTimestamp(System.currentTimeMillis());
        request.setToken(token);
        request.setAppId("citizenSign");
        request.setProjectId("trustSign");
        // 设置证书信息
        GDCAApplyCert gdcaApplyCert = new GDCAApplyCert();
        gdcaApplyCert.setName(applyUser.getName());
        gdcaApplyCert.setIdNum(applyUser.getIdNum());
        gdcaApplyCert.setType(Byte.valueOf("3"));
        // 设置经办人信息
        GDCAApplyAgent gdcaApplyAgent = new GDCAApplyAgent();
        gdcaApplyAgent.setName(applyUser.getName());
        gdcaApplyAgent.setIdNum(applyUser.getIdNum());
        gdcaApplyAgent.setContactPhone(applyUser.getPhone());
        // 设置请求体业务数据
        GDCAApplyDTO gdcaApplyDTO = new GDCAApplyDTO();
        gdcaApplyDTO.setCert(gdcaApplyCert);
        gdcaApplyDTO.setAgent(gdcaApplyAgent);
        request.setDatas(gdcaApplyDTO);
        String json = JSONUtil.toJsonStr(request);
        String url = "https://testservice.95105813.cn/trustSign/cert/applyCertificate";
        System.out.println("apply-请求GDCA申请证书URL：" + url);
        System.out.println("apply-请求GDCA申请证书入参：" + json);
        String result = "";
        try {
            result = HttpRequest.post(url).body(json).execute().body();
        } catch (HttpException e) {
            System.out.println("apply-请求GDCA申请证书异常：" + e.getMessage());
        }
        System.out.println("apply-请求GDCA申请证书结果：" + result);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        // 调用GDCA失败则抛出异常并终止证书的本地DB保存
        if (!"success".equals(jsonObject.getStr("result"))) {
            System.out.println("apply-请求GDCA申请证书失败：：" + jsonObject.getStr("message") + ",request=" + request);
            System.out.println("apply-请求GDCA申请证书失败，失败的用户信息为：" + applyUser);
            return 0;
        }
        System.out.println("apply-请求GDCA申请证书成功，成功的用户信息为：" + applyUser);
        return 1;
    }
}
