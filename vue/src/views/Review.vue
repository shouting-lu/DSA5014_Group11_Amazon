<template>
  <div>
    <el-container>
      <el-header style="height: 10vh">
        <Header></Header>
      </el-header>

      <el-container style="height: 85vh">
        <el-row style="width: 100vw">
          <el-col :span="8">
            <div class="card-container">

              <el-card class="card-container"
                       :body-style="{ padding: '20px' }"
                       style="height:70vh; margin-left: 60px; margin-right: 50px; margin-top: 60px">
                <img :src="cardData.image" alt="Card Image" @error="e => { e.target.src = defaultimage }" style="width: 100%; height: 40vh"/>
                <h2>{{ cardData.title }}</h2>
                <p>Rate: {{ cardData.rate }}</p>
                <p>Brand: {{ cardData.brand }}</p>
                <p class="description">Description: {{ cardData.description }}</p>

                <el-button type="primary" round style="margin-left: 300px" @click="backsearch">Back to Search</el-button>

              </el-card>

            </div>
          </el-col>

          <el-col :span="16">
            <div>
              <el-table
                  :data="tableData"
                  style="width: 60vw; margin-top: 50px; margin-left: 50px"
                  max-height="675">
                <el-table-column
                    prop="reviewTime"
                    label="reviewTime"
                    width="150">
                </el-table-column>
                <el-table-column
                    prop="customerID"
                    label="customer_ID"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="overall"
                    label="overall"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="image"
                    label="image"
                    width="300">
                  <template slot-scope="scope">
                    <img :src="scope.row.image" alt="Image" @error="e => { e.target.src = defaultimage }" style=" max-height: 100px;">
                  </template>
                </el-table-column>
                <el-table-column
                    prop="summary"
                    label="summary"
                    width="500">
                </el-table-column>
              </el-table>
            </div>
          </el-col>
        </el-row>

      </el-container>

      <el-footer style="height: 5vh">
        <Footer></Footer>
      </el-footer>

    </el-container>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";

export default {
  name: "Review",
  components: {Header, Footer},
  data(){
    return{
      cardData:[{
        image: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
        title: "I'm hungry!",
        rate: '3.7',
        brand: "IDN",
        description: ""
      }],
      defaultimage:require('../assets/noimage.jpeg'),
      tableData: []
    }
  },
  created() {
    this.cardData = JSON.parse(this.$route.query.item);
    this.request.get("/getreview" , {
      params: {
        asin: this.cardData.asin,
      }
    }).then(res =>{
      console.log(res)
      this.tableData = res
    })
  },
  methods:{
    backsearch(){
      this.$router.push('/')

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

.description {
  white-space: pre-line; /* 自动换行 */
  word-wrap: break-word; /* 当一个单词太长时强制换行 */
}

.card-container {
  height: 85vh; /* 指定容器的固定高度 */
  overflow-y: auto; /* 垂直滚动条 */
}

</style>