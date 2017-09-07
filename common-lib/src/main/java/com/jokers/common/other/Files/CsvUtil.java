package com.jokers.common.other.Files;

import com.csvreader.CsvReader;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author yuton
 * @version 1.0
 * @description cvs文件处理
 * @since 2017/7/17 10:30
 */
@Slf4j
public class CsvUtil {
    public static List<String> read(String path) {
        List<String> list = Lists.newArrayList();
        try {
            CsvReader csvReader = new CsvReader(path, ',', StandardCharsets.UTF_8);
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                String s = csvReader.getRawRecord();
                list.add(s);
            }
        } catch (FileNotFoundException e) {
            log.error("文件不存在", e);
        } catch (IOException e) {
            log.error("io异常", e);
        }
        return list;
    }
}
