package com.controller;

import com.bean.Word;
import com.service.UploadImageService;
import com.utils.OCRTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: RequestController
 * @description:
 * @author: nping
 * @create: 2020-06-07 22:23
 **/

@Controller
@CrossOrigin
public class RequestController {

    @Autowired
    UploadImageService uploadImageService;



    @RequestMapping("/upload")
    @ResponseBody
    public List<Word> uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        //TODO：还有地方需要完善比如文件不存在等等
        System.out.println(file.getOriginalFilename()+"上传");
        String result = uploadImageService.uploadImage(file);
        if(result== null || result.length() == 0){
            //没有上传成功
            System.out.println("m欸有上传成果");
        }
        //System.out.println("上传成功");
        //解析图片

        return OCRTest.getWordList(file.getOriginalFilename());
    }
}
