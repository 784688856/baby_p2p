<template>
  <div class="container">
    <el-container style="height: 670px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '2','3']">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>借款项目
            </template>
            <el-menu-item index="1-1">投标明细</el-menu-item>
            <el-menu-item index="1-2">收款明细</el-menu-item>
            <el-menu-item index="1-3">借款明细</el-menu-item>
            <el-menu-item index="1-3">我的还款</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>我的账户
            </template>
            <el-menu-item index="2-1">
              <nuxt-link to="/home/profile/myprofile">个人资料</nuxt-link>
            </el-menu-item>
            <el-menu-item index="2-2">
              <nuxt-link to="/home/profile/bindBankCard">绑定银行卡</nuxt-link>
            </el-menu-item>
            <el-menu-item index="2-3">
              <nuxt-link to="/home/profile/loginHistory">登录记录</nuxt-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>资产详情
            </template>
            <el-menu-item index="3-1">账户流水</el-menu-item>
            <el-menu-item index="3-2">我要充值</el-menu-item>
            <el-menu-item index="3-3">余额提现</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <nuxt />
      </el-container>
    </el-container>
  </div>
</template>

<script>
import service from '~/api'
export default {
  // 需要加上async 和 await，才能返回
  async asyncData() {

  },
  mounted() {
  },
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄',

    };
    return {
      tableData: Array(20).fill(item),
      id: '',

    }
  }
};
</script>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>