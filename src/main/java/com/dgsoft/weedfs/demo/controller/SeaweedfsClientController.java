package com.dgsoft.weedfs.demo.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgsoft.weedfs.demo.entity.WeedfsFileEntity;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@Component
@RequestMapping("/weedfs")
public class SeaweedfsClientController {
    @Value("${seaweedfs.postUrl}")
    private  String postUrl;
    //上傳文件
    public synchronized  WeedfsFileEntity uploadFile(String filePath,String fieleName ) {
        CloseableHttpClient http =null;
        CloseableHttpResponse response=null;
        try {
                http = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(this.postUrl);
                httpPost.setHeader(new BasicHeader("Accept-Language", "zh-cn"));
                File file = new File(filePath);
                org.apache.http.HttpEntity reqEntity = org.apache.http.entity.mime.MultipartEntityBuilder.create()
                        .setCharset(Charset.forName("UTF-8")).setMode(org.apache.http.entity.mime.HttpMultipartMode.BROWSER_COMPATIBLE)
                        .addBinaryBody(URLEncoder.encode(fieleName), file).build();
                httpPost.setEntity(reqEntity);
                response = http.execute(httpPost);
                ObjectMapper mapper = new ObjectMapper();
                WeedfsFileEntity weedfile = mapper.readValue(response.getEntity().getContent(),WeedfsFileEntity.class);
                return weedfile;
            }catch (Exception e){
                e.printStackTrace();
                return null;
        }finally {
            try { 
                if(response != null){  response.close();}
            } catch (IOException e1) {
                     e1.printStackTrace();
            }
            try {
                if(http != null){ http.close();}
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
    
    @RequestMapping("/upload")
    public String upload() {
        WeedfsFileEntity entity=this.uploadFile("d:/test.jpg", "test.jpg");
        return entity.toString();
    }
}
