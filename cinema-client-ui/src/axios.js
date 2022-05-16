import axios from 'axios'
import Element from "element-ui";
import Router from "./router";
import errorCode from "@/const/errorCode";

axios.defaults.baseURL = 'http://cinema-gateway:9999'

axios.defaults.validateStatus = function (status) {
    return status >= 200 && status <= 500
}

axios.interceptors.request.use(config => {
    return config
})

axios.interceptors.response.use(response => {
        const status = Number(response.status) || 200;
        const message = response.data.msg || errorCode[status];
        if (status === 424) {
            Element.Message({
                message: "令牌状态已过期，请重新登录",
                type: 'warning',
                duration: 1000
            })
            Router.push("/login")
        }
        if (response.data.code === 0 || status === 200) {
            if (!message) {
            } else {
                Element.Message({
                    message: message,
                    type: 'success',
                    duration: 1000
                })
            }
            return response
        } else {
            Element.Message({
                message: message,
                type: 'warning',
                duration: 1000
            })
            return Promise.reject(new Error(message))
        }
    },
    error => {
        return Promise.reject(new Error(error))
    })
