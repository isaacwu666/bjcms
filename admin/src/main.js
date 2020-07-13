import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';
import VueI18n from 'vue-i18n';
import { messages } from './components/common/i18n';
import axios from 'axios';
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
// import './assets/css/theme-green/index.css'; // 浅绿色主题
import './assets/css/icon.css';
// import './components/common/directives';
import 'babel-polyfill';


// import { Loading } from 'element-ui';

Vue.config.productionTip = false;
Vue.use(VueI18n);
// axios.defaults.baseURL="http://localhost:8010";

var token = localStorage.getItem("token")||null;
// console.error(token)
axios.defaults.headers.common['token'] = localStorage.getItem("token")||null

Vue.prototype.$axios=axios
// Vue.prototype.$loading=Loading
Vue.use(ElementUI, {
  size: 'small'
});
const i18n = new VueI18n({
  locale: 'zh',
  messages
});

// //使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
  document.title = `${to.meta.title} | 百家号@爱旅游爱工作`;
  const token = localStorage.getItem('token');
  if (!token && to.path !== '/login') {
    next('/login');
  } else if (to.meta.permission) {
    // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
    // token === 'admin' ? next() : next('/403');
  } else {
    // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
    if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
      Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
        confirmButtonText: '确定'
      });
    } else {
      next();
    }
  }
});

axios.interceptors.response.use(function (response) {
  if (response.data.code=="LOGIN_ERROR") {
    // console.log("")
    router.push("/login");
    return response;
  }

  return response
}), function (error) {
  // 捕获异常状态码
  return new Promise(function () { })
}

new Vue({
  router,
  i18n,
  render: h => h(App)
}).$mount('#app');
