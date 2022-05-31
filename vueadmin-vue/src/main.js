import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import Element from "element-ui"
import "element-ui/lib/theme-chalk/index.css"

import axois from "./axios"
import global from './globalFun'

Vue.prototype.$axios = axois  //

Vue.config.productionTip = false

// require("./mock") //引入mock数据，关闭则注释该行

Vue.use(Element)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
