package com.bluesoft.excercise3;

import java.io.File;

public class FilesizeUtils {

    private FilesizeUtils() {

    }

    private static Double fileSize(String filePath) throws NullPointerException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());

        if (!filePath.isEmpty() && file.exists()) {
            return Double.valueOf(file.length());
        }
        else {
            throw new NullPointerException();
        }
    }

    public static Double getFilesizeInMegaBytes(String filePath) {
        return fileSize(filePath) / (1024*1024);
    }

    public static Double getFilesizeInKiloBytes(String filePath) {
        return fileSize(filePath) / 1024;
    }

    public static Double getFilesizeInBytes(String filePath) {
        return fileSize(filePath);
    }
}
