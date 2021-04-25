package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * 文件切割工具类
 *
 * @author wcj
 * @createDate 2021年1月26日10:07:43
 * @version 1.0.0
 *
 */
public class FileSplitUtils {

    /**
     * 默认切割后，每个文件大小 100M
     */
    public static final int DEFAULT_SIZE = 1024 * 1024 * 100;

    /**
     * 切割指定源文件，并输出到指定目录，按默认大小切割
     *
     * @param srcFile 指定要切割的源文件
     * @param outputDir 指定输出目录
     * @throws IOException 有异常时抛出，由调用者处理
     */
    public static void split(File srcFile, String outputDir) throws IOException {
        split(srcFile, outputDir, DEFAULT_SIZE);
    }

    /**
     * 切割指定源文件，并输出到指定目录
     *
     * @param srcFile 指定要切割的源文件
     * @param outputDir 指定输出目录
     * @param size 切割大小
     * @throws IOException 有异常时抛出，由调用者处理
     */
    public static void split(File srcFile, String outputDir, int size) throws IOException {
        try  {

            String srcFileStr = srcFile.getName();

            // ["logs_r","log"]
            String[] names = srcFileStr.split("\\.");

            String name = names[0];

            FileInputStream inputStream = FileUtils.openInputStream(srcFile);
            File temp = null;
            byte[] buffer = new byte[size];
            int len = 0;

            // IOUtils.read(inputStream, buffer) 读取完后，再读取则返回值为0
            for (int i = 1; (len = IOUtils.read(inputStream, buffer)) > 0; i++) {
                temp = FileUtils.getFile(outputDir, "\\name" + name + "_" + i +".log");
                FileUtils.writeByteArrayToFile(temp, buffer, 0, len);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 聚形碎片
     *
     * @param inputDir 碎片输入目录
     * @param destFile 聚形目标文件
     * @throws IOException 有异常时抛出，由调用者处理
     */
    public static void combinateFormChips(String inputDir, File destFile) throws IOException {
        File inputPath = FileUtils.getFile(inputDir);
        String[] files = inputPath.list();

        try {
            FileOutputStream outputStream = FileUtils.openOutputStream(destFile);
            // 按文件名排序
            Arrays.sort(files);
            for (String fileName : files) {
                File file = FileUtils.getFile(inputPath, fileName);
                byte[] data = FileUtils.readFileToByteArray(file);

                IOUtils.write(data, outputStream);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}