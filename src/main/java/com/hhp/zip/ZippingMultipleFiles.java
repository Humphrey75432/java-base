package com.hhp.zip;

import lombok.SneakyThrows;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZippingMultipleFiles {

    public static void main(String[] args) {
        String fileName1 = "/Users/huhaiping/IdeaProjects/java-base/file1.txt";
        String fileName2 = "/Users/huhaiping/IdeaProjects/java-base/file2.txt";

        String zipFileName = "/Users/huhaiping/IdeaProjects/java-base/after-zipfile.zip";
        File zipFile = new File(zipFileName);

        try {
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zipFile(fileName1, zos);
            zipFile(fileName2, zos);
            zos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private static void zipFile(String fileName, ZipOutputStream zos) {
        final int BUFFER = 1024;
        BufferedInputStream bis = null;
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis, BUFFER);

            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);
            byte[] data = new byte[BUFFER];
            int count;
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                zos.write(data, 0, count);
            }
            zos.closeEntry();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert bis != null;
            bis.close();
        }
    }
}
