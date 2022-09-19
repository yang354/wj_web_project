package com.wj.web.model.controller;

import com.wj.web.config.minio.MinioUtil;
import com.wj.web.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @projectName: wj_web_project
 * @package: com.wj.web.model.controller
 * @className: FileController
 * @author: JaHero
 * @description: 文件管理
 * @date: 2022/9/18 18:07
 * @version: 1.0
 */
@Api(value = "文件管理", tags = "文件管理", description = "文件管理")
@RestController
@RequestMapping("/api/File")
public class FileController {
    @Autowired
    private MinioUtil minioUtil;

    @ApiOperation("上传一个文件")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "文件", name = "uploadfile", dataType = "MultipartFile"),
            @ApiImplicitParam(value = "桶名", name = "bucket", dataType = "String"),
            @ApiImplicitParam(value = "文件名", name = "objectName", dataType = "String"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @PostMapping(value = "/upload")
    public Result uploadfile(@RequestParam MultipartFile uploadfile, @RequestParam String bucket,
                             @RequestParam(required = false) String objectName) throws Exception {
        minioUtil.createBucket(bucket);
        if (objectName != null) {
            minioUtil.uploadFile(uploadfile.getInputStream(), bucket, objectName + "/" + uploadfile.getOriginalFilename());
        } else {
            minioUtil.uploadFile(uploadfile.getInputStream(), bucket, uploadfile.getOriginalFilename());
        }
        return Result.ok();
    }

    @ApiOperation("列出所有的桶")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @GetMapping(value = "/listBuckets")
    public Result listBuckets() throws Exception {
        return Result.ok(minioUtil.listBuckets());
    }

    @ApiOperation("递归列出一个桶中的所有文件和目录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "桶名", name = "bucket", dataType = "String"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @GetMapping(value = "/listFiles")
    public Result listFiles(@RequestParam String bucket) throws Exception {
        return Result.ok(minioUtil.listFiles(bucket));
    }

    @ApiOperation("下载一个文件")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "桶名", name = "bucket", dataType = "String"),
            @ApiImplicitParam(value = "文件名", name = "objectName", dataType = "String"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @PostMapping(value = "/downloadFile")
    public void downloadFile(@RequestParam String bucket, @RequestParam String objectName,
                             HttpServletResponse response) throws Exception {
        InputStream stream = minioUtil.download(bucket, objectName);
        ServletOutputStream output = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(objectName.substring(objectName.lastIndexOf("/") + 1), "UTF-8"));
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(stream, output);
    }


    @ApiOperation("删除一个文件")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "桶名", name = "bucket", dataType = "String"),
            @ApiImplicitParam(value = "文件名", name = "objectName", dataType = "String"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @DeleteMapping(value = "/deleteFile")
    public Result deleteFile(@RequestParam String bucket, @RequestParam String objectName) throws Exception {
        minioUtil.deleteObject(bucket, objectName);
        return Result.ok();
    }

    @ApiOperation("删除一个桶")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "桶名", name = "bucket", dataType = "String"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @DeleteMapping(value = "/deleteBucket")
    public Result deleteBucket(@RequestParam String bucket) throws Exception {
        minioUtil.deleteBucket(bucket);
        return Result.ok();
    }


    @ApiOperation("复制一个文件")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "源桶名", name = "sourceBucket", dataType = "String"),
            @ApiImplicitParam(value = "目标桶名", name = "targetBucket", dataType = "String"),
            @ApiImplicitParam(value = "源文件对象名", name = "sourceObject", dataType = "String"),
            @ApiImplicitParam(value = "目标文件对象名", name = "targetObject", dataType = "String"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @GetMapping("/copyObject")
    public Result copyObject(@RequestParam String sourceBucket, @RequestParam String sourceObject, @RequestParam String targetBucket, @RequestParam String targetObject) throws Exception {
        minioUtil.copyObject(sourceBucket, sourceObject, targetBucket, targetObject);
        return Result.ok();
    }

    @ApiOperation("获取文件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "桶名", name = "bucket", dataType = "String"),
            @ApiImplicitParam(value = "文件名", name = "objectName", dataType = "String"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @GetMapping("/getObjectInfo")
    public Result getObjectInfo(@RequestParam String bucket, @RequestParam String objectName) throws Exception {
        return Result.ok(minioUtil.getObjectInfo(bucket, objectName));
    }

//    @ApiOperation("获取一个连接以供下载")
//    @ApiImplicitParams({
//            @ApiImplicitParam(value = "桶名",name = "bucket",dataType = "String"),
//            @ApiImplicitParam(value = "文件名",name = "objectName",dataType = "String"),
//            @ApiImplicitParam(paramType = "header", name = "token", required = false)
//    })
//    @GetMapping("/getPresignedObjectUrl")
//    public Result getPresignedObjectUrl(@RequestParam String bucket, @RequestParam String objectName, @RequestParam Integer expires) throws Exception {
//        return Result.ok(minioUtil.getPresignedObjectUrl(bucket, objectName, expires));
//    }

    @ApiOperation("获取minio中所有的文件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @GetMapping("/listAllFile")
    public Result listAllFile() throws Exception {

        return Result.ok(minioUtil.listAllFile());
    }

}

