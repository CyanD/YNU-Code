package com.cfg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * 解析properties
 * @author dql
 *
 */
public class Configuration {	
    private Properties props;
    private FileInputStream ips;
    Logger log=Logger.getLogger(Configuration.class);
    public static final URL findAsResource(String path) {
        URL url = null;

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            url = contextClassLoader.getResource(path);
        }
        if (url != null) {
            return url;
        }

        url = Configuration.class.getClassLoader().getResource(path);
        if (url != null) {
            return url;
        }

        url = ClassLoader.getSystemClassLoader().getResource(path);

        return url;
    }
    public Configuration(){
        props=new Properties();
    }
    public Configuration(String filepath){//加载属性配置文件
        props=new Properties();
        try{
            ips=new FileInputStream(filepath);
            props.load(ips);            
            ips.close();
        }catch(FileNotFoundException e){
            log.info(filepath+",file doesn't exist!");
            e.printStackTrace();
        }catch(IOException e){
            log.info(filepath+",load the file failure");
            e.printStackTrace();
        }
    }
    /**
     * 通过属性名获取属性值
     * @param key 属性名
     * @return
     */
    public String getValue(String key){
        if(props.containsKey(key)){
            String value=props.getProperty(key);
            return value;
        }
        else 
            return "";
        
    }
    /**
     * 在指定的属性文件中获取相关属性名的属性值
     * @param fileName
     * @param key
     * @return
     */
    public String getValue(String fileName,String key){
        try{
            String value=null;
            ips=new FileInputStream(fileName);
            props.load(ips);
            ips.close();
            if(props.containsKey(key)){
                value=props.getProperty(key);
                return value;
            }else
                return value;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    public void clear(){
        props.clear();
    }
}
