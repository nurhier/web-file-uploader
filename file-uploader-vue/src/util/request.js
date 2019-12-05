import axios from 'axios'
import qs from 'qs'

const baseUrl = 'http://localhost:8077/api'
export const request = (url, body, type = 'get', isJson = true, baseUrlRewrite) => {
  const query = {
    url: `${baseUrlRewrite || baseUrl}${url}`,
    method: type,
    withCredentials: true,
    timeout: 30000
  }
  if (type === 'get') {
    query.params = body
  } else {
    query.data = isJson ? body : qs.stringify(body)
    query.headers = {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  }
  return axios.request(query)
    .then(res => {
      return new Promise((resolve, reject) => {
        if (!res.data) {
          reject(new Error('服务器响应超时'))
          return
        }
        if (res.data.status === 1) {
          resolve(res.data.data)
        } else {
          res.data.message = res.data.msg
          reject(res.data)
        }
      })
    }, e => {
      return Promise.reject(e.response)
    }).catch(e => {
      return Promise.reject(e)
    })
}
