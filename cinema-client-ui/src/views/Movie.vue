<template>
  <div>
    <Header></Header>
    <div style="margin-left: 260px;margin-right: 260px">
      <el-page-header style="margin-top: 20px" @back="goBack" content="电影详情"></el-page-header>
      <div style="display: flex;margin-top: 30px">
        <div style="display: inline-block">
          <el-card>
            <el-image style="width: 220px;height: 300px;border-radius: 5px" :src="getImageUrl(movie.img)"></el-image>
          </el-card>
        </div>

        <div style="display: inline-block;margin-left: 50px">
          <h1>{{ movie.name }}</h1>
          <div style="font-size: 14px;margin-top: 10px">{{ movie.category }}</div>
          <div style="font-size: 14px;margin-top: 10px">{{ movie.producer }}/{{ movie.length }}分钟</div>
          <div style="font-size: 14px;margin-top: 10px">{{ movie.releaseDate }} 上映</div>
          <div style="margin-top:60px;margin-bottom: 20px">
            <el-button style="width: 120px" icon="el-icon-success" @click.native="removeMovieWants"
                       v-if="isWants === true" type="danger">已想看
            </el-button>
            <el-button style="width: 120px" icon="el-icon-circle-check" @click.native="addMovieWants"
                       v-if="isWants === false">想看
            </el-button>
            <el-button style="width: 120px" icon="el-icon-star-on" @click.native="getMovieScore"
                       v-if="isScores === true" type="warning">已评分
            </el-button>
            <el-button style="width: 120px" icon="el-icon-star-off" @click.native="openScorePanel"
                       v-if="isScores === false">评分
            </el-button>
          </div>
          <div style="font-size: 14px">
            <el-button style="width: 250px" type="danger" icon="el-icon-goods" @click.native="toMovieScreening(movie.id)">购票</el-button>
          </div>
          <el-dialog center title="评分" :visible.sync="dialogVisible" width="30%">
            <div style="text-align: center">
              <el-rate :colors="colors" v-model="movieScore.score" :disabled="isScores"
                       @change="addMovieScore"></el-rate>
            </div>
          </el-dialog>
        </div>
      </div>

      <div>
        <el-divider></el-divider>
        <div style="font-size: 24px;margin-top: 20px;margin-bottom: 20px;">剧情简介</div>
        <div style="line-height: 27px">{{ movie.introduction }}</div>
        <el-divider></el-divider>
        <div style="font-size: 24px;margin-top: 20px;margin-bottom: 20px;">演职人员</div>
        <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">导演</div>
        <div>{{ movie.director }}</div>
        <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">编剧</div>
        <div>{{ movie.screenwriter }}</div>
        <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">演员</div>
        <div>{{ movie.star }}</div>
        <el-divider></el-divider>
        <div style="font-size: 24px;margin-top: 20px;margin-bottom: 20px;">电影评论</div>
        <div style="display: flex">
          <div style="display: inline-block;width: 100%">
            <el-input
                class="textarea"
                type="textarea"
                autosize
                placeholder="请输入评论"
                v-model="movieComment.comment">
            </el-input>
          </div>
          <div style="display: inline-block;margin-left: 10px;margin-top: -2px">
            <el-button type="primary" @click.native="addMovieComment" >提交</el-button>
          </div>
        </div>
        <el-card style="margin-top: 10px" v-for="(movieComment,index) in movieCommentList" :key="movieComment.id">
          <div style="display: flex">
            <el-avatar :size="60" :src="avatarList[index]"></el-avatar>
            <h2 style="margin-left: 20px">{{ movieCommentList[index].createBy }}</h2>
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
        <el-pagination style="margin-top: 20px;text-align: center"
                       background
                       hide-on-single-page
                       layout="prev, pager, next"
                       @current-change="getMovieCommentList"
                       :current-page=this.current
                       :page-size=this.size
                       :total=this.movieCommentPage.total>
        </el-pagination>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import Element from "element-ui";

