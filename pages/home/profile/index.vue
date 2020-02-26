<template>
  <div>
    <el-card class="box-card" style="width: 960px">
      <div>
        <div class="text">
          <div class="demo-basic--circle">
            <el-avatar shape="square" :size="150" :src="userInfo.avatar" class="div-left" />
            <div class="div-right">
              <div>
                <h5>用户名：{{this.$store.state.user.userInfo.username}}</h5>
                <br />
                <h5>最后登录时间：</h5>
                <br />
              </div>
              <div>
                <el-button type="primary" size="mini">
                  <nuxt-link to="/home/profile/recharge">
                    <span class="tt">账户充值</span>
                  </nuxt-link>
                </el-button>
                <el-button type="danger" size="mini">
                  <nuxt-link to="/home/profile/withdraw">
                    <span class="tt">账户提现</span>
                  </nuxt-link>
                </el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-card>
    <el-card class="box-card b2">
      <li>
        <dl>
          <span class="text">账户总额：{{walletInfo.availableAmount+walletInfo.freezeAmount}}</span>&nbsp;
          <span class="text">可用金额：{{walletInfo.availableAmount}}元</span>&nbsp;
          <span class="text">冻结金额：{{walletInfo.freezeAmount}}元</span>
        </dl>
        <dl>
          <span class="text">代收利息：{{walletInfo.interestPending}}元</span>&nbsp;
          <span class="text">代收本金：{{walletInfo.principalPending}}元</span>&nbsp;
          <span class="text">待还金额：{{walletInfo.repaidAmount}}元</span>
        </dl>
      </li>
    </el-card>
    <el-card>
      <div class="xxx">
        <i class="el-icon-user icon"></i>
        实名认证
        <h6 v-if="userInfo.realname">
          已认证
          <el-button class="el-button–mini">
            <nuxt-link to="/home/profile/myprofile" style="color: white">查看</nuxt-link>
          </el-button>
        </h6>
        <h6 v-else>
          <nuxt-link to="#">去认证</nuxt-link>
        </h6>
        <h6>实名认证后才能在平台投资</h6>
      </div>

      <div class="xxx">
        <i class="el-icon-phone icon"></i>手机认证
        <h6 v-if="userInfo.phoneNumber">
          已认证
          <el-button class="el-button–mini">
            <nuxt-link to="/home/profile/myprofile" style="color: white;">查看</nuxt-link>
          </el-button>
        </h6>
        <h6 v-else>
          <nuxt-link to="#">去认证</nuxt-link>
        </h6>
        <h6>实名认证后才能在平台投资</h6>
      </div>
      <div class="xxx">
        <i class="el-icon-s-management icon"></i>邮箱认证
        <h6 v-if="userInfo.email">
          已认证
          <el-button class="el-button–mini">
            <nuxt-link to="/home/profile/myprofile" style="color: white;">查看</nuxt-link>
          </el-button>
        </h6>
        <h6 v-else>
          <el-button class="el-button–mini" @click="checkEmail">去认证</el-button>
        </h6>
        <h6>实名认证后才能在平台投资</h6>
      </div>
      <div class="xxx" style="margin-top: 20px;">
        <i class="el-icon-s-promotion icon"></i>VIP会员
        <h6 v-if="false">
          普通用户
          <el-button class="el-button–mini">
            <nuxt-link to="#" style="color: white">查看</nuxt-link>
          </el-button>
        </h6>
        <h6 v-else>
          VIP
          <el-button class="el-button–mini">
            <nuxt-link to="#" style="color: white">查看</nuxt-link>
          </el-button>
        </h6>

        <h6>实名认证后才能在平台投资</h6>
      </div>
    </el-card>

    <el-dialog title="暴躁提示：" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
      <el-form ref="form" :model="emailInfo" label-width="80px">
        <el-form-item label="新邮箱">
          <el-input v-model="emailInfo.newEmail" placeholder="请输入有效的邮箱"></el-input>
        </el-form-item>
        <el-form-item label="验证码">
          <el-input v-model="emailInfo.code" placeholder="请输入验证码" style="width: 54%"></el-input>
          <el-button
            icon="el-icon-mobile-phone"
            @click="send"
            style="width: 44%"
            type="success"
            :disabled="disabled=!show"
          >
            <span v-show="show">获取验证码</span>
            <span v-show="!show" class="count">{{count}} s</span>
          </el-button>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">绑定邮箱</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import service from '~/api'
