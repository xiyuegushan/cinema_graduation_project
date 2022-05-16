import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from "@/views/Register";
import Home from "@/views/Home";
import Movie from "@/views/Movie";
import UserCenter from "@/views/UserCenter";
import OrderManage from "@/views/OrderManage";
import MovieSearch from "@/views/MovieSearch";
import MovieScreening from "@/views/MovieScreening";

Vue.use(VueRouter)
const routes = [
    {
        path: '/',
        name: 'Index',
        meta: {
            requireAuth: true
        },
        redirect: {name: "Login"}
    }, {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: '/home',
        name: 'Home',
        component: Home
    },
    {
        path: '/movie',
        name: 'Movie',
        component: Movie
    },
    {
        path: '/user/center',
        name: 'UserCenter',
        component: UserCenter
    },
    {
        path: '/order/manage',
        name: 'OrderManage',
        component: OrderManage
    },
    {
        path: '/movie/search',
        name: 'MovieSearch',
        component: MovieSearch
    },
    {
        path: '/movie/screening',
        name: 'MovieScreening',
        component: MovieScreening
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition
        } else {
            return {x: 0, y: 0}
        }
    },
})

export default router

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
