package com.cmh.minio.controller;

import javax.servlet.http.HttpServletResponse;

import com.cmh.minio.config.MinIoProperties;
import com.cmh.minio.utils.MinIoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * MinIO测试接口
 * </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2020/8/16 20:39
 * 源码博客：
 * https://blog.csdn.net/qq_38225558/article/details/108047537
 * Minio文档链接：
 * https://docs.min.io/docs/
 */
@RestController
@RequestMapping("/api/minio")
@Api(tags = {"MinIO测试接口"})
public class MinIoController {

    @Autowired
    MinIoProperties minIoProperties;

    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("hello!");
        String fileUrl = MinIoUtil.upload(minIoProperties.getBucketName(), file);
        return "文件下载地址：" + fileUrl;
    }

    @ApiOperation(value = "下载文件")
    @GetMapping(value = "/download")
    public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        MinIoUtil.download(minIoProperties.getBucketName(), fileName, response);
    }

    @ApiOperation(value = "删除文件")
    @GetMapping(value = "/delete")
    public String delete(@RequestParam("fileName") String fileName) {
        MinIoUtil.deleteFile(minIoProperties.getBucketName(), fileName);
        return "删除成功";
    }

}