import Vue from 'vue'
import Router from 'vue-router'
import home from '../view/home.vue'
import errorPage from '../components/public/404.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/home',
      name: 'home',
      component: home
    },
    {
      path: '*',
      name: '404',
      component: errorPage
    }
  ]
})
