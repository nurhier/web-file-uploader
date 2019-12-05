package com.example.uploader.controller;

import com.example.uploader.util.ResponseUtil;
import com.example.uploader.model.FileChunk;
import com.example.uploader.model.FileInfo;
import com.example.uploader.service.FileChunkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nurhier
 * @date 2019/12/2
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class FileUploadController {
    private FileChunkService fileChunkService;

    @Autowired
    public void setFileChunkService(FileChunkService fileChunkService) {
        this.fileChunkService = fileChunkService;
    }

    @Value("${upload.file.path}")
    private String filePath;

    /**
     * 断点续传校验
     *
     * @param fileChunk 文件块
     * @return http result
     */
    @GetMapping(value = "/uploader/chunk")
    public ResponseEntity<?> checkUploadChunk(FileChunk fileChunk) {
        try {
            return ResponseUtil.response(fileChunkService.existFileChunk(fileChunk));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error();
        }
    }

    @PostMapping(value = "/uploader/chunk")
    public ResponseEntity<?> uploadChunk(FileChunk fileChunk) {
        try {
            FileInfo fileInfo = fileChunkService.saveFileChunk(filePath, fileChunk);
            return ResponseUtil.success(fileInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error();
        }
    }

    @PostMapping(value = "/uploader/merge")
    public ResponseEntity<?> mergeFileChunk(@RequestBody FileInfo fileInfo) {
        try {
            fileChunkService.mergeFileChunk(filePath, fileInfo);
            return ResponseUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error();
        }
    }

    @PostMapping(value = "/uploader/cancel")
    public ResponseEntity<?> cancelUploadFile(@RequestBody FileInfo fileInfo) {
        if (log.isDebugEnabled()) {
            log.debug("cancel upload, file: {}", fileInfo);
        }
        try {
            fileChunkService.deleteFile(filePath, fileInfo);
            return ResponseUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error();
        }
    }
}
