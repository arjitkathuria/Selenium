package com.nagp.common.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties prop = null;
    public static File file = null;
    public static FileInputStream fis = null;

    /**
     * Function Decription:- Function used to read data from config file.
     * Created by - Arjit Kathuria
     */

    public ConfigReader() {
        {
            file = new File(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            prop = new Properties();
            try {
                prop.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

}
