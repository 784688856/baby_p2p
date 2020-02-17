<template>
  <div>
    <div class="panel panel-default">
      <div class="panel-body el-account">
        <div class="el-account-info">
          <div class="pull-left el-head-img">
            <img
              class="icon"
              id="imgAvatar"
              width="180"
              height="180"
              src="http://49.232.162.73:8080/avatar/nobody.jpg"
            />
          </div>
          <div class="pull-left el-head">
            <p>
              用户名：
              <span class="username">lbwnb</span>
            </p>
            <p>
              最后登录时间：
              <span class="lastLoginTime">2020-02-17 11:28:00</span>
            </p>
          </div>
          <div
            class="pull-left"
            style="padding-left: 20px; width: 400px;margin:30px auto 0px auto;"
          >
            <a class="btn btn-primary btn-lg" href="recharge.html">账户充值</a>
            &emsp;
            <a class="btn btn-danger btn-lg" href="withdraw.html">账户提现</a>
          </div>
          <div class="clearfix"></div>
        </div>

        <div class="row h4 account-info">
          <div class="col-sm-4">
            账户总额：
            <span id="totalAmount" class="text-primary">4930.35元</span>
          </div>
          <div class="col-sm-4">
            可用金额：
            <span id="availableAmount" class="text-primary">4930.35元</span>
          </div>
          <div class="col-sm-4">
            冻结金额：
            <span id="freezeAmount" class="text-primary">0元</span>
          </div>
        </div>

        <div class="row h4 account-info">
          <div class="col-sm-4">
            待收利息：
            <span id="interestPending" class="text-primary">0元</span>
          </div>
          <div class="col-sm-4">
            待收本金：
            <span id="principalPending" class="text-primary">0元</span>
          </div>
          <div class="col-sm-4">
            待还本息：
            <span id="repaidAmount" class="text-primary">0元</span>
          </div>
        </div>

        <div class="row h4 account-info">
          <div class="col-sm-4">
            授信额度：
            <span id="creditLine" class="text-primary">11000元</span>
          </div>
          <div class="col-sm-4">
            剩余授信额度：
            <span id="residualCreditLine" class="text-primary">10010元</span>
          </div>
          <div class="col-sm-4">
            信用得分：
            <span id="creditScore" class="text-primary">110分</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
      sizeList: ["large", "medium", "small"]
    }
  }
}
</script>

