package com.example.uploader.service.impl;

import com.example.uploader.model.FileChunk;
import com.example.uploader.model.FileInfo;
import com.example.uploader.service.StorageFile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

/**
 * @author nurhier
 * @date 2019/12/5
 */
@Component(value = "udfsStorageFile")
public class UdfsStorageFile implements StorageFile {
    private final static String FILE_EXTEND_NAME = ".tmp";

    @Override
    public FileInfo storeFileChunk(String rootPath, FileChunk fileChunk) throws IOException {
        if (fileChunk == null || fileChunk.getIdentifier() == null || fileChunk.getChunkNumber() == null
                || fileChunk.getFile() == null) {
            return null;
        }
        // TODO upload file chunk to udfs
        FileInfo fileInfo = new FileInfo();
        fileInfo.setTotalChunks(fileChunk.getTotalChunks());
        return fileInfo;
    }

    @Override
    public void mergeFileChunk(String rootPath, FileInfo fileInfo) throws IOException {
        // TODO download file chunk from udfs
    }

    @Override
    public boolean deleteFile(String rootPath, FileInfo fileInfo) {
        return false;
    }
}
