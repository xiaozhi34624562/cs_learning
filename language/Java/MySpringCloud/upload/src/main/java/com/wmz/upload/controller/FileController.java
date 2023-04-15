package com.wmz.upload.controller;

import com.wmz.upload.config.FileDfsUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class FileController {

    @Resource
    private FileDfsUtil fileDfsUtil;

    @RequestMapping(value = "/uploadFile",
            headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String result = "";
        try {
            String path = fileDfsUtil.upload(multipartFile);
            if (!StringUtils.isEmpty(path)) {
                result = path;
            } else {
                result = "上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "服务异常";
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
    public ResponseEntity<String> deleteByPath(String filePath) {
        fileDfsUtil.deleteFile(filePath);
        return ResponseEntity.ok("删除成功！");
    }
}
