package com.example.uploader.service.impl;

import com.example.uploader.dao.FileChunkDao;
import com.example.uploader.model.FileChunk;
import com.example.uploader.model.FileInfo;
import com.example.uploader.service.FileChunkService;
import com.example.uploader.service.StorageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author nurhier
 * @date 2019/12/3
 */
@Service
public class FileChunkServiceImpl implements FileChunkService {

    private FileChunkDao fileChunkDao;

    @Autowired
    public void setFileChunkDao(FileChunkDao fileChunkDao) {
        this.fileChunkDao = fileChunkDao;
    }

    private StorageFile storageFile;

    @Autowired
    public void setStorageFile(@Qualifier("localStorageFile") StorageFile storageFile) {
        this.storageFile = storageFile;
    }

    @Override
    public HttpStatus existFileChunk(FileChunk fileChunk) {
        FileChunk fileChunkEntity = fileChunkDao.getEntityByKey(fileChunk);
        if (fileChunkEntity != null) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.UPGRADE_REQUIRED;
        }
    }

    @Override
    public FileInfo saveFileChunk(String filePath, FileChunk fileChunk) throws IOException {
        FileInfo fileInfo = storageFile.storeFileChunk(filePath, fileChunk);
        fileChunkDao.insertEntity(fileChunk);
        return fileInfo;
    }

    @Override
    public void mergeFileChunk(String filePath, FileInfo fileInfo) throws IOException {
        storageFile.mergeFileChunk(filePath, fileInfo);
    }

    @Override
    public void deleteFile(String filePath, FileInfo fileInfo) {
        storageFile.deleteFile(filePath, fileInfo);
    }
}