export default {
  name: "Movie",
  data() {
    return {
      movie: {
        id: null,
        name: null,
        img: null,
        introduction: null,
        star: null,
        producer: null,
        director: null,
        screenwriter: null,
        language: null,
        length: null,
        category: null,
        releaseDate: null,
      },
      userId: '',
      movieCommentList: [],
      avatarList: [],
      movieCommentPage: [],
      movieWantsList: null,
      movieScoresList: null,
      movieComment: {
        movieId: this.$route.query.id,
        userId: this.$store.getters.getUser.userId,
        comment: '',
      },
      isWants: true,
      isScores: true,
      dialogVisible: false,
      colors: ['#99A9BF', '#F7BA2A', '#ff6600'],
      current: 1,
      size: 5,
      movieWants: {
        userId: this.$store.getters.getUser.userId,
        movieId: this.$route.query.id
      },
      movieScore: {
        userId: this.$store.getters.getUser.userId,
        movieId: this.$route.query.id,
        score: null
      },
      userInfo: null
    };
  },
  components: {Footer, Header},
  created() {
    this.userId = this.$store.getters.getUser.userId
    this.getMovie()
    this.getMovieCommentList(this.current)
    this.getMovieWantsList()
    this.getMovieScoresList()
  },
  methods: {
    getMovie() {
      this.$axios.get('/manage/movie/' + this.$route.query.id, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.movie = res.data.data
      })
    },
    getMovieScore() {
      this.dialogVisible = true
    },
    addMovieScore() {
      this.$axios.post("/manage/moviescore", this.movieScore, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        if (res.data.data === true) {
          this.isScores = true
          this.getMovieScoresList(this.$store.getters.getUser.userId, this.$route.query.id)
          this.dialogVisible = false
        }
      })
    },
    getMovieScoresList() {
      this.$axios.get('/manage/moviescore/page?current=1&size=20&userId=' + this.$store.getters.getUser.userId + "&movieId=" + this.$route.query.id, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.movieScoresList = res.data.data.records
        if (res.data.data.total === 0) {
          this.isScores = false
        } else {
          this.movieScore.score = this.movieScoresList[0].score
        }
      })
    },
    addMovieComment() {
      this.$axios.post('/manage/moviecomment', this.movieComment, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        if (res.data.data === true) {
          Element.Message({
            message: "评论成功",
            type: 'success',
            duration: 1000
          })
        } else {
          Element.Message({
            message: "评论失败",
            type: 'warning',
            duration: 1000
          })
        }
        this.movieComment.comment = ''
        this.getMovieCommentList(this.current)
      })
    },
    getMovieCommentList(current) {
      this.current = current
      this.$axios.get('/manage/moviecomment/page?current=' + this.current + '&size=' + this.size + '&movieId=' + this.$route.query.id, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.movieCommentList = res.data.data.records
        this.movieCommentPage = res.data.data
        this.avatarList = [];
        for (let index = 0; index < this.movieCommentList.length; index++) {
          this.getUserAvatar(this.movieCommentList[index], index)
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
    getMovieWantsList() {
      this.$axios.get('/manage/moviewants/page?current=1&size=20&userId=' + this.$store.getters.getUser.userId + "&movieId=" + this.$route.query.id, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.movieWantsList = res.data.data.records
        if (res.data.data.total === 0) {
          this.isWants = false
        }
      })
    },
    addMovieWants() {
      this.$axios.post("/manage/moviewants", this.movieWants, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        if (res.data.data === true) {
          this.isWants = true
        }
      })
    },
    removeMovieWants() {
      this.getMovieWantsList()
      this.$axios.delete("/manage/moviewants/" + this.movieWantsList[0].id, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        if (res.data.data === true) {
          this.isWants = false
        }
      })
    },
    openScorePanel() {
      this.dialogVisible = true
    },
    getImageUrl(url) {
      return this.$axios.defaults.baseURL + url
    },
    goBack() {
      this.$router.go(-1)
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
          this.getMovieCommentList(this.current)
        })
      })
    },
    toMovieScreening(id) {
      this.$router.push({name: 'MovieScreening', query: {id: id}})
    }
  }
}
</script>

<style scoped>
.textarea >>> .el-textarea__inner {
  font-family: "Microsoft" !important;
  font-size: 16px !important;
}
</style>