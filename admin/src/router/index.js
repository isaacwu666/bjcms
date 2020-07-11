import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    base: 'admin',
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/articleList',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/ArticleList.vue'),
            meta: { title: '文章管理' }
        }
        ,{
            path: '/articleDetail',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/ArticleDetail.vue'),
            meta: { title: '文章编辑' }
        }
        ,
        {
            path: '/login',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登陆' }
        }

    ]
});
