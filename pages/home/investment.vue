<template>
  <div>
    <div class="container">
      <h3>投资列表</h3>
      <el-tabs>
        <el-tab-pane label="全部">
          <el-table
            ref="singleTable"
            :data="tableData"
            highlight-current-row
            style="width: 100%"
          >
            <el-table-column type="index" width="50"></el-table-column>
            <el-table-column property="date" label="日期" width="120"></el-table-column>
            <el-table-column property="name" label="姓名" width="120"></el-table-column>
            <el-table-column property="address" label="地址"></el-table-column>
          </el-table>
          <div style="margin-top: 20px">
            <el-button @click="setCurrent(tableData[1])">选中第二行</el-button>
            <el-button @click="setCurrent()">取消选择</el-button>
          </div>
        </el-tab-pane>
        <el-tab-pane label="招标中">招标中</el-tab-pane>
        <el-tab-pane label="还款中">角色管理</el-tab-pane>
        <el-tab-pane label="已完成">定时任务补偿</el-tab-pane>
      </el-tabs>
    </div>
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
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],
      currentRow: null
    }
  }
}
</script>

<style>
</style>