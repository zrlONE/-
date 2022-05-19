package com.zrl.reggie.controller;

import com.zrl.reggie.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: zrl
 * @date: 2022/5/10 18:26
 * @description:
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${reggie.basePath}")
    private String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        //file是一个临时文件，本次请求完成就会删除，所以需要转储到其他位置

        String originalFilename = file.getOriginalFilename();

        //获得后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //得到新的文件名
        String filename = UUID.randomUUID().toString()+suffix;

        //判断上传目录是否存在
        File dir = new File(basePath);
        if (!dir.exists()){
            dir.mkdirs();
        }

        //将临时文件转储到指定位置
        try {
            file.transferTo(new File(basePath+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(filename);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
            //通过输入流读取内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath+name));
            //通过输出流将文件写会浏览器，在浏览器中预览图片
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");

            int len=0;
            byte[] bytes = new byte[1024];
            while((len=fileInputStream.read(bytes))!=-1){
                    outputStream.write(bytes,0,len);
                    outputStream.flush();
                }
            //关闭资源
            outputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
