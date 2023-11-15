<template>
  <div >
    <el-container>
      <el-header style="height: 10vh">
        <Header></Header>
      </el-header>

      <el-container style="height: 85vh">

<!--        <el-aside width="200px" style="height: 85vh">-->
<!--          <Aside></Aside>-->
<!--        </el-aside>-->


        <el-main>
          <el-row style="width: 85vw; height: 49vh; margin-bottom: 10px; margin-left: 6vw; margin-top: 10px " justify="center" align="middle">
            <el-col  v-if="cardData && cardData.length > 0 && !iscardDataEmpty()" v-for="item in cardData" :key="item.asin" :span="5" style="margin-right: 10vw">
              <el-card class="card-container"

                       style="width: 20vw; margin-left: 10px; margin-right: 10px; margin-top: 10px; height: 45vh; overflow-y: auto;">
                <img :src="item.image" alt="Card Image" @error="e => { e.target.src = defaultimage }" style="width: 100%; height: 25vh"/>
                <h2>{{ item.title }}</h2>
                <p>Vote: {{ item.rate }}</p>
                <p>Brand: {{ item.brand }}</p>
                <div class="bottom clearfix">
                  <el-button type="text" class="button" @click="viewcomments(item)">View comments</el-button>
                </div>
              </el-card>
            </el-col>

            <el-col v-if="!cardData || cardData.length === 0 || iscardDataEmpty()" :span="24">
              <el-empty description="No data available" style="height: 49vh"></el-empty>
            </el-col>

          </el-row>

          <h2 style="height: 2vh"> Recommend for you </h2>

          <el-carousel :interval="4000" type="card" height="21vh" >
            <el-carousel-item v-if="recommendData && recommendData.length >0 && !isrecommendDataEmpty()" v-for="(item, index) in recommendData" :key="index" style="overflow-y: auto;">
              <div class="carousel-item">
                <div class="left" style="margin-right: 50px">
                  <img :src="item.image" alt="Product Image"@error="e => { e.target.src = defaultimage }" style="width: 100%; height:200px" />
                </div>
                <div class="right" >
                  <h2 style="margin-bottom: 1px">{{ item.title }}</h2>
<!--                  <p style="margin-bottom: 1px">Brand: {{ item.brand }}</p>-->
                  <p style="margin-bottom: 1px">Price: {{ item.price }}</p>
                  <p style="margin-bottom: 1px">Rate: {{ item.rate }}</p>
                </div>
              </div>
            </el-carousel-item>

            <el-carousel-item v-if="!recommendData || recommendData.length === 0 || isrecommendDataEmpty()" :span="24">
              <el-empty description="No data available"></el-empty>
            </el-carousel-item>

          </el-carousel>
        </el-main>
      </el-container>

      <el-footer style="height: 5vh">
        <Footer></Footer>
      </el-footer>

    </el-container>
  </div>
</template>


<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";
import Footer from "@/components/Footer";

export default {
  name: "Search",
  components: {Footer, Aside, Header},
  data() {
    return {
      cardData:[{

      }],
      defaultimage:require('../assets/noimage.jpeg'),
      asinArray: [],
      recommendData: [{

      }]
    };
  },
  created() {
    this.$root.$off('productData'); // 取消之前的事件订阅
    this.$root.$on('productData', data => {
      this.cardData = data;
      this.asin1 = data[0] ? data[0].asin : null;
      this.asin2 = data[1] ? data[1].asin : null;
      this.asin3 = data[2] ? data[2].asin : null;
      // console.log(this.asin1)
      // console.log(this.asin2)
      // console.log(this.asin3)

      this.getrecommend();
    });
  },
  methods:{
    viewcomments(item){
      this.$router.push({ path: '/review', query: { item: JSON.stringify(item) } });
    },
    iscardDataEmpty() {
      // 判断数组中的每个对象是否为空
      return this.cardData.every(item => Object.keys(item).length === 0);
    },
    isrecommendDataEmpty() {
      // 判断数组中的每个对象是否为空
      return this.recommendData.every(item => Object.keys(item).length === 0);
    },

    getrecommend(){

      this.request.get("/alsobuy/products" , {
        params: {
          asin1: this.asin1 !== null ? this.asin1 : "",
          asin2: this.asin2 !== null ? this.asin2 : "",
          asin3: this.asin3 !== null ? this.asin3 : ""
        }
      }).then(res =>{
        this.recommendData = res
        console.log(res)
      })
    }
  }

}


</script>

<style scoped>

.el-header {
  background-color: #303133;
  color: #333;
  text-align: center;
  line-height: 10vh;
}

.el-footer {
  background-color: #303133;
  color: #333;
  text-align: center;
  line-height: 5vh;
}

.el-aside {
  background-color: #606266;
  color: #333;
  text-align: center;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;

}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.card-container {
  flex: 1; /* 等宽分配空间，可以根据需要进行调整 */
  margin: 10px;
  /* 其他样式设置 */
}
.carousel-item {
  display: flex;
  justify-content: space-between;
}

.left {
  flex: 1;
  padding: 0 10px;
}

.right {
  flex: 2;
  padding: 0 1px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 1px 0; /* Adjust the top and bottom margin as needed */
  line-height: 1.1; /* Adjust the line height as needed */
}

</style>