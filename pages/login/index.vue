<template>
  <div class="login-box">
    <div class="login-header">
      <div class="header-content">
        <nuxt-link class="logo-a" to="/" alt="Baby投">
          <!-- <img src="../static/images/aLogo.png" /> -->
        </nuxt-link>
      </div>
    </div>
    <div class="login-content clearfix">
      <div class="lc-rigth">
        <!-- <img src="../static/images/login-img.jpg" /> -->
      </div>
      <div class="lc-left">
        <div class="login-form">
          <el-form :model="loginInfo" :rules="login_rules" ref="loginRuleForm" class="clearfix">
            <h1 class="login-title">账号登录</h1>
            <el-form-item class="input_item" label prop="username">
              <el-input v-model="loginInfo.username" class="user-name" placeholder="账号" clearable></el-input>
            </el-form-item>
            <el-form-item class="input_item" label prop="password">
              <el-input
                v-model="loginInfo.password"
                class="user-pass"
                placeholder="密码"
                @keydown.13.native="verifyForm"
                show-password
                clearable
              ></el-input>
            </el-form-item>
            <!-- <el-form-item class="input_item" label="" prop="numbercode">
                        <div class="code-div">
                            <el-input v-model="loginInfo.numbercode" class="user-code" placeholder="验证码" :maxlength="4"  clearable></el-input>
                            <span class="sever-code">6487</span>
                        </div>
            </el-form-item>-->
            <div class="remember-div clearfix">
              <el-checkbox v-model="isRememberName" class="rd-user">记住用户名</el-checkbox>
              <!-- <el-checkbox v-model="isAutoLogin" class="rd-login">两周内自动登录</el-checkbox> -->
            </div>
            <div
              class="login-btn double-click-none"
              :class="{'forbidLogin': loginStatus.loginIng === true}"
              @click="verifyForm()"
            >{{loginStatus.submitText}}</div>
            <a href="javascript:void(0)" @click="tipNo" class="forget-pass">忘记密码？</a>
            <p class="login-tip">
              <i class="icon-tip" />如出现账号不存在，或者登陆失败请与在线客服联系！
            </p>
          </el-form>
        </div>
      </div>
    </div>

    <div class="login-footer">
      <div class="footer-content">
        <p class="copyright-top">Copyright © 2013 - 2019 Skyworth. All Rights Reserved.</p>
        <p class="copyright-bottom">
          Baby投集团公司版权所有&nbsp;&nbsp;&nbsp;
          <a
            href="http://beian.miit.gov.cn/"
            target="_blank"
          >粤ICP备09157852号</a>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import service from '~/api'
