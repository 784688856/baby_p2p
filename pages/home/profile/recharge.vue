<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h1 class="formspan">账户充值</h1>
        <h3>1. 由于银行系统的限制，充值功能建议使用 IE 内核（IE8及以上）的浏览器。</h3>
        <h3>2. 请注意：为打击信用卡套现行为，充值资金必须经过投资回款后方能允许提现。</h3>
      </div>
      <div class="dd">
        <el-form ref="form" :model="bankInfo" label-width="80px">
          <el-form-item label="银行">
            <el-input v-model="bankInfo.bankName"></el-input>
          </el-form-item>
          <el-form-item label="充值金额">
            <el-input v-model="bankInfo.balance" placeholder="当前余额："></el-input>
          </el-form-item>
          <el-form-item label="说明">
            <el-input v-model="bankInfo.remark" placeholder="请输入充值说明"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <el-button class="bb" type="primary" size="mini" @click="recharge">确认充值</el-button>
    </el-card>
  </div>
</template>

<script>
import { add } from '@/api/recharge'
import { getInfo } from '@/api/bindBank'
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
    let res = await getInfo(context.store.state.user.userInfo.id).then(res => {
      return res
    })
    return { bankInfo: res.data }

  }, methods: {
    // 发起充值
    recharge() {
      let recharge = {
        userId: this.bankInfo.userId, // 用户id
        username: this.$store.state.user.userInfo.username, // 用户名
        bankCardId: this.bankInfo.cardNumber, // 银行卡号
        amount: this.bankInfo.balance, // 数量
        remark: this.bankInfo.remark // 备注
      }
      add(recharge).then(res => {
        if (res.success) {
          this.$message.success('充值成功，请等待运营人员进行审核！')
        } else {
          this.$message.error('充值失败，请稍后重试！')
        }
        this.$router.replace("/home/profile/rechargeList")
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
}

.bb {
  margin-left: 80px;
}
</style>