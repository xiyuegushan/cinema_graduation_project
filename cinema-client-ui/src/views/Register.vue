<template>
  <div>
    <el-container direction="vertical">
      <el-main>
        <el-form :model="registerForm" :rules="rules" ref="registerForm" label-width="100px" class="demo-ruleForm">
          <el-image style="width: 220px;margin-top: -20px;margin-left: 120px"
                    src="http://cinema-gateway:9999/admin/sys-file/cinema/dceb95ce6e7d4589b6e15064d7f80ff2.png"></el-image>
          <div
              style="width: 400px;margin-left: 80px;margin-top: 10px;margin-bottom: 30px;font-weight: 300;letter-spacing: 2px;font-size: 28px;font-weight: bold;color: #84b9fa;">
            影院购票网站用户端-注册
          </div>
          <el-form-item label="用户名" prop="username">
            <el-input prefix-icon="el-icon-user" v-model="registerForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input prefix-icon="el-icon-key" type="password" v-model="registerForm.password"
                      show-password></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input prefix-icon="el-icon-phone" v-model="registerForm.phone"></el-input>
          </el-form-item>
          <el-form-item label-width="130px">
            <el-button style="margin-right: 80px" type="primary" @click="userRegister('registerForm')">注册</el-button>
            <el-button type="primary" @click="toLogin">返回</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>

export default {
  name: "Register",
  data() {
    var checkPhone = (rule, value, callback) => { // 手机号验证
      if (!value) {
        return callback(new Error('手机号不能为空'));
      } else {
        const reg = /^1[3456789]\d{9}$/
        if (reg.test(value)) {
          callback();
        } else {
          return callback(new Error('请输入正确的手机号'));
        }
      }
    };
    return {
      registerForm: {
        username: '',
        password: '',
        phone: ''
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'change'}
        ],
        phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {validator: checkPhone, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    userRegister(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/admin/user/register", this.registerForm).then(res => {
            if (res.data.data === true) {
              this.toLogin();
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    toLogin() {
      this.$router.push("/login")
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