import { bandEmail, checkEmail } from '@/api/email'
export default {
  data() {
    return {
      dialogVisible: false,
      emailInfo: {
        newEmail: '',
        code: '',
        id: '',
        userId: ''
      },
      show: true,  // 初始启用按钮
      count: '',   // 初始化次数
      timer: null,
    }
  },
  // nuxt服务端请求数据
  async  asyncData(context) {
    // 登录后用户基本信息存到了vuex中，通过vuex拿到id去获取钱包信息
    let walletInfo = await service.getWallet(context.store.state.user.userInfo.id).then(res => {
      return res.data
    })
    // 查询用户详细信息
    let userInfo = await service.getDetailInfo(context.store.state.user.userInfo.id).then(res => {
      console.log(res)
      return res.data
    })
    return { userInfo: userInfo, walletInfo: walletInfo }
  },
  created() {
    service.getDetailInfo(this.$store.state.user.userInfo.id).then(res => {
      // 返回用户详细信息中的头像
      this.squareUrl = res.data.avatar

    })
  },
  methods: {
    checkEmail() {
      this.dialogVisible = true
    },
    send() {
      let TIME_COUNT = 60
      if (!this.timer) {
        this.count = TIME_COUNT;
        this.show = false;
        this.timer = setInterval(() => {
          if (this.count > 0 && this.count <= TIME_COUNT) {
            this.count--;
          } else {
            this.show = true;
            clearInterval(this.timer);  // 清除定时器
            this.timer = null;
          }
        }, 1000)
      }
      // 发送邮箱给后端
      bandEmail(this.emailInfo).then(res => {
        this.emailInfo.userId = this.$store.state.user.userInfo.id
        this.emailInfo.id = res.data
      })


    },
    onSubmit() {
      checkEmail(this.emailInfo).then(res => {
        console.log(res)
        if (res.success) {
          this.$message.success('绑定成功！')
        } else {
          this.$message.error('绑定失败，请稍后重试！')
        }
        // 刷新
        this.$router.go(0)
      })

    },
    rand(min, max) {
      return Math.floor(Math.random() * (max - min)) + min;
    },
    handleClose() {
      this.dialogVisible = false
    }
  }

}
</script>


<style scoped >
dl {
  /* margin-top: 80px; */
  /* text-align: center */
}

.text {
  /* margin-left: 60px; */
  /* font-size: 20px; */
}

.tt {
  color: black;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}

.div-left {
  float: left;
  margin-bottom: 20px;
}

.div-right {
  float: left;
  margin-top: 20px;
  margin-top: 20px;
  margin-left: 50px;
}

.b2 {
  height: 150px;
  width: 960px;
}
.b3 {
  height: 273px;
  width: 960px;
}

.xxx {
  width: 180px;
  height: 100px;
  background-color: rgb(235, 227, 227);
  display: inline-block;
  margin-left: 93px;
  text-align: center;
  padding-top: 32px;
}

.icon {
  font-size: 35px;
}

.el-button–mini {
  color: #fff;
  background-color: #fc813b;
  border-color: #fc813b;
  padding: 2px 5px;
}
.el-button–mini:hover {
  background-color: #ef9e6f;
  border-color: #ef9e6f;
}
.el-button–mini:focus {
  background-color: #ef9e6f;
  border-color: #ef9e6f;
}

.count {
  color: red;
}
</style>

