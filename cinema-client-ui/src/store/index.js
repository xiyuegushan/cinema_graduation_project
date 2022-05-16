import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: '',
        userInfo: {
            avatar: '',
            deptId: '',
            userId: '',
            username: ''
        }
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
            localStorage.setItem("token", "Bearer " + token)
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo
            sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
        },
        SET_USERINFO_AVATAR: (state, avatar) => {
            state.userInfo.avatar = avatar
            sessionStorage.setItem("userInfo", JSON.stringify(state.userInfo))
        },
        REMOVE_INFO: (state) => {
            localStorage.setItem("token", '')
            sessionStorage.setItem("userInfo", JSON.stringify(''))
            state.userInfo = {}
        }
    },
    getters: {
        getUser: state => {
            return state.userInfo
        }
    },
    actions: {},
    modules: {},

    plugins: [createPersistedState()]

})
