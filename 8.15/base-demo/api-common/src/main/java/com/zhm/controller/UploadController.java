package com.zhm.controller;

import com.zhm.model.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhm on 17-3-23.
 */
@Api(value = "/", tags="文件上传接口")
@RestController
public class UploadController extends BaseController{
    @Value("${file.upload.path}")
    private String uploadPath;
    @ApiOperation(value = "文件上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public CommonResponse upload(@RequestParam("file") MultipartFile file){
        try {
            File destPath = new File(uploadPath);
            if(!destPath.exists()){
                destPath.mkdirs();
            }
            String extName = extractExtensionFromContentType(file.getOriginalFilename());
            file.transferTo(new File(uploadPath+System.currentTimeMillis()+"."+extName));
        } catch (IOException e) {
            e.printStackTrace();
            return buildErrorResponse("/upload","上传失败！"+e.getMessage());
        }
        return buildSuccessResponse("/upload","上传成功！");
    }

}
