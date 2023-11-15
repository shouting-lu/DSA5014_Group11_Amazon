<template>
  <el-row>
    <el-col :span="8">
      <div style="color:#FFFFFF" >
        Amazon Review Data (2018) - Fashion (Clothing, Shoes and Jewelry)
      </div>
    </el-col>
    <el-col :span="12">
      <div style="margin-top: 0px; width: 50vw; margin-left: 30px">
        <el-input placeholder="Search" v-model="input" class="input-with-select">
          <el-select v-model="select" slot="prepend" placeholder="please select" >
            <el-option label="description" value="1"></el-option>
            <el-option label="products" value="2"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
        </el-input>
      </div>
    </el-col>
    <el-col :span="2">
      <div style="color:#FFFFFF; ">
        <i class="el-icon-shopping-cart-2" style="font-size: 40px; margin-top: 28px; margin-left: 80px"></i>
      </div>
    </el-col>
    <el-col :span="2">
      <div style="color:#FFFFFF; ">
        <h3 style="margin-top: 0px;">v1.0.0</h3>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      input: '',
      select: '',
      cardData:[]
    };
  },
  methods:{
    search() {
      if (this.select === '1') {
        this.request.get("/dessearch" , {
          params: {
            keyword: this.input,
          }
        }).then(res =>{
          // console.log(res)
          this.cardData = res
          this.$root.$emit('productData', this.cardData);
        })
      }else if (this.select === '2'){
        this.request.get("/ctsearch" , {
          params: {
            keyword: this.input,
          }
        }).then(res =>{
          // console.log(res)
          this.cardData = res
          this.$root.$emit('productData', this.cardData);
        })
      }
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
</style>