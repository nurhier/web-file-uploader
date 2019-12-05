package com.example.uploader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author nurhier
 * @date 2019/12/2
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileInfo {
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件标识
     */
    private String identifier;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 总块数
     */
    private Integer totalChunks;
}
