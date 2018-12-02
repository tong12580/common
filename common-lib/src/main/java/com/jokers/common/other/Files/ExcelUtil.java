package com.jokers.common.other.Files;

import com.google.common.collect.Lists;
import com.jokers.common.http.HttpUtil;
import com.jokers.pojo.enums.FileFormatEnum;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Class: Excel
 *
 * @author yutong
 * Description:导出ExceL表
 * @since 2016/01/14 02:00:00
 */
@Slf4j
public class ExcelUtil {

    private static final String CONTENT_DISPOSITION_VALUE = "attachment;filename=";

    /**
     * 生成excel文件(文件标题栏与文件内容一定要对应)
     *
     * @param os    OutputStream
     * @param title (excel文件标题栏) String[]
     * @param lists (excel文件内容)
     * @throws IOException           java.io.IOException
     * @throws RowsExceededException jxl.write.biff.RowsExceededException
     * @throws WriteException        jxl.JXLException.WriteException
     */
    public static void writeExcel(OutputStream os, String[] title, List lists) throws IOException, WriteException {
        // 创建可以写入的Excel工作薄
        WritableWorkbook wwb = Workbook.createWorkbook(os);
        // 生成工作表,(name:First Sheet,参数0表示这是第一页)
        WritableSheet sheet = wwb.createSheet("First Sheet", 0);
        // 开始写入第一行(即标题栏)
        for (int i = 0; i < title.length; i++) {
            // 用于写入文本内容到工作表中去
            // 在Label对象的构造中指明单元格位置(参数依次代表列数、行数、内容 )
            Label label = new Label(i, 0, title[i]);
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
        }
        // 开始写入内容
        for (int row = 0; row < lists.size(); row++) {
            String listvalue = lists.get(row).toString();
            Label label;
            int j = 1;
            if (row > title.length)
                j++;
            label = new Label(row, j, listvalue);
            sheet.addCell(label);
        }
        wwb.write();
        wwb.close();
        os.close();
    }

    /**
     * 读excel
     *
     * @param path 路径
     * @return 集合
     */
    public static List<String> readExcel(String path) {
        Workbook workbook;
        try {
            workbook = Workbook.getWorkbook(new File(path));
        } catch (IOException | BiffException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        Sheet sheet = workbook.getSheet(0);
        log.info("总行数" + sheet.getRows());
        log.info("总列数" + sheet.getColumns());
        List<String> contents = Lists.newArrayList();
        for (int i = 0; i < sheet.getRows(); i++) {
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                contents.add(cell.getContents());
            }
        }
        workbook.close();
        return contents;
    }

    /**
     * 导出并下载Excel文件
     *
     * @param response       http返回
     * @param fileFormatEnum 文件枚举类型
     * @param msg            文件信息
     */
    public static void writeExcel(HttpServletResponse response, String msg, FileFormatEnum fileFormatEnum) throws IOException {
        final String fileName = System.currentTimeMillis() + (null == fileFormatEnum ? FileFormatEnum.EXCEL.getName() : fileFormatEnum.getName());
        response.setContentType(HttpUtil.CONTENT_TYPE_APPLICATION_OCTET_STREAM);
        response.setHeader(HttpUtil.CONTENT_DISPOSITION, CONTENT_DISPOSITION_VALUE);
        response.setHeader(HttpUtil.LOCATION, fileName);
        ServletOutputStream op = response.getOutputStream();
        op.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
        op.write(msg.getBytes(Charset.forName("UTF-8")));
    }
}
