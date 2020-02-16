<template>
  <div>
    <el-row class="nav-bar">
      <el-col :span="12">
        <span class="welcome-text">欢迎您！</span>
      </el-col>
      <el-col :span="12" class="nav-right">
        <span class="nav-right-span">
          <a href="javascript:void(0)" class="phone-a">
            手机版
            <i class="el-icon-arrow-down" />
            <div class="gzh-div">
              <!-- <img src="../../../static/images/xcx-code.jpg"/> -->
            </div>
          </a>
        </span>
        <span class="nav-right-span account-name" v-if="this.$store.state.user.userInfo">
          <nuxt-link to="/center/perinfo" class="account-name-a">
            <i class="account-text">{{this.$store.state.user.userInfo.username}}</i>
            <i class="el-icon-arrow-down" />
          </nuxt-link>
          <ul class="my-ul">
            <li class="my-li">
              <nuxt-link to="/center/perinfo" class="my-a">个人中心</nuxt-link>
            </li>
            <li class="my-li">
              <a class="my-a" herf="javascript:void(0)">评价晒单</a>
            </li>
            <li class="my-li">
              <nuxt-link to="/center/collect" class="my-a">我的收藏</nuxt-link>
            </li>
            <li class="my-li">
              <a class="my-a" @click="loginOut">退出登录</a>
            </li>
          </ul>
        </span>
        <nuxt-link class="account-login-a" to="/login" v-else>登录</nuxt-link>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { removeToken } from '@/utils/auth'
export default {
  name: 'Header',
  data() {
    return {
      isCodeShow: false
    }
  },
  methods: {
    handleDESC(desc) {
      if (desc) {
        let descArray = desc.split("|")
        return descArray.join(" ")
      }
    },
    deleteCartItem(item) {
      this.$store.dispatch('cart/deleteProductItem', item)
    },
    loginOut() {
      // 清除vuex中的token
      this.$store.dispatch('token/LOGIN_OUT_TOKEN')
      // 清除vuex中的用户信息
      this.$store.commit('user/LOGIN_OUT_USERINFO')
      // 清除cookie
      removeToken()
      this.$router.push('/home')
      this.$message({
        message: '您已成功退出Baby投！',
        type: 'success'
      })
    }
  },
  mounted() {
    //    this.userInfo = this.$store.state.user.userInfo
  },
  computed: {
    // RealName() {

    // },
  },
}
</script>