import md5 from 'js-md5'
import { getToken, setToken, removeToken } from '@/utils/auth'
export default {

  name: 'login',
  layout: 'public',
  data() {
    var check_username = (rule, value, callback) => {
      if (value === '') {
        return callback(new Error('请输入账户名'));
      } else {
        callback()
      }
    }
    var check_password = (rule, value, callback) => {
      if (value === '') {
        return callback(new Error('请输入密码'));
      } else {
        callback()
      }
    }
    // var check_numbercode = (rule, value, callback) => {
    //     if (value === '') {
    //       return callback(new Error('请输入验证码'));
    //     }else {
    //       callback()
    //     }
    // }
    return {
      loginInfo: {
        username: '',
        password: '',
      },
      isRememberName: true,
      isAutoLogin: true,
      loginStatus: {
        submitText: '登录',
        loginIng: false
      },
      login_rules: { // 登录验证
        username: [
          { validator: check_username, trigger: 'blur' },
        ],
        password: [
          { validator: check_password, trigger: 'blur' }
        ]
        // numbercode: [
        // { validator: check_numbercode, trigger: 'blur' }
        // ]
      },
    }
  },
  methods: {
    tipNo() {
      this.$notify({
        title: '温馨提示',
        message: '此功能暂未开放，敬请期待！',
        type: 'warning'
      })
    },
    verifyForm() {
      this.$refs['loginRuleForm'].validate((valid) => {
        if (valid) {
          this.handleUserLogin()
        } else {
          return false
        }
      });
    },
    handleUserLogin() {
      if (this.loginStatus.loginIng) {
        return
      }
      this.loginStatus.submitText = "登录中..."
      this.loginStatus.loginIng = true
      let rememberInfo = {
        status: this.isRememberName,
        name: this.loginInfo.username
      }
      // 处理记住账号  
      this.$store.commit('user/setRemenberUserName', rememberInfo)
      // 让self指带this，防止防止某些方法中拿不到this对象
      let self = this
      // let params = {
      //   sign: this.$store.state.token.token,
      //   userid: this.$store.state.token.userid,
      //   pwd: this.$store.state.token.pwd
      // }
      service.login(this.loginInfo).then(res => {
        if (res.success) { //登录正常
          self.$message.success('欢迎您登录Baby投！')
          // let userToken = md5('h53c1b09eea74403b24b11db1c08d72f' + res.MemberId + 'h53c1b09eea74403b24b11db1c08d72f')
          let userToken = res.data.id
          // 存储token到store中
          self.$store.dispatch('token/loginSucSetToken', { userToken: userToken, userid: res.id, pwd: res.password })
          // store每次刷新就没了，讲token也存一份到cookie保存
          setToken(userToken)
          // 通过token去服务端获取用户信息
          service.getMember(userToken).then(mRes => {
            if (mRes.success) {
              // 存入用户信息到vuex
              self.$store.commit('user/setUserInfo', mRes.data)
              this.$router.replace("/home")
            }
          })
        } else { // 登录异常
          this.$message.error(res.msg)
          this.loginStatus.submitText = "登录"
          this.loginStatus.loginIng = false
        }
      })
    }
  },
  mounted() {
    // if( sessionStorage.getItem("user")) {
    //     this.$router.back()
    // }
    //处理记住账号
    this.$store.commit('user/initRememberUser')
    this.loginInfo.username = this.$store.state.user.userNameInfo.name
    this.isRememberName = this.$store.state.user.userNameInfo.status
    let tmpTag = 'https:' === document.location.protocol ? false : true;
    if (tmpTag) {
      let urls = window.location.href;
      if (urls.indexOf('localhost') === -1) {
        urls = urls.replace("http", "https");
        window.location.href = urls;
      }
    }
  }
}
</script>
<style  lang="scss" scoped>
.w193 {
  width: 193px;
}
.w188 {
  width: 188px;
}
.w376 {
  width: 376px;
}
.bgf8 {
  background-color: #f8f8f8;
}
.login-box {
  width: 100%;
  height: 100%;
  background-color: #f0f0f0;
  .login-header {
    width: 100%;
    height: 100px;
    background-color: #fff;
    .header-content {
      position: relative;
      width: 1000px;
      height: 100px;
      margin: 0 auto;
      overflow: hidden;
      .logo-a {
        position: absolute;
        left: 0;
        top: 25px;
        display: block;
        width: 183px;
        height: 51px;
        img {
          width: 100%;
          height: 100%;
        }
      }
    }
  }
  .login-content {
    width: 1000px;
    height: 502px;
    margin: 65px auto 20px;
    box-shadow: 0px 8px 23px 0px rgba(226, 226, 226, 1);
    border-radius: 15px;
    background-color: #fff;
    .lc-rigth {
      width: 526px;
      height: 437px;
      float: left;
      margin: 30px 0 35px 30px;
      img {
        width: 526px;
        height: 437px;
      }
    }
    .lc-left {
      width: 444px;
      height: 432px;
      float: left;
      margin: 35px 0;
      .login-form {
        width: 305px;
        margin: 0 auto;
        text-align: center;
        .login-title {
          text-align: center;
          font-size: 18px;
          color: #333;
          margin-top: 19px;
        }
        .user-name {
          margin-top: 35px;
        }
        // .user-pass {
        //     margin-top: 15px;
        // }
        .code-div {
          height: 40px;
          // margin-top: 15px;
          .user-code {
            display: inline-block;
            width: 180px;
          }
          .sever-code {
            display: inline-block;
            width: 100px;
            height: 40px;
            margin-left: 15px;
            color: #999;
            font-size: 14px;
            text-align: center;
            line-height: 40px;
          }
        }
        .remember-div {
          margin-top: 18px;
          .rd-user {
            float: left;
          }
          .rd-login {
            float: right;
          }
        }
        .login-btn {
          width: 305px;
          height: 40px;
          background: linear-gradient(
            90deg,
            rgba(255, 78, 80, 1),
            rgba(252, 145, 58, 1)
          );
          border-radius: 20px;
          text-align: center;
          line-height: 40px;
          color: #fff;
          font-size: 14px;
          cursor: pointer;
          margin-top: 38px;
        }
        .forget-pass {
          display: inline-block;
          margin-top: 11px;
          font-size: 14px;
          color: #999;
        }
        .login-tip {
          position: relative;
          font-size: 14px;
          color: #999;
          margin-top: 14px;
          text-align: left;
          padding-left: 20px;
          .icon-tip {
            position: absolute;
            top: 3px;
            left: 1px;
            font-size: 14px;
            color: #ff2f47;
          }
        }
      }
    }
  }
  .login-guide {
    width: 1000px;
    min-height: 330px;
    background-color: #fff;
    margin: 0 auto 70px;
    box-shadow: 0px 8px 23px 0px rgba(226, 226, 226, 1);
    border-radius: 15px;
    .guide-header {
      font-size: 16px;
      color: #333333;
      padding: 30px 0 34px 31px;
    }
    .elTable {
      &::before {
        bottom: -1px;
        background-color: #fff;
      }
    }
    .guide-table {
      width: 940px;
      margin: 0 auto;
      padding-bottom: 60px;
      dl {
        color: #666;
        font-size: 14px;
        dt {
          height: 40px;
        }
        dd {
          height: 40px;
        }
        .span-common {
          display: block;
          float: left;
          line-height: 40px;
        }
      }
    }
  }
  .login-footer {
    height: 100px;
    width: 100%;
    background-color: #f9f9f9;
    .footer-content {
      width: 1000px;
      height: 100px;
      margin: 0 auto;
      text-align: center;
      color: #333;
      font-size: 14px;
      box-sizing: border-box;
      padding: 24px 0;
      line-height: 26px;
    }
    a {
      color: #333;
    }
  }
}
// .remember-div /deep/ .el-checkbox__input.is-checked+.el-checkbox__label {
//     color: #FF2F47;
// }
// .remember-div  /deep/ .el-checkbox__input.is-checked .el-checkbox__inner, .el-checkbox__input.is-indeterminate .el-checkbox__inner {
//     background-color: #FF2F47;
//     border-color: #FF2F47;
// }
// .remember-div  /deep/ .el-checkbox__input.is-focus .el-checkbox__inner , .cart-have /deep/ .el-checkbox__inner:hover{
//     border-color: #FF2F47 !important;
// }
.forbidLogin {
  cursor: not-allowed !important;
}
</style>

