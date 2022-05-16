<template>
  <div class="header">
    <el-menu router :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
      <el-menu-item>
        <el-image style="width: 220px;margin-top: -20px"
                  src="http://cinema-gateway:9999/admin/sys-file/cinema/dceb95ce6e7d4589b6e15064d7f80ff2.png"></el-image>
      </el-menu-item>
      <el-menu-item index="/home">首页</el-menu-item>
      <el-menu-item index="/movie/search">查询电影</el-menu-item>
      <el-menu-item index="/user/center">个人中心</el-menu-item>
      <el-menu-item index="/order/manage">订单管理</el-menu-item>
      <el-dropdown size="medium" placement="left" @command="handleCommand">
      <span class="el-dropdown-link">
        <el-avatar style="margin-right: 20px;" :size="50" :src="user.avatar"></el-avatar>
      </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item disabled>当前用户: {{ user.username }}</el-dropdown-item>
          <el-dropdown-item icon="el-icon-user" command="userCenter">个人中心</el-dropdown-item>
          <el-dropdown-item icon="el-icon-s-order" command="orderManage">订单管理</el-dropdown-item>
          <el-dropdown-item icon="el-icon-back" command="logout">注销</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-menu>
    <div class="line"></div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      hasLogin: false,
      user: {
        username: "未登录",
        phone: "",
        avatar: ""
      },
      blogs: {},
      pageNum: 1,
      total: 0,
      activeIndex: '1',
      activeIndex2: '1'
    }
  },

  methods: {
    handleCommand(command) {
      if (command === 'logout') {
        this.logout()
      }
      if (command === 'userCenter') {
        this.$router.push("/user/center")
      }
      if (command === 'orderManage') {
        this.$router.push("/order/manage")
      }
    },
    logout() {
      this.$axios.delete('/auth/token/logout', {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(() => {
        this.$store.commit('REMOVE_INFO')
        this.$router.push('/login')
      })
    }
  },

  created() {
    if (this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username
      this.user.phone = this.$store.getters.getUser.phone
      this.user.avatar = this.$axios.defaults.baseURL + this.$store.getters.getUser.avatar
      this.hasLogin = true
    }
  }
}
</script>

<style>
.header {
  text-align: right;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

</style>