<style lang="scss" scoped>
@import "~/assets/css/variables.scss";
.nav-bar {
  height: 40px;
  line-height: 40px;
  width: 1200px;
  margin: 0 auto;
  color: #999;
  font-size: 12px;
  .welcome-text {
    margin-right: 27px;
  }
  .index-a {
    color: #999;
    &:hover {
      color: $base-color;
    }
  }
  .nav-right {
    .nav-right-span {
      display: block;
      float: right;
      width: 96px;
      text-align: center;
      .phone-a {
        position: relative;
        color: #999;
        &:hover {
          .gzh-div {
            display: block;
          }
        }
        .gzh-div {
          display: none;
          position: absolute;
          top: 30px;
          left: -35px;
          background: #fff;
          width: 130px;
          height: 130px;
          z-index: 10000;
          box-shadow: 0 1px 5px rgba(158, 158, 158, 0.6);
          transition: all 0.3s;
          &::before {
            content: "";
            width: 0;
            height: 0;
            display: block;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-bottom: 6px solid #fff;
            position: absolute;
            top: -5px;
            left: 56px;
          }
          img {
            width: 120px;
            height: 120px;
            vertical-align: top;
            margin-top: 5px;
          }
        }
      }
    }
    .cart {
      position: relative;
      box-sizing: border-box;
      .cart-a {
        cursor: pointer;
        color: $base-color;
        .el-icon-shopping-cart-2 {
          font-size: 14px;
          margin-right: 7px;
        }
      }
      &:hover {
        background-color: #fff;
        border-right: 1px solid #f9f9f9;
        border-left: 1px solid #f9f9f9;
        .cart-show {
          display: block;
        }
      }
      .cart-show {
        display: none;
        position: absolute;
        top: 40px;
        right: 0;
        width: 370px;
        // min-height: 200px;
        background-color: #fff;
        border-top: 1px solid rgba(249, 249, 249, 1);
        box-shadow: 0px 18px 25px 2px rgba(223, 223, 223, 0.61);
        border-radius: 0px 0px 10px 10px;
        z-index: 1000;
        .cart-none {
          width: 370px;
          height: 219px;
          .no-cart-img {
            height: 76px;
            width: 114px;
            margin: 23px auto 15px;
            img {
              height: 76px;
              width: auto;
            }
          }
          .no-cart-text {
            text-align: center;
            font-size: 14px;
            color: #999;
          }
          .no-cart-btn {
            .no-cart-btn-a {
              display: block;
              height: 40px;
              line-height: 40px;
              width: 150px;
              color: #fff;
              text-align: center;
              font-size: 14px;
              border-radius: 20px;
              margin: 29px auto 0;
              background: linear-gradient(
                90deg,
                rgba(255, 78, 80, 1) 0%,
                rgba(252, 145, 58, 1) 100%
              );
            }
          }
        }
        .cart-have {
          width: 370px;
          .cart-have-ul {
            .cart-have-li {
              height: 134px;
              padding-top: 23px;
              padding-left: 22px;
              text-align: left;
              border-bottom: 1px solid #f6f6f6;
              box-sizing: border-box;
              .li-img {
                display: block;
                float: left;
                width: 90px;
                height: 90px;
                cursor: pointer;
                img {
                  height: 90px;
                  width: auto;
                }
              }
              .li-text {
                float: left;
                width: 258px;
                height: 90px;
                padding-left: 24px;
                box-sizing: border-box;
                .top {
                  height: 30px;
                  line-height: 30px;
                  font-size: 14px;
                  .title {
                    display: inline-block;
                    color: #333;
                    width: 188px;
                    @include text-overflow();
                    i {
                      cursor: pointer;
                      font-style: normal;
                    }
                  }
                  .icon-delete {
                    cursor: pointer;
                    display: inline-block;
                    height: 30px;
                    line-height: 30px;
                    width: 30px;
                    text-align: center;
                    vertical-align: top;
                  }
                }
                .desc {
                  height: 30px;
                  line-height: 30px;
                  font-size: 12px;
                  color: #999;
                  padding-right: 22px;
                  box-sizing: border-box;
                  @include text-overflow();
                }
                .bottom {
                  height: 30px;
                  line-height: 30px;
                  font-size: 14px;
                  .price {
                    color: #ff2f47;
                  }
                  .num {
                    color: #999;
                    margin-left: 9px;
                  }
                }
              }
            }
          }
          .cart-have-bottom {
            font-size: 14px;
            height: 66px;
            line-height: 66px;
            .cart-num {
              display: block;
              float: left;
              color: #333;
              margin-left: 22px;
            }
            .cart-price {
              display: block;
              float: left;
              margin-left: 20px;
              color: #ff2f47;
            }
            .go-to-settle {
              display: block;
              float: right;
              width: 100px;
              height: 35px;
              line-height: 35px;
              background: linear-gradient(
                90deg,
                rgba(255, 78, 80, 1) 0%,
                rgba(252, 145, 58, 1) 100%
              );
              border-radius: 18px;
              color: #fff;
              text-align: center;
              margin-top: 15px;
              margin-right: 22px;
            }
          }
        }
      }
    }
    .account-name {
      position: relative;
      width: 110px;
      height: 40px;
      box-sizing: border-box;
      .account-name-a {
        display: block;
        color: #999;
        height: 40px;
        .account-text {
          display: inline-block;
          font-style: normal;
          width: 70px;
          @include text-overflow();
        }
        .el-icon-arrow-down {
          vertical-align: 14px;
          display: inline-block;
          transition: all 0.3s ease-in-out;
        }
      }

      &:hover {
        background-color: #fff;
        border-right: 1px solid #e5e5e5;
        border-left: 1px solid #e5e5e5;
        box-shadow: 0px 11px 25px 2px rgba(223, 223, 223, 0.61);
        .my-ul {
          display: block;
          padding-bottom: 9px;
        }
        .el-icon-arrow-down {
          color: $base-color;
          &::before {
            display: inline-block;
            transition: transform 0.3s;
            transform: rotate(180deg);
          }
        }
      }
      .my-ul {
        position: absolute;
        display: none;
        top: 40px;
        left: -1px;
        width: 110px;
        border-right: 1px solid #e5e5e5;
        border-left: 1px solid #e5e5e5;
        border-bottom: 1px solid #e5e5e5;
        background-color: #fff;
        border-radius: 0px 0px 6px 6px;
        box-shadow: 0px 11px 25px 2px rgba(223, 223, 223, 0.61);
        z-index: 1000;
        box-sizing: border-box;
        .my-li {
          height: 30px;
          line-height: 30px;
          .my-a {
            font-size: 12px;
            color: #999;
            text-align: center;
            cursor: pointer;
            display: block;
            &:hover {
              color: $base-color;
            }
          }
        }
      }
    }
    .el-icon-arrow-down {
      margin-left: 3px;
    }
    .order-a {
      color: #999;
      &:hover {
        color: $base-color;
      }
    }
  }
  .account-login-a {
    display: block;
    float: right;
    color: #999;
    &:hover {
      color: #ff2f47;
    }
  }
}
</style>


