package com.rookie.demo;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by rookie on 2018/1/18.
 */
public class FileDemo {

    /**
     * 操作项目中的txt文件
     */
    public void operateTxt() throws IOException {
        //1.获取文件对象 将文件放置在resources文件夹的files文件夹下 注意路径格式
        File file = ResourceUtils.getFile("classpath:files/rookie.txt");

        //2.读取文件中的内容
        String fileContent = FileUtils.readFileToString(file);
        System.out.println(fileContent);

        //3.向文件中写入内容 注意write的重载方法根据需求选用
        FileUtils.write(file,"这是写入的内容");
    }
}
