<template>
  <div class="app-iframe-container">
    <el-form :model="vehicle" ref="vehicle" label-width="120px" size="small" :rules="rules">
      <el-card :body-style="{ padding: '20px'}" style="margin-bottom: 20px;" shadow="hover">
        <div slot="header" class="clearfix">
          <span>文件上传</span>
        </div>
        <div>
          <uploader class="uploader-example"
                    :options="options"
                    :file-status-text="fileStatusText"
                    @file-success="onFileSuccess"
                    @file-progress="onFileProgress"
                    @file-error="onFileError"
                    @file-removed="onFileRemoved">
            <uploader-unsupport></uploader-unsupport>
            <uploader-drop>
              <p>选择上传文件</p>
              <uploader-btn>选择文件</uploader-btn>
              <uploader-btn :attrs="attrs">选择图片</uploader-btn>
            </uploader-drop>
            <uploader-list></uploader-list>
          </uploader>
        </div>
      </el-card>
    </el-form>
  </div>
</template>
<script>
  import {fileApi} from '../api/api'

  export default {
    data () {
      return {
        rules: {},
        vehicle: {
          startId: null,
          endId: null,
          frameNo: null,
          modifyTime: []
        },
        options: {
          target: '//localhost:8077/api/uploader/chunk',
          chunkSize: 2 * 1024 * 1024,
          forceChunkSize: true,
          testChunks: true,
          parseTimeRemaining: function (timeRemaining, parsedTimeRemaining) {
            return parsedTimeRemaining
              .replace(/\syears?/, '年')
              .replace(/\days?/, '天')
              .replace(/\shours?/, '小时')
              .replace(/\sminutes?/, '分钟')
              .replace(/\sseconds?/, '秒')
          }
        },
        attrs: {
          accept: 'image/*'
        }
      }
    },
    methods: {
      fileStatusText (status, response) {
        const statusTextMap = {
          success: '上传成功',
          error: '上传失败',
          uploading: '正在上传',
          paused: '暂停上传',
          waiting: '等待上传'
        }
        return statusTextMap[status]
      },
      onFileSuccess (rootFile, file, res, chunk) {
        let fileInfo = {
          fileName: file.name,
          fileType: file.fileType,
          identifier: file.uniqueIdentifier,
          totalChunks: JSON.parse(res).data.totalChunks
        }
        fileApi.fileMerge(fileInfo).then(data => {
          this.$message.success('上传成功')
        })
      },
      onFileProgress (rootFile, file, chunk) {
        console.log(`上传中 ${file.name}，chunk：${chunk.startByte / 1024 / 1024} ~ ${chunk.endByte / 1024 / 1024}`)
      },
      onFileError (rootFile, file, response, chunk) {
        this.$message.error(response.msg)
      },
      onFileRemoved (file) {
        const fileInfo = {
          fileName: file.name,
          fileType: file.fileType,
          identifier: file.uniqueIdentifier
        }
        fileApi.cancelUpload(fileInfo).then(() => {
          this.$message.success('删除成功')
        })
      }
    },
    mounted () {
    }
  }
</script>
<style>
  .uploader-example {
    width: 880px;
    padding: 15px;
    margin: 40px auto 0;
    font-size: 12px;
    box-shadow: 0 0 10px rgba(0, 0, 0, .4);
  }

  .uploader-example .uploader-btn {
    margin-right: 4px;
  }

  .uploader-example .uploader-list {
    max-height: 440px;
    overflow: auto;
    overflow-x: hidden;
    overflow-y: auto;
  }
</style>
