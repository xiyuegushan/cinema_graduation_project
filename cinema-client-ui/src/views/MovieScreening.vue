<template>
  <div>
    <Header></Header>
    <div style="margin-left: 260px;margin-right: 260px">
      <el-page-header style="margin-top: 20px" @back="goBack" content="电影购票"></el-page-header>
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
          <div style="font-size: 14px">
            <el-button style="width: 250px;margin-top:120px" type="danger" icon="el-icon-back"
                       @click.native="toMovie(movie.id)">返回电影详情
            </el-button>
          </div>
        </div>
      </div>
      <el-divider></el-divider>
      <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">电影场次：</div>
      <el-card :class="[ 'show', current === index  ?  'active' : ' ' ]"
               @click.native="chooseScreening(screeningList[index]);active(index)"
               v-if="haveScreening === true" v-for="(screening,index) in screeningList"
               :key="screening.id">
        <h3 style="margin: 5px">影厅：{{ screeningRoomNameList[index] }}</h3>
        <div style="margin: 5px">票价：{{ screeningList[index].price }} 元</div>
        <div style="margin: 5px">放映方式：{{ screeningList[index].type | formatStatus }}</div>
        <div style="margin: 5px">开场时间：{{ screeningList[index].screeningStartTime }}</div>
      </el-card>
      <div v-if="haveScreening === false" style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">暂无安排场次</div>
      <div v-if="screeningId !== null">
        <el-divider></el-divider>
        <div style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">电影选座：</div>
        <div style="margin-top: 20px;margin-bottom: 20px;margin-left: 5px">
          <el-button :disabled="chooseNum === 0" type="danger" icon="el-icon-goods" @click.native="buyChooseSeat">
            创建订单
          </el-button>
          <span style="margin-top: 20px;margin-left: 20px">你已选择的座位：</span>
          <span v-for="choose in screeningBuyChooseSeatList" :key="choose.id"> {{ choose.siteX }}排{{
              choose.siteY
            }}号 </span>
        </div>
      </div>
      <div style="display: inline-block" v-for="(screeningSeat,index) in screeningSeatList" :key="screeningSeat.id" v-if="screeningSeatList[index].siteX === 1">
        <el-button v-if="screeningSeatList[index].status === 0" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,false)">{{
            screeningSeat.siteX
          }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === -1" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === 2" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="primary" v-if="screeningSeatList[index].status === 1" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,true)">{{ screeningSeat.siteX }}排{{
            screeningSeat.siteY
          }}号
        </el-button>
      </div>
