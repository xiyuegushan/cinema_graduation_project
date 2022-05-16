<template>
  <div>
    <Header :key="componentKey"></Header>
    <div style="margin-left: 260px;margin-right: 260px">
      <el-page-header style="margin-top: 20px" @back="goBack" content="个人中心"></el-page-header>
      <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">个人信息修改：</div>
      <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">头像：</div>
      <el-upload
          class="avatar-uploader"
          action="http://cinema-gateway:9999/admin/sys-file/upload"
          :headers="headers"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
        <img v-if="userInfo.avatar !== null && userInfo.avatar !== ''" :src="getImageUrl(this.userInfo.avatar)"
             class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
      <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px">用户名：</div>
      <div>
        <el-input
            disabled
            v-model="userInfo.username">
        </el-input>
      </div>
      <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px">手机：</div>
      <div>
        <el-input
            v-model="updateUserInfo.phone">
        </el-input>
      </div>
      <div style="margin-top: 20px;margin-bottom: 20px;">
        <el-button type="primary" @click.native="openInputPasswordPanel">修改个人信息</el-button>
        <el-button type="primary" @click.native="openInputNewPasswordPanel">修改密码</el-button>
      </div>
      <el-divider></el-divider>
      <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">最近评论：</div>
      <el-card style="margin-top: 10px" v-for="(movieComment,index) in movieCommentList" :key="movieComment.id">
        <div style="display: flex;flex-direction:row">
          <el-avatar :size="60" :src="avatarList[index]"></el-avatar>
          <h2 style="margin-left: 10px;width: 500px">{{ movieCommentList[index].createBy }}</h2>
          <div style="width: 100px;height: 130px;cursor: pointer;margin-left: 49%">
            <el-image @click.native="toMovie(movieCommentInfoList[index].id)"
                      :src="getImageUrl(movieCommentImgList[index])"></el-image>
          </div>
        </div>
        <el-divider></el-divider>
        <div style="display: flex;flex-direction:column">
          <div>{{ movieCommentList[index].comment }}</div>
          <el-button v-if="movieCommentList[index].userId === userId" type="danger"
                     style="align-self: flex-end" @click.native="deleteMovieComment(movieCommentList[index].id)">删除
          </el-button>
        </div>
        <p style="float: right;font-size: 12px">{{ movieCommentList[index].createTime }}</p>
      </el-card>
      <div v-if="movieCommentList.length === 0" style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">暂无评论</div>
      <el-dialog center title="验证身份" :visible.sync="dialogVisible" width="30%">
        <div style="text-align: center">
          <el-input
              show-password
              placeholder="请输入原密码"
              v-model="updateUserInfo.password">
          </el-input>
          <el-button style="margin-top: 30px" type="primary" @click.native="uploadUserInfo">确认修改</el-button>
        </div>
      </el-dialog>

      <el-dialog :before-close="handleClose" center title="修改密码" :visible.sync="changePasswordDialogVisible"
                 width="30%">
        <div style="text-align: center">
          <el-input
              show-password
              placeholder="请输入新密码"
              v-model="updateUserInfo.newpassword1">
          </el-input>
          <el-button style="margin-top: 30px" type="primary" @click.native="openInputPasswordPanel">修改密码</el-button>
        </div>
      </el-dialog>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import Element from "element-ui";

