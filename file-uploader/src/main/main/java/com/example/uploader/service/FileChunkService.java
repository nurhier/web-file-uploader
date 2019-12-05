package com.example.uploader.service;

import com.example.uploader.model.FileChunk;
import com.example.uploader.model.FileInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * @author nurhier
 * @date 2019/12/3
 */
public interface FileChunkService {
    /**
     * 文件分片是否已存在
     *
     * @param fileChunk fileChunk
     * @return http status
     */
    HttpStatus existFileChunk(FileChunk fileChunk);

    /**
     * saveFileChunk
     *
     * @param filePath  file path
     * @param fileChunk fileChunk
     * @return file info
     * @throws IOException io exception
     * @date 2019/12/5 11:54
     */
    FileInfo saveFileChunk(String filePath, FileChunk fileChunk) throws IOException;

    /**
     * mergeFileChunk
     *
     * @param filePath  filePath
     * @param fileInfo fileInfo
     * @throws IOException io exception
     * @date 2019/12/5 16:56
     */
    void mergeFileChunk(String filePath, FileInfo fileInfo) throws IOException;

    /**
     * deleteFile
     *
     * @param filePath filePath
     * @param fileInfo fileInfo
     * @date 2019/12/5 16:59
     */
    void deleteFile(String filePath, FileInfo fileInfo);
}
