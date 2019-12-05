<template>
  <div id="app" v-loading.fullscreen="fullLoading">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {}
  },
  created () {
    document.title = 'The Tenant Management System By Wise'
  },
  watch: {
    // 防止跳过登录直接进系统
    $route (newVal, oldVal) {
      if (!this.$store.state.user.name && newVal.path !== '/logon') {
        this.$router.push('/logon')
        this.$message({
          showClose: true,
          message: '请登录',
          type: 'error'
        })
      }
    }
  },
  methods: {},
  computed: {
    fullLoading () {
      return this.$store.state.fullLoading
    }
  }
}
</script>

<style lang="less">
  /*引入通用样式*/
  @import "css/common.less";
  
  #app {
    min-width: 1300px;
    height: 100%;
  }
</style>
