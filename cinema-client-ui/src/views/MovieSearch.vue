<template>
  <div>
    <Header></Header>
    <div style="margin-left: 260px;margin-right: 260px">
      <el-page-header style="margin-top: 20px" @back="goBack" content="查询电影"></el-page-header>
      <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">类型</div>
      <el-checkbox-group
          @change="searchMovie(current)"
          style="margin-top: 30px"
          v-model="checkedCategory"
          :min="0"
          :max="1">
        <el-checkbox class="el-checkbox-width" v-for="category in categories" :label="category" :key="category">
          {{ category }}
        </el-checkbox>
      </el-checkbox-group>
      <el-divider></el-divider>
      <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">制片国家/地区</div>
      <el-checkbox-group
          @change="searchMovie(current)"
          style="margin-top: 30px"
          v-model="checkedProducer"
          :min="0"
          :max="1">
        <el-checkbox class="el-checkbox-width" v-for="producer in producers" :label="producer" :key="producer">
          {{ producer }}
        </el-checkbox>
      </el-checkbox-group>
      <el-divider></el-divider>
      <div style="font-size: 18px;margin-top: 20px">电影名称</div>
      <el-input placeholder="请输入电影名称" style="margin-top: 30px" v-model="searchParams.name"
                @input="searchMovie(current)"></el-input>
      <div style="font-size: 18px;margin-top: 20px">主演名字</div>
      <el-input placeholder="请输入主演名字" style="margin-top: 30px" v-model="searchParams.star"
                @input="searchMovie(current)"></el-input>
      <div style="font-size: 18px;margin-top: 20px">导演名字</div>
      <el-input placeholder="请输入导演名字" style="margin-top: 30px" v-model="searchParams.director"
                @input="searchMovie(current)"></el-input>
      <div style="margin: 20px;font-size: 24px">查询结果</div>
      <el-row>
        <el-col v-for="movie in searchMovieList" :key="movie.id" style="margin:5px;width: 260px">
          <el-card style="cursor: pointer" @click.native="toMovie(movie.id)">
            <el-image style="width: 220px;height: 300px;border-radius: 5px"
                      :src="getImageUrl(movie.img)"></el-image>
            <div style="padding: 14px;">
              <div style="text-align: center;font-size: 16px;">{{ movie.name }}</div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-pagination style="margin-top: 20px;text-align: center"
                     background
                     hide-on-single-page
                     layout="prev, pager, next"
                     @current-change="searchMovie"
                     :current-page=current
                     :page-size=size
                     :total=searchMoviePage.total>
      </el-pagination>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";

const categoryOptions = ['全部', '剧情', '喜剧', '动作', '爱情', '科幻', '动画', '悬疑', '惊悚', '恐怖', '犯罪', '同性', '音乐', '歌舞', '传记', '历史', '战争', '西部', '奇幻', '冒险', '灾难', '武侠', '情色'];
const producerOptions = ['全部', '中国大陆', '美国', '中国香港', '中国台湾', '日本', '韩国', '英国', '法国', '德国', '意大利', '西班牙', '印度', '泰国', '俄罗斯', '伊朗', '加拿大', '澳大利亚', '爱尔兰', '瑞典', '巴西', '丹麦']
export default {
  name: "MovieSearch",
  components: {Header, Footer},
  data() {
    return {
      searchMovieList: null,
      searchMoviePage: {total: null},
      searchParams: {
        name: '',
        category: '',
        star: '',
        producer: '',
        director: ''
      },
      current: 1,
      size: 10,
      checkedCategory: ['全部'],
      checkedProducer: ['全部'],
      producers: producerOptions,
      categories: categoryOptions
    }
  },
  created() {
    this.searchMovie(1)
  },
  methods: {
    searchMovie(current) {
      this.current = current
      this.searchParams.category = this.checkedCategory[0]
      this.searchParams.producer = this.checkedProducer[0]
      if (this.checkedCategory[0] === '全部') {
        this.searchParams.category = ''
      }
      if (this.checkedProducer[0] === '全部') {
        this.searchParams.producer = ''
      }
      this.$axios.post('/manage/movie/search?current=' + this.current + '&size=' + this.size, this.searchParams, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.searchMovieList = res.data.data.records
        this.searchMoviePage = res.data.data
      })
    },
    goBack() {
      this.$router.go(-1)
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

.el-checkbox-width {
  width: 100px;
  height: 25px;
}
</style>