<template>
  <div>
    <!-- 轮播图 -->
    <el-carousel :interval="5000" arrow="always">
      <el-carousel-item>
        <img src="~/assets/img/pic/banner01.jpg" width="100%" alt="Baby投" />
      </el-carousel-item>
      <el-carousel-item>
        <img src="~/assets/img/pic/banner02.jpg" width="100%" alt="Baby投" />
      </el-carousel-item>
    </el-carousel>

    <!-- 项目三大特色 -->
    <div style="padding-left: 20%;height:200px;margin-bottom: 16px;">
      <el-card class="box-card">
        <div class="text item" style="text-align:center">
          <el-image
            style="width: 100px; height: 85px"
            src="https://kt-los.oss-cn-beijing.aliyuncs.com/cover/01.png"
          ></el-image>
          <h2 style="font-size:20px">简单便捷</h2>
          <p>
            100元即可出借
            <br />直接出借更便捷
          </p>
        </div>
      </el-card>
      <el-card class="box-card box-card2">
        <div class="text item" style="text-align:center">
          <el-image
            style="width: 100px; height: 85px"
            src="https://kt-los.oss-cn-beijing.aliyuncs.com/cover/02.png"
          ></el-image>
          <h2>资金存管</h2>
          <p>
            隔离客户资金
            <br />行业安全第一
          </p>
        </div>
      </el-card>
      <el-card class="box-card box-card3">
        <div class="text item" style="text-align:center">
          <el-image
            style="width: 100px; height: 85px"
            src="https://kt-los.oss-cn-beijing.aliyuncs.com/cover/03.png"
          ></el-image>
          <h2>省心无忧</h2>
          <p>
            多维智能匹配
            <br />资金小额分散
          </p>
        </div>
      </el-card>
    </div>
    <!-- 项目三大特色结束 -->

    <!-- 表格开始 -->
    <el-table ref="singleTable" :data="tableData" highlight-current-row style="width: 100%">
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column property="date" label="日期" width="120"></el-table-column>
      <el-table-column property="name" label="姓名" width="120"></el-table-column>
      <el-table-column property="address" label="地址"></el-table-column>
    </el-table>
    <!-- 表格结束 -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }],
      currentRow: null
    }
  }
}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.box-card {
  width: 200px;
  /* height: 200px; */
}
.box-card2 {
  position: relative;
  margin: -200px 0px 0px 300px;
  /* padding: 10px; */
}
.box-card3 {
  position: relative;
  margin: -200px 0px 0px 600px;
  /* padding: 10px; */
}
</style>