<!--      第二排-->
      <div></div>
      <div style="display: inline-block" v-for="(screeningSeat,index) in screeningSeatList" :key="screeningSeat.id" v-if="screeningSeatList[index].siteX === 2">
        <el-button v-if="screeningSeatList[index].status === 0" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,false)">{{
            screeningSeat.siteX
          }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === -1" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === 2" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="primary" v-if="screeningSeatList[index].status === 1" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,true)">{{ screeningSeat.siteX }}排{{
            screeningSeat.siteY
          }}号
        </el-button>
      </div>
      <!--      第三排-->
      <div></div>
      <div style="display: inline-block" v-for="(screeningSeat,index) in screeningSeatList" :key="screeningSeat.id" v-if="screeningSeatList[index].siteX === 3">
        <el-button v-if="screeningSeatList[index].status === 0" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,false)">{{
            screeningSeat.siteX
          }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === -1" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === 2" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="primary" v-if="screeningSeatList[index].status === 1" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,true)">{{ screeningSeat.siteX }}排{{
            screeningSeat.siteY
          }}号
        </el-button>
      </div>
      <!--      第四排-->
      <div></div>
      <div style="display: inline-block" v-for="(screeningSeat,index) in screeningSeatList" :key="screeningSeat.id" v-if="screeningSeatList[index].siteX === 4">
        <el-button v-if="screeningSeatList[index].status === 0" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,false)">{{
            screeningSeat.siteX
          }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === -1" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === 2" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="primary" v-if="screeningSeatList[index].status === 1" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,true)">{{ screeningSeat.siteX }}排{{
            screeningSeat.siteY
          }}号
        </el-button>
      </div>
      <!--      第五排-->
      <div></div>
      <div style="display: inline-block" v-for="(screeningSeat,index) in screeningSeatList" :key="screeningSeat.id" v-if="screeningSeatList[index].siteX === 5">
        <el-button v-if="screeningSeatList[index].status === 0" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,false)">{{
            screeningSeat.siteX
          }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === -1" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === 2" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="primary" v-if="screeningSeatList[index].status === 1" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,true)">{{ screeningSeat.siteX }}排{{
            screeningSeat.siteY
          }}号
        </el-button>
      </div>
      <!--      第六排-->
      <div></div>
      <div style="display: inline-block" v-for="(screeningSeat,index) in screeningSeatList" :key="screeningSeat.id" v-if="screeningSeatList[index].siteX === 6">
        <el-button v-if="screeningSeatList[index].status === 0" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,false)">{{
            screeningSeat.siteX
          }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === -1" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="danger" disabled v-if="screeningSeatList[index].status === 2" style="margin: 5px">
          {{ screeningSeat.siteX }}排{{ screeningSeat.siteY }}号
        </el-button>
        <el-button type="primary" v-if="screeningSeatList[index].status === 1" style="margin: 5px"
                   @click.native="chooseSeat(screeningSeat,index,true)">{{ screeningSeat.siteX }}排{{
            screeningSeat.siteY
          }}号
        </el-button>
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
  name: "OrderManage",
  components: {Footer, Header},
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
      screeningList: [],
      screeningSeatList: [],
      screeningBuyChooseSeatList: [],
      screeningChooseSeatList: [],
      screeningRoomNameList: [],
      screeningRoomId: null,
      haveScreening: true,
      screeningId: null,
      chooseNum: 0,
      current: null
    }
  },
  created() {
    this.getMovie()
    this.getMovieScreening()
  },
  filters: {
    formatStatus(status) {
      if (status === 0) {
        return '2D'
      } else if (status === 1) {
        return '3D'
      } else if (status === 2) {
        return 'IMAX'
      }
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    getMovie() {
      this.$axios.get('/manage/movie/' + this.$route.query.id, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.movie = res.data.data
      })
    },
    toMovie(id) {
      this.$router.push({name: 'Movie', query: {id: id}})
    },
    getImageUrl(url) {
      return this.$axios.defaults.baseURL + url
    },
    getMovieScreening() {
      this.$axios.get('/manage/screening/page/fromnow?current=1&size=20&movieId=' + this.$route.query.id, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.screeningList = res.data.data.records
        for (let index = 0; index < this.screeningList.length; index++) {
          this.getMovieScreeningRoomName(this.screeningList[index], index)
        }
        if (res.data.data.total === 0) {
          this.haveScreening = false
        }
      })
    },
    getMovieScreeningRoomName(screening, index) {
      this.$axios.get('/manage/screeningroom/' + screening.screeningRoomId, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        if (res.data.data !== null) {
          this.screeningRoomNameList.push(res.data.data.name)
          this.screeningRoomNameList[index] = res.data.data.name
        } else {
          this.screeningRoomNameList.push(null)
          this.screeningRoomNameList[index] = null
        }
      })
    },
    chooseScreening(screening) {
      this.screeningChooseSeatList = []
      this.screeningBuyChooseSeatList = []
      this.screeningId = screening.id
      this.screeningRoomId = screening.screeningRoomId
      this.$axios.get('/manage/screeningseat/page?current=1&size=999&screeningRoomId=' + screening.screeningRoomId + '&screeningId=' + screening.id, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.screeningSeatList = res.data.data.records
      })
    },
    chooseSeat(screeningSeat, index, isCancel) {
      if (this.screeningSeatList[index].status === 0) {
        this.screeningSeatList[index].status = 1

        /*改动*/
        this.screeningChooseSeatList = this.screeningChooseSeatList.filter((item) => {
          return item.id !== this.screeningSeatList[index].id;
        });
        this.screeningChooseSeatList.push(this.screeningSeatList[index])
        console.log(this.screeningChooseSeatList)

      } else if (this.screeningSeatList[index].status === 1) {
        this.screeningSeatList[index].status = 0

        /*改动*/
        this.screeningChooseSeatList = this.screeningChooseSeatList.filter((item) => {
          return item.id !== this.screeningSeatList[index].id;
        });
        this.screeningChooseSeatList.push(this.screeningSeatList[index])
        console.log(this.screeningChooseSeatList)
      }
      if (isCancel) {
        let list = this.screeningBuyChooseSeatList
        let cancelId = list.findIndex((list) => list.id === screeningSeat.id)
        this.screeningBuyChooseSeatList.splice(cancelId, 1)
        if (this.chooseNum === 0) {
          this.chooseNum = 0
        } else {
          this.chooseNum--
        }
      } else {
        this.screeningBuyChooseSeatList.push(this.screeningSeatList[index])
        this.chooseNum++
      }
      this.$axios.put('/manage/screeningseat/choose/false/' + this.screeningId, this.screeningChooseSeatList, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        if (res.data.data === false) {
          this.screeningChooseSeatList = []
          this.screeningBuyChooseSeatList = []
          location.reload()
        }
        this.$axios.get('/manage/screeningseat/page?current=1&size=999&screeningRoomId=' + screeningSeat.screeningRoomId + '&screeningId=' + screening.id, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(res => {
          this.screeningSeatList = res.data.data.records
        })
      })
    },
    buyChooseSeat() {
      this.$confirm('是否创建订单', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(() => {
        for (let index = 0; index < this.screeningBuyChooseSeatList.length; index++) {
          this.screeningBuyChooseSeatList[index].status = 2
        }
        this.$axios.put('/manage/screeningseat/choose/true/' + this.screeningId, this.screeningBuyChooseSeatList, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(res => {
          this.$axios.get('/manage/screeningseat/page?current=1&size=999&screeningRoomId=' + this.screeningRoomId + '&screeningId=' + screening.id, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(res => {
            this.chooseNum = 0
            this.screeningBuyChooseSeatList = []
            this.screeningChooseSeatList = []
            this.screeningSeatList = res.data.data.records
          })
        })
        Element.Message({
          message: "订单已创建,请到订单管理中进行支付",
          type: 'success',
          duration: 2000
        })
      })
    },
    active(index) {
      this.current = index
    }
  }
}
</script>

<style scoped>
.show {
  margin-top: 10px;
  cursor: pointer
}

.active {
  margin-top: 10px;
  cursor: pointer;
  background: #bdd8ff;
}
</style>