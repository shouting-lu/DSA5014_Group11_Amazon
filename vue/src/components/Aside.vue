<template>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      background-color="#232F3E"
      style="height: 85vh">
    <el-menu-item index="1">
      <i class="el-icon-menu"></i>
      <span slot="title" style="color:#FFFFFF">Search</span>
    </el-menu-item>
    <el-menu-item index="2">
      <i class="el-icon-menu"></i>
      <span slot="title" style="color:#FFFFFF">Comment</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>
.el-aside {
  background-color: #606266;
  color: #333;
  text-align: center;
}
</style>