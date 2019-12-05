import 'babel-polyfill'

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  // 全屏 loading 动画
  fullLoading: false,
  // 当前登录人
  user: {
    name: 'WiseWrong',
    deptName: 'DXN'
  }
}

const store = new Vuex.Store({
  state
})

export default store
