package com.jokers.common.other.Files;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuton
 * @version 1.0
 * @description 文件NIO流操作
 * @since 2017/1/13 10:51
 */
public class FileUtil {
    /**
     * @param path     {@link String}
     * @param charsets {@link Charset}
     * @return context {@link List<String>} 返回文件內容
     * @throws IOException
     * @description 文件讀取 一次讀取一行 適合打文件讀取
     */
    public static List<String> read(String path, Charset charsets) throws IOException {
        if (null == charsets) {
            charsets = StandardCharsets.UTF_8;
        }
        return Files.lines(Paths.get(path), charsets).collect(Collectors.toList());
    }

    /**
     * @param path     {@link String}
     * @param charsets {@link Charset}
     * @return context {@link String} 返回文件內容
     * @throws IOException
     * @description 文件讀取 一次讀取一行 并轉化為String 且在末尾添加換行符 適合打文件讀取
     */
    public static String readFile(String path, Charset charsets) throws IOException {
        List<String> contentLines = read(path, charsets);
        StringBuilder context = new StringBuilder();
        final String finalNewline = System.getProperty("line.separator");
        contentLines.forEach(contentLine -> context.append(contentLine).append(finalNewline));
        return context.toString();
    }

    /**
     * @param path    {@link String}
     * @param context {@link List<String>}
     * @return path {@link String}
     * @throws IOException
     * @description 文件寫 按行寫文件
     */
    public static String writeByLine(String path, List<String> context) throws IOException {
        Files.write(Paths.get(path), context, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        return path;
    }

    /**
     * @param path    {@link String}
     * @param context {@link Charset}
     * @return path {@link String}
     * @throws IOException
     * @description 文件寫 字節流寫入
     */
    public static String writeByBytes(String path, String context) throws IOException {
        byte[] contentBytes = context.getBytes(StandardCharsets.UTF_8);
        Files.write(Paths.get(path), contentBytes, StandardOpenOption.CREATE);
        return path;
    }
}
