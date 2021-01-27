package com.java;

import java.io.File;

import org.apache.commons.io.FileUtils;
import utils.FileSplitUtils;

/**
 * 文件切割程序
 * 
 * @author wcj
 * @createDate 2021年1月26日10:07:43
 * @version 1.0.0
 *
 */
public class FileSplit {

    public static void main(String[] args) {
        try {
            // 切割文件
            File srcFile = FileUtils.getFile("C:\\Users\\Administrator\\Desktop\\logs.log");
            String chipsDir = "E:\\日志切割";
            FileSplitUtils.split(srcFile, chipsDir);

            // 聚合文件
//            File destFile = FileUtils.getFile("G:\\MongoDB2.zip");
//            FileSplitUtils.combinateFormChips(chipsDir, destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}