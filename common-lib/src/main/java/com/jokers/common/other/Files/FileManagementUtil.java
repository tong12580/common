package com.jokers.common.other.Files;

import com.jokers.common.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * <p>FileManagementUtil class.</p>
 *
 * @author yuton
 * @version 1.0
 * 文件管理
 * @since 2016/11/30 14:05
 */
@Slf4j
@Async
public class FileManagementUtil {

    /**
     * 文件下载
     *
     * @param response HttpServletResponse
     * @param file     File
     */
    public static void exportFile(HttpServletResponse response, File file) {
        getFileName(response, file);
        try (ServletOutputStream out = response.getOutputStream();
             InputStream in = new FileInputStream(file.getPath())) {
            byte[] buffer = new byte[1024];
            int i;
            while ((i = in.read(buffer)) != -1) {
                out.write(buffer, 0, i);
            }
        } catch (Exception e) {
            log.error("文件下载异常", e);
        }
    }

    private static void getFileName(HttpServletResponse response, File file) {
        String filename = null;
        try {
            filename = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException ignore) {
        }
        response.setContentType(HttpUtil.CONTENT_TYPE_APPLICATION_OCTET_STREAM);
        response.setContentLength((int) file.length());
        response.setHeader(HttpUtil.CONTENT_DISPOSITION, "attachment;filename=" + filename);
        response.setHeader(HttpUtil.LOCATION, filename);
    }


    /**
     * 文件下载 nio 大缓存
     *
     * @param response HttpServletResponse
     * @param file     File
     */
    public static void exportFileByNIO(HttpServletResponse response, File file) {
        getFileName(response, file);
        int bufferSize = 1024 * 128;
        try (ServletOutputStream op = response.getOutputStream();
             FileInputStream fileInputStream = new FileInputStream(file.getPath())) {
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 128);
            byte[] buffer = new byte[bufferSize];
            int nRead, nGet;
            while ((nRead = fileChannel.read(bb)) != -1) {
                if (nRead == 0) {
                    continue;
                }
                bb.position(0);
                bb.limit(nRead);
                while (bb.hasRemaining()) {
                    nGet = Math.min(bb.remaining(), bufferSize);
                    bb.get(buffer, 0, nGet);
                    op.write(buffer);
                }
                bb.clear();
            }
        } catch (Exception e) {
            log.error("文件下载异常", e);
        }
    }

    /**
     * 文件写入
     *
     * @param template     template
     * @param templatePath templatePath
     * @throws java.io.IOException java.io.IOException
     */
    public void createXls(String template, String templatePath) throws IOException {
        byte[] buffer = template.getBytes();
        FileOutputStream out = new FileOutputStream(templatePath);
        out.write(buffer, 0, buffer.length);
        out.close();
    }
}
