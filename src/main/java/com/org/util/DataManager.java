package com.org.util;

import java.io.*;
import java.util.Properties;

public class DataManager {
    FileInputStream fis;
    InputStream input;
    OutputStream output;
    String filePath=System.getProperty("user.dir")+"/src/test/resources/Data.properties";
    Properties prop = new Properties();
    File DataFile=new File(filePath);
    public String getDataProperty(String key){
        try {
            prop.load(new FileReader(DataFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
    public void setDataProperty(String key,String value){
        try {
            prop.load(new FileReader(DataFile));
            prop.setProperty(key, value);
            prop.store(new FileWriter(DataFile),"Storing in Data properties Key:"+key+" and Value"+value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
