package com.example.uploader.dao;


import org.apache.ibatis.annotations.Mapper;
import com.example.uploader.model.FileChunk;
import org.springframework.stereotype.Repository;

/**
 * @author nurhier
 * @date 2019/12/3
 */
@Repository
@Mapper
public interface FileChunkDao {
    /**
     * getEntity
     *
     * @param id id
     * @return org.example.uploader.model.FileChunk
     * @date 2019/12/4 18:04
     */
    FileChunk getEntity(Long id);

    /**
     * getEntityByKey
     *
     * @param fileChunk fileChunk
     * @return com.example.uploader.model.FileChunk
     * @date 2019/12/5 15:19
     */
    FileChunk getEntityByKey(FileChunk fileChunk);

    /**
     * insertEntity
     *
     * @param fileChunk fileChunk
     * @return java.lang.Long
     * @date 2019/12/5 15:03
     */
    Long insertEntity(FileChunk fileChunk);
}
