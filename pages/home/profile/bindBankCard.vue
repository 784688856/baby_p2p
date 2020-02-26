<template>
  <!-- 绑定银行卡页面 -->
  <div class="bankcard">
    <el-card class="box-card" v-if="bankInfo==null">
      <div slot="header" class="clearfix">
        <span class="formspan">绑定银行卡</span>
      </div>
      <span style="color:red">为保护您账户安全，绑定银行卡之后，不能自己修改，请认真填写！</span>
      <br />

      <el-form :inline="true" :model="model" class="demo-form-inline">
        <el-form-item label="开户名 : ">
          <el-input v-model="model.realname" placeholder="请输入真实姓名" clearable></el-input>
        </el-form-item>
        <br />

        <el-form-item label="银行卡号:">
          <el-input v-model="model.cardNumber" placeholder="请输入银行卡号" clearable></el-input>
        </el-form-item>
        <br />

        <el-form-item label="开户行 : ">
          <el-select v-model="model.bankName" placeholder="请选择">
            <el-option label="中国银行" value="中国银行"></el-option>
            <el-option label="中国工商银行" value="中国工商银行"></el-option>
            <el-option label="中国农业银行" value="中国农业银行"></el-option>
            <el-option label="中国建设银行" value="中国建设银行"></el-option>
            <el-option label="中国招商银行" value="中国招商银行"></el-option>
            <el-option label="中国交通银行" value="中国交通银行"></el-option>
          </el-select>
        </el-form-item>
        <br />

        <el-form-item label="支行名称:">
          <el-input v-model="model.branchName" placeholder="请输入支行名称" clearable></el-input>
        </el-form-item>
        <br />

        <el-form-item>
          <el-button type="primary" @click="bangding">绑定银行卡</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="box-card" v-else>
      <div slot="header" class="clearfix">
        <span class="formspan">您已经绑定了银行卡</span>
      </div>
      <div class="text item">开户名：{{bankInfo.realname}}</div>
      <div class="text item">开户行：{{bankInfo.bankName}}</div>
      <div class="text item">银行卡号：{{bankInfo.cardNumber}}</div>
      <div class="text item">支行名称：{{bankInfo.branchName}}</div>
      <div class="text item">余额：{{bankInfo.balance}}</div>
    </el-card>
  </div>
</template>

<script>
import { add, getInfo } from '@/api/bindBank'

export default {
  data() {
    return {
      model: {
        id: '',
        userId: '',
        realname: '',
        bankName: '',
        cardNumber: '',
        branchName: '',
        balance: '',
      },
    };
  },
  methods: {

    bangding() {
      let newCardInfo = {
        userId: this.$store.state.user.userInfo.id, // 用户id
        realname: this.model.realname, // 真实姓名
        bankName: this.model.bankName, // 银行名称
        cardNumber: this.model.cardNumber, // 银行卡号
        branchName: this.model.branchName, // 支行名称
      }
      add(newCardInfo).then(res => {
        if (res.success) {
          this.$message.success('绑定成功!')
          // 替换为查询页面
          this.$router.go(0)
        }
      })
    },



  }, created() {


  },
  async asyncData(context) {
    // 判断是否绑定了银行卡
    let res = await getInfo(context.store.state.user.userInfo.id).then(res => {
      return res
    })
    console.log(res)
    return { bankInfo: res.data }

  }
}
</script>

<style scoped>
/* Card卡片 */
.box-card {
  width: 958px;
  height: 666px;
}
.text {
  font-size: 14px;
}
.item {
  margin-bottom: 18px;
}
.clearfix {
  font-size: 18px;
  text-align: left;
}
</style>>