export default {
  name: "UserCenter",
  components: {Footer, Header},
  data() {
    return {
      userInfo: {username: '', phone: '', avatar: '', userId: ''},
      userId: '',
      headers: null,
      dialogVisible: false,
      changePasswordDialogVisible: false,
      needLogout: false,
      componentKey: 0,
      movieCommentList: [],
      avatarList: [],
      movieCommentInfoList: [],
      movieCommentImgList: [],
      updateUserInfo: {
        avatar: '',
        phone: '',
        password: '',
        username: '',
        newpassword1: ''
      },
    }
  },
  created() {
    this.userId = this.$store.getters.getUser.userId
    this.getUserInfo()
    this.headers = {
      "Authorization": localStorage.getItem("token")
    }
    this.getMovieCommentList()
  }
  ,
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    getMovieCommentList() {
      this.$axios.get('/manage/moviecomment/page?current=1&size=3&userId=' + this.$store.getters.getUser.userId, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.movieCommentList = res.data.data.records
        this.movieCommentPage = res.data.data
        this.avatarList = [];
        for (let index = 0; index < this.movieCommentList.length; index++) {
          this.getUserAvatar(this.movieCommentList[index], index)
          this.getMovie(this.movieCommentList[index].movieId, index)
        }
      })
    },
    getUserAvatar(movieComment, index) {
      this.$axios.get('/admin/user/info/' + movieComment.createBy, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        if (res.data.data.sysUser.avatar !== null) {
          this.avatarList.push(this.$axios.defaults.baseURL + res.data.data.sysUser.avatar)
          this.avatarList[index] = this.$axios.defaults.baseURL + res.data.data.sysUser.avatar
        } else {
          this.avatarList.push(null)
          this.avatarList[index] = null
        }
      })
    },
    getMovie(movieId, index) {
      this.$axios.get('/manage/movie/' + movieId, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        if (res.data.data !== null) {
          this.movieCommentInfoList.push(res.data.data)
          this.movieCommentInfoList[index] = res.data.data
          this.movieCommentImgList.push(res.data.data.img)
          this.movieCommentImgList[index] = res.data.data.img
        } else {
          this.movieCommentInfoList.push(null)
          this.movieCommentInfoList[index] = null
          this.movieCommentImgList.push(null)
          this.movieCommentImgList[index] = null
        }
      })
    },
    getUserInfo() {
      this.$axios.get('/admin/user/info/' + this.$store.getters.getUser.username, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.userInfo = res.data.data.sysUser
        this.updateUserInfo.phone = this.userInfo.phone
        this.updateUserInfo.avatar = this.userInfo.avatar
        this.updateUserInfo.username = this.userInfo.username
      })
    }
    ,
    getImageUrl(url) {
      return this.$axios.defaults.baseURL + url
    },
    handleAvatarSuccess(res, file) {
      this.userInfo.avatar = res.data.url
      this.updateUserInfo.avatar = res.data.url
      Element.Message({
        message: "上传头像成功,请点击下方修改个人信息以确认修改",
        type: 'success',
        duration: 2000
      })
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt10M = file.size / 1024 / 1024 < 10;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是JPG格式');
      }
      if (!isLt10M) {
        this.$message.error('上传头像图片大小不能超过10mb');
      }
      return isJPG && isLt10M;
    },
    uploadUserInfo() {
      this.updateUserInfo.username = this.$store.getters.getUser.username
      this.$axios.put('/admin/user/edit', this.updateUserInfo, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.$store.commit('SET_USERINFO_AVATAR', this.updateUserInfo.avatar)
        this.componentKey++
        if (res.data.data === true) {
          Element.Message({
            message: "修改个人信息成功",
            type: 'success',
            duration: 1000
          })
        } else {
          Element.Message({
            message: "修改个人信息失败",
            type: 'warning',
            duration: 1000
          })
        }
        if (this.needLogout) {
          this.logout()
        }
      })
      this.dialogVisible = false
    },
    openInputPasswordPanel() {
      this.changePasswordDialogVisible = false
      this.dialogVisible = true
    },
    openInputNewPasswordPanel() {
      this.needLogout = true
      this.changePasswordDialogVisible = true
    },
    toMovie(id) {
      this.$router.push({name: 'Movie', query: {id: id}})
    },
    handleClose(done) {
      this.$confirm('是否退出修改密码')
          .then(_ => {
            this.needLogout = false
            done();
          })
          .catch(_ => {
          });
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
    },
    deleteMovieComment(id) {
      this.$confirm("是否删除此评论", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$axios.delete('/manage/moviecomment/' + id, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(res => {
          this.getMovieCommentList()
        })
      })
    }
  }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  border: 1px #cccccc solid;
  width: 178px;
  height: 178px;
  display: block;
}
</style>