package com.bugu.bgcommunity.common.utils;

import cn.hutool.core.util.IdUtil;
import com.bugu.bgcommunity.enums.ResultEnum;
import com.bugu.bgcommunity.exception.BuguException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片上传工具类
 * Created by mcbbugu
 * 2019-11-24 11:35
 */
@Slf4j
public class ImgUtil {
    public static String upload(MultipartFile file, String uploadPath, String accessPath){
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf('.'));
        if(!fileSuffix.equals(".jpg") && !fileSuffix.equals(".jpeg") && !fileSuffix.equals(".png") && !fileSuffix.equals(".gif")){
            log.info("请上传图片");
            throw new BuguException(ResultEnum.not_img);
        }
        //唯一id
        String uuid = IdUtil.randomUUID();
        File newFile = new File(uploadPath + uuid + fileSuffix);
        try {
            FileUtils.writeByteArrayToFile(newFile, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessPath + uuid + fileSuffix;
    }
}