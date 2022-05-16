<template>
  <div>
    <Header></Header>
    <!--      正在热映-->
    <div style="margin-left: 260px;margin-right: 260px">
      <div style="margin: 20px;font-size: 24px">正在热映</div>
      <el-row>
        <el-col v-for="movie in showingList" :key="movie.id" style="margin:5px;width: 260px">
          <el-card style="cursor: pointer" @click.native="toMovie(movie.id)">
            <el-image style="width: 220px;height: 300px;border-radius: 5px" :src="getImageUrl(movie.img)"></el-image>
            <div style="padding: 14px;">
              <div style="text-align: center;font-size: 16px;">{{ movie.name }}</div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <!--      即将上映-->
      <div style="margin: 20px;font-size: 24px">即将上映</div>
      <el-row>
        <el-col v-for="movie in comingsoonList" :key="movie.id" style="margin:5px;width: 260px">
          <el-card style="cursor: pointer" @click.native="toMovie(movie.id)">
            <el-image style="width: 220px;height: 300px;border-radius: 5px" :src="getImageUrl(movie.img)"></el-image>
            <div style="padding: 14px;">
              <div style="text-align: center;font-size: 16px">{{ movie.name }}</div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <!--      最多想看-->
      <div style="margin: 20px;font-size: 24px">最多想看</div>
      <el-row>
        <el-col v-for="movie in mostwantsList" :key="movie.id" style="margin:5px;width: 260px">
          <el-card style="cursor: pointer" @click.native="toMovie(movie.id)">
            <el-image style="width: 220px;height: 300px;border-radius: 5px" :src="getImageUrl(movie.img)"></el-image>
            <div style="padding: 14px;">
              <div style="text-align: center;font-size: 16px">{{ movie.name }}</div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <!--      最高评分-->
      <div style="margin: 20px;font-size: 24px">最高评分</div>
      <el-row>
        <el-col v-for="movie in highscoreList" :key="movie.id" style="margin:5px;width: 260px">
          <el-card style="cursor: pointer" @click.native="toMovie(movie.id)">
            <el-image style="width: 220px;height: 300px;border-radius: 5px" :src="getImageUrl(movie.img)"></el-image>
            <div style="padding: 14px;">
              <div style="text-align: center;font-size: 16px">{{ movie.name }}</div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";

export default {
  name: "Home",
  components: {Footer, Header},
  data() {
    return {
      showingList: null,
      comingsoonList: null,
      mostwantsList: null,
      highscoreList: null
    };
  },
  created() {
    this.showing();
    this.comingsoon();
    this.mostwants();
    this.highscore();
  },
  methods: {
    showing() {
      this.$axios.get('/manage/movie/showing?current=1&size=5', {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.showingList = res.data.data.records
      })
    },
    comingsoon() {
      this.$axios.get('/manage/movie/comingsoon?current=1&size=5', {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.comingsoonList = res.data.data.records
      })
    },
    mostwants() {
      this.$axios.get('/manage/movie/mostwants?current=1&size=5', {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.mostwantsList = res.data.data.records
      })
    },
    highscore() {
      this.$axios.get('/manage/movie/highscore?current=1&size=5', {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.highscoreList = res.data.data.records
      })
    },
    toMovie(id) {
      this.$router.push({name: 'Movie', query: {id: id}})
    },
    getImageUrl(url) {
      return this.$axios.defaults.baseURL + url
    }
  }
}
</script>

<style scoped>
a {
  text-decoration: none
}
</style>