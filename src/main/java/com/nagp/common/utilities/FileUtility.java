package com.nagp.common.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtility extends ZipUtils {

    /**
     * Function Decription:- Function recursively copy all the sub folder and files from sourceFolder to destinationFolder
     * Created by - Arjit Kathuria
     */

    public void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
        if (sourceFolder.exists()) {
            if (sourceFolder.isDirectory()) {
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdir();
                }
                String files[] = sourceFolder.list();
                for (String file : files) {
                    if (!file.equalsIgnoreCase("archive")) {
                        File srcFile = new File(sourceFolder, file);
                        File destFile = new File(destinationFolder, file);
                        copyFolder(srcFile, destFile);
                    }
                }
            } else {
                Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    public void deleteFileFolder(File file) {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteFileFolder(f);
            }
        }
        file.delete();
    }
}
