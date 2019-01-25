package com.dxb.readfileinjar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Resource {
    public void getResource() throws IOException {
        //查找指定资源的URL，其中res.txt仍然开始的bin目录下
//        URL fileURL = this.getClass().getResource("/res.txt");
//        System.out.println(fileURL.getFile());
        //返回读取指定资源的输入流
        InputStream is = this.getClass().getResourceAsStream("/res.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = "";
        while ((s = br.readLine()) != null)
            System.out.println(s);
    }

    public static void main(String[] args) throws IOException {
        Resource res = new Resource();
        res.getResource();
    }
}  