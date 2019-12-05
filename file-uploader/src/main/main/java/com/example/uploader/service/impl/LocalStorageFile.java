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
@Component(value = "localStorageFile")
public class LocalStorageFile implements StorageFile {
    private final static String FILE_EXTEND_NAME = ".tmp";

    @Override
    public FileInfo storeFileChunk(String rootPath, FileChunk fileChunk) throws IOException {
        if (fileChunk == null || fileChunk.getIdentifier() == null || fileChunk.getChunkNumber() == null
                || fileChunk.getFile() == null) {
            return null;
        }
        Path folderPath = Paths.get(rootPath, fileChunk.getIdentifier());
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        Files.write(Paths.get(folderPath.toString(), fileChunk.getChunkNumber() + FILE_EXTEND_NAME),
                    fileChunk.getFile().getBytes());
        FileInfo fileInfo = new FileInfo();
        fileInfo.setTotalChunks(fileChunk.getTotalChunks());
        return fileInfo;
    }

    @Override
    public void mergeFileChunk(String rootPath, FileInfo fileInfo) throws IOException {
        Objects.requireNonNull(fileInfo);
        Path folderPath = Paths.get(rootPath, fileInfo.getIdentifier());
        Path filePath = Paths.get(folderPath.toString(), fileInfo.getFileName());
        Files.deleteIfExists(filePath);
        File file = new File(folderPath.toString());
        File[] files = file.listFiles();
        if (files == null || files.length != fileInfo.getTotalChunks()) {
            throw new FileNotFoundException("merge file size is error");
        }
        Path targetFilePath = Files.createFile(filePath);
        for (int i = 1; i <= fileInfo.getTotalChunks(); i++) {
            Path tmpFile = Paths.get(folderPath.toString(), i + FILE_EXTEND_NAME);
            Files.write(targetFilePath, Files.readAllBytes(tmpFile),
                        StandardOpenOption.APPEND);
            Files.delete(tmpFile);
        }
    }

    @Override
    public boolean deleteFile(String rootPath, FileInfo fileInfo) {
        return deleteFile(new File(rootPath, fileInfo.getIdentifier()));
    }

    private static boolean deleteFile(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFile(f);
                }
            }
        }
        return file.delete();
    }
}
