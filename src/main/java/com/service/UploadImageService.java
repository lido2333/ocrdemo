package com.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: UploadImageService
 * @description:
 * @author: nping
 * @create: 2020-03-18 15:35
 **/
@Service
public class UploadImageService {

    //记录可以允许的类型
    private static final List<String> suffixes = Arrays.asList("image/png","image/jpeg","image/jpg");

    /**
     * 负责上传图片，并将图片上传后的路径写入数据库中。
     * //目前默认保存在了本地路径，以后有两种选择
     *  1.在云服务器搭建文件路径，写入数据库
     *  2.在本机添加一个配置类用来动态配置文件的传输位置。
     * @param file
     * @return
     */
    public String uploadImage(MultipartFile file) throws IOException {
        //TODO:优化上传文件逻辑
        /**
         * 1.图片信息校验
         *      1)校验文件类型
         *      2)校验图片内容
         * 2.保存图片
         *      1)生成保存目录
         *      2)保存图片
         *      3)拼接图片地址
         */
        //http的content-type来校验文件类型
        String type = file.getContentType();
        if (!suffixes.contains(type)) {
            //类型不匹配
            return null;
        }
        //检验图片的内容
        BufferedImage image = ImageIO.read(file.getInputStream());
        if (image == null) {
            //"上传失败，文件内容不符合要求");
            return null;
        }

        File dir = new File("C:\\uploadImage");
            if (!dir.exists()){
                dir.mkdirs();
            }
            file.transferTo(new File(dir, Objects.requireNonNull(file.getOriginalFilename())));

        return "上传成功！";
    }
}
