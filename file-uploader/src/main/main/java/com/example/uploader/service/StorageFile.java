package com.example.uploader.service;

import com.example.uploader.model.FileChunk;
import com.example.uploader.model.FileInfo;

import java.io.IOException;

/**
 * @author nurhier
 * @date 2019/12/5
 */
public interface StorageFile {

    /**
     * storeFileChunk
     *
     * @param rootPath  rootPath
     * @param fileChunk fileChunk
     * @return com.example.uploader.model.FileInfo
     * @throws IOException io
     * @date 2019/12/5 15:46
     */
    FileInfo storeFileChunk(String rootPath, FileChunk fileChunk) throws IOException;

    /**
     * mergeFileChunk
     *
     * @param rootPath rootPath
     * @param fileInfo fileInfo
     * @throws IOException io
     * @date 2019/12/5 15:46
     */
    void mergeFileChunk(String rootPath, FileInfo fileInfo) throws IOException;

    /**
     * deleteFile
     *
     * @param rootPath rootPath
     * @param fileInfo fileInfo
     * @return boolean
     * @date 2019/12/5 16:22
     */
    boolean deleteFile(String rootPath, FileInfo fileInfo);

}
