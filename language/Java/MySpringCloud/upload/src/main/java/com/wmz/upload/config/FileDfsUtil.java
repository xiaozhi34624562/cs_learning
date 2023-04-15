package com.wmz.upload.config;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FileDfsUtil {
    @Autowired
    private FastFileStorageClient storageClient;

    public String upload(MultipartFile multipartFile) throws Exception {
        String filename = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        InputStream inputStream = multipartFile.getInputStream();
        long size = multipartFile.getSize();
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(), multipartFile.getSize(), filename, null);
        return storePath.getFullPath();
    }

    public void deleteFile(String fileUrl) {
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
    }
}
