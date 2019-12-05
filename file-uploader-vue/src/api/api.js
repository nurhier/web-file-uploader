import {request} from '../util/request'

/**
 * 后台管理API
 */
export const fileApi = {
  fileMerge: (file) => request(`/uploader/merge`, file, 'post'),
  cancelUpload: (file) => request(`/uploader/merge`, file, 'post')
}
