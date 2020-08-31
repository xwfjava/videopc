package com.example.alioos.utils;

import java.io.*;

/**
 * date 2020/8/13
 */
public class FileUtil {

    public static void copyFile(File file, String outputFilePath) throws IOException {
        File outputFile = new File(outputFilePath);
        InputStream inputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        byte[] buffer = new byte[1024];
        int i;
        while ((i = inputStream.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, i);
        }
    }
}
