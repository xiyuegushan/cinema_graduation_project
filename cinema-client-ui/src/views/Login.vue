<template>
  <div>
    <el-container direction="vertical">
      <el-main>
        <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="100px" class="demo-ruleForm">
          <el-image style="width: 220px;margin-top: -20px;margin-left: 120px;"
                    src="http://cinema-gateway:9999/admin/sys-file/cinema/dceb95ce6e7d4589b6e15064d7f80ff2.png"></el-image>
          <div style="width: 400px;margin-left: 80px;margin-top: 10px;margin-bottom: 30px;font-weight: 300;letter-spacing: 2px;font-size: 28px;font-weight: bold;color: #84b9fa;">影院购票网站用户端-登录</div>
          <el-form-item label="用户名" prop="username">
            <el-input prefix-icon="el-icon-user" v-model="loginForm.username"></el-input>
          </el-form-item>
          <el-form-item style="padding-bottom: 62px" label="密码" prop="password">
            <el-input prefix-icon="el-icon-key" type="password" v-model="loginForm.password" show-password></el-input>
          </el-form-item>
          <el-form-item label-width="130px">
            <el-button style="margin-right: 80px" type="primary" @click="userLogin('loginForm')">登录</el-button>
            <el-button type="primary" @click="toRegister">注册</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import AES from "@/common/AES.js";
import Element from "element-ui";

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'change'}
        ]
      }
    };
  },
  methods: {
    userLogin(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const params = new URLSearchParams();
          params.append('username', this.loginForm.username)
          params.append('password', AES.encrypt(this.loginForm.password))
          this.$axios.post("/auth/oauth/token?grant_type=password&scope=server",
              params,
              {headers: {Authorization: "Basic dGVzdDp0ZXN0"}}).then(res => {
            this.$store.commit('SET_TOKEN', res.data.access_token)
            this.$store.commit("SET_USERINFO", res.data.userInfo)
            this.$router.push("/home")
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    toRegister() {
      this.$router.push("/register")
    }
  }
}
</script>

<style scoped>

.demo-ruleForm {
  max-width: 400px;
  margin: 200px auto;
}


</style>