<template>
  <div>
    <Header></Header>
    <div style="margin-left: 260px;margin-right: 260px">
      <el-page-header style="margin-top: 20px" @back="goBack" content="订单管理"></el-page-header>
      <el-card :class="[ 'show', userOrder.status === 0  ?  'create' : '',userOrder.status === 1  ?  'pay' : '' ]"
               style="margin-top: 20px" v-for="(userOrder,index) in userOrderList" :key="userOrder.id">
        <div>订单号：{{ userOrder.id }}</div>
        <div style="margin-top: 3px">订单状态：{{ userOrder.status | formatOrderStatus }}</div>
        <div style="margin-top: 3px">订单价格：{{ userOrderSeatLengthList[index] * screeningPriceList[index] }}元</div>
        <div style="margin-top: 3px">订单创建时间：{{ userOrder.createTime }}</div>
        <el-divider></el-divider>
        <div>电影名称：{{ movieNameList[index] }}</div>
        <div style="margin-top: 3px">放映类型：{{ screeningTypeList[index] | formatStatus }}</div>
        <div style="margin-top: 3px">开场时间：{{ screeningStartTimeList[index] }}</div>
        <el-button type="danger" icon="el-icon-sold-out" @click.native="payOrder(userOrder,index)"
                   style="margin-top: 15px"
                   :disabled="userOrder.status !== 0">支付
        </el-button>
        <el-button type="warning" icon="el-icon-close" @click.native="cancelOrder(userOrder,index)"
                   style="margin-top: 15px"
                   :disabled="userOrder.status !== 0">取消
        </el-button>
      </el-card>
      <el-pagination style="margin-top: 20px;text-align: center"
                     background
                     hide-on-single-page
                     layout="prev, pager, next"
                     @current-change="getUserOrder"
                     :current-page=this.current
                     :page-size=this.size
                     :total=this.userOrderPage.total>
      </el-pagination>
      <div v-if="userOrderList.length === 0" style="font-size: 18px;margin-top: 20px;margin-bottom: 20px;">暂无订单</div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";

export default {
  name: "OrderManage",
  components: {Footer, Header},
  data() {
    return {
      userOrderList: [],
      userOrderPage: [],
      screeningStartTimeList: [],
      screeningTypeList: [],
      screeningPriceList: [],
      screeningMovieIdList: [],
      movieNameList: [],
      userOrderSeatList: [],
      userOrderSeatLengthList: [],
      current: 1,
      size: 5
    }
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
    },
    formatOrderStatus(status) {
      if (status === 0) {
        return '未付款'
      } else if (status === 1) {
        return '已付款'
      } else if (status === -1) {
        return '已取消'
      }
    }
  },
  async created() {
    //1.应该先走这个
    await this.getUserOrder(this.current)
    //screeningMovieIdList在这个方法要用，拿不到
    await this.getMovieList()
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    async getUserOrder(current) {
      this.current = current
      // 获取用户id对应的订单List
      await this.$axios.get('/manage/userorder/page/?current=' + this.current + '&size=' + this.size + '&userId=' + this.$store.getters.getUser.userId, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(async res => {
        this.userOrderList = res.data.data.records
        this.userOrderPage = res.data.data
        // 获取订单对应的场次信息
        for (let index = 0; index < this.userOrderList.length; index++) {
          //2.这里
          await this.getScreening(this.userOrderList[index], index)
        }
        for (let index = 0; index < this.userOrderList.length; index++) {
          // 获取对应订单的座位号和座位数量
          await this.getUserOrderSeat(this.userOrderList[index], index)
        }
      })
    },
    async getUserOrderSeat(userOrder, index) {
      await this.$axios.get('/manage/userorderseat/page?current=1&size=99&userOrderId=' + userOrder.id, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.userOrderSeatList[index] = res.data.data.records
        for (let index = 0; index < this.userOrderSeatList.length; index++) {
          this.userOrderSeatLengthList.push(this.userOrderSeatList[index].length)
          this.userOrderSeatLengthList[index] = this.userOrderSeatList[index].length
        }
      })
    },
    async getScreening(userOrder, index) {
      await this.$axios.get('/manage/screening/' + userOrder.screeningId, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        // 3.这里获取到电影的id然后存到screeningMovieIdList
        // 获取对应场次的电影id
        if (res.data.data !== null) {
          this.screeningMovieIdList.push(res.data.data.movieId)
          this.screeningMovieIdList[index] = res.data.data.movieId
        } else {
          this.screeningMovieIdList.push(null)
          this.screeningMovieIdList[index] = null
        }
        // 获取对应场次的开场时间
        if (res.data.data !== null) {
          this.screeningStartTimeList.push(res.data.data.screeningStartTime)
          this.screeningStartTimeList[index] = res.data.data.screeningStartTime
        } else {
          this.screeningStartTimeList.push(null)
          this.screeningStartTimeList[index] = null
        }
        // 获取对应场次的放映类型
        if (res.data.data !== null) {
          this.screeningTypeList.push(res.data.data.type)
          this.screeningTypeList[index] = res.data.data.type
        } else {
          this.screeningTypeList.push(null)
          this.screeningTypeList[index] = null
        }
        // 获取对应场次的价格
        if (res.data.data !== null) {
          this.screeningPriceList.push(res.data.data.price)
          this.screeningPriceList[index] = res.data.data.price
        } else {
          this.screeningPriceList.push(null)
          this.screeningPriceList[index] = null
        }
      })
    },
    getMovieList() {
      // 获取场次电影id List对应的电影名称
      for (let index = 0; index < this.screeningMovieIdList.length; index++) {
        this.getMovie(this.screeningMovieIdList[index], index)
      }
    },
    getMovie(movieId, index) {
      this.$axios.get('/manage/movie/' + movieId, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        if (res.data.data !== null) {
          this.movieNameList.push(res.data.data.name)
          this.movieNameList[index] = res.data.data.name
        } else {
          this.movieNameList.push(null)
          this.movieNameList[index] = null
        }
      })
    },
    payOrder(userOrder, index) {
      this.$confirm('是否支付' + this.userOrderSeatLengthList[index] * this.screeningPriceList[index] + '元', "支付中心", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(() => {
        userOrder.status = 1
        this.$axios.put('/manage/userorder', userOrder, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(res => {
          this.getUserOrder(this.current)
          this.getMovieList()
        })
      })
    },
    cancelOrder(userOrder, index) {
      this.$confirm('是否取消该订单', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(() => {
        userOrder.status = -1
        this.$axios.put('/manage/userorder', userOrder, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(res => {
          this.getUserOrder(this.current)
          this.getMovieList()
        })
      })
    }
  }
}
</script>

<style scoped>
.show {
  margin-top: 10px;
}

.create {
  margin-top: 10px;
}

.pay {
  margin-top: 10px;
  background: #c6ffbd;
}
</style>