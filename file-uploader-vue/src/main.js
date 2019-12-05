import 'babel-polyfill'

import Vue from 'vue'
import App from './App'
import Vuex from 'vuex'
import store from './vuex/store'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './js/iconfont.js'
import uploader from 'vue-simple-uploader'

Vue.use(Vuex)
Vue.use(ElementUI)
Vue.use(uploader)

axios.defaults.baseURL = '/api'
Vue.prototype.$ajax = axios

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
