<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h2 class="formspan">账户提现</h2>
        <h5>1. 为了确保银行转账成功，请您确认银行账号信息的 正确性</h5>
        <h5>2. 单笔提现范围：500元 ~ 5000元</h5>
        <h5>3. 目前每笔提现服务费：(提现金额 × 1%)</h5>
      </div>
      <h3>
        <span>可提现金额：{{initavaiable}}元</span>
        <span style="margin-left: 20px;">冻结金额：{{walletInfo.freezeAmount}}元</span>
      </h3>
      <div class="dd">
        <el-form ref="form" :model="bankInfo" label-width="80px">
          <el-form-item label="开户行">
            <el-input v-model="bankInfo.bankName" disabled></el-input>
          </el-form-item>
          <el-form-item label="开户支行">
            <el-input v-model="bankInfo.branchName" disabled></el-input>
          </el-form-item>
          <el-form-item label="银行卡号">
            <el-input v-model="bankInfo.cardNumber" disabled></el-input>
          </el-form-item>
          <el-form-item label="开户名">
            <el-input v-model="bankInfo.realname" disabled></el-input>
          </el-form-item>
          <el-form-item label="提现金额">
            <el-input v-model="walletInfo.availableAmount" placeholder="请输入提现金额"></el-input>
          </el-form-item>
          <el-form-item label="手续费">{{0.01*walletInfo.availableAmount}}</el-form-item>
        </el-form>
      </div>
      <el-button class="bb" type="primary" size="mini" @click="recharge">确认提现</el-button>
    </el-card>
  </div>
</template>

<script>
import { addWithdraw } from '@/api/withdraw'
import { getInfo } from '@/api/bindBank'
import service from '~/api'
export default {
  data() {
    return {
      form: {
        name: '哈哈哈'
      }

    }
  },
  async asyncData(context) {
    // 查询银行卡信息
    let bankInfo = await getInfo(context.store.state.user.userInfo.id).then(res => {
      return res.data
    })
    let walletInfo = await service.getWallet(context.store.state.user.userInfo.id).then(res => {
      console.log(res.data)
      return res.data
    })
    let initavaiable = walletInfo.availableAmount
    return { bankInfo: bankInfo, walletInfo: walletInfo, initavaiable: initavaiable }

  }, methods: {
    // 发起提现
    recharge() {
      let withdraw = {
        userId: this.bankInfo.userId, // 用户id
        amount: this.bankInfo.balance, // 数量
        bankName: this.bankInfo.bankName, // 银行名称
        realname: this.bankInfo.realname,// 开户人姓名
        cardNumber: this.bankInfo.cardNumber, // 银行卡号
        branchName: this.bankInfo.branchName // 支行名称
      }
      addWithdraw(withdraw).then(res => {
        if (res.success) {
          this.$message.success('提现成功，请等待运营人员进行审核！')
        } else {
          this.$message.error('提现失败，请稍后重试！')
        }
        // 刷新
        this.$router.go(0)
      })
    }
  }
}
</script>

<style scoped>
.box-card {
  width: 958px;
  height: 666px;
}
.dd {
  width: 500px;
  margin-top: 20px;
}

.bb {
  margin-left: 80px;
}
</style>