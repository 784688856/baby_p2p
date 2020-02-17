<template>
  <div class="panel panel-default">
    <div class="panel-heading">绑定银行卡</div>
    <!-- 银行卡详情面板 Begin -->
    <div id="pnlBankCardDetail" class="panel-body" style>
      <h4 class="text-success">您已经绑定了银行卡</h4>
      <hr />
      <table style="width: 100%;height: 100px;">
        <tbody>
          <tr>
            <td>
              开户名：
              <span class="realname">2323</span>
            </td>
            <td>
              开户行：
              <span class="bankName">中国银行</span>
            </td>
          </tr>
          <tr>
            <td>
              <span>
                银行卡号：
                <span class="cardNumber">32323</span>
              </span>
            </td>
            <td>
              支行名称：
              <span class="branchName">232323</span>
            </td>
          </tr>
          <tr>
            <td>
              <span>
                余额：
                <span class="balance" style="color:red; font-size: 22px; font-weight: bold;">15000元</span>
              </span>
            </td>
            <td></td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- 添加银行卡面板 End -->
    <!-- 银行卡信息添加面板 Begin -->
    <div id="pnlBankCardAdd" style="display: none;">
      <form
        class="form-horizontal"
        id="bankForm"
        method="post"
        action="http://49.232.162.73:8080/user/bankcard/add"
        novalidate="novalidate"
      >
        <fieldset>
          <div class="form-group">
            <p class="text-center text-danger">为保护您账户安全，绑定银行卡之后，不能自己修改，请认真填写！</p>
          </div>
          <div class="form-group">
            <label class="col-sm-4 control-label">开户名</label>
            <div class="col-sm-8">
              <input id="userId" class="form-control" name="userId" type="hidden" value />
              <input id="realname" class="form-control" name="realname" type="text" value />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-4 control-label">银行卡号</label>
            <div class="col-sm-8">
              <input id="cardNumber" class="form-control" name="cardNumber" type="text" value />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-4 control-label">开户行</label>
            <div class="col-sm-8">
              <select class="form-control" name="bankName" size="1">
                <option value="中国银行">中国银行</option>
                <option value="中国工商银行">中国工商银行</option>
                <option value="中国农业银行">中国农业银行</option>
                <option value="中国建设银行">中国建设银行</option>
                <option value="中国招商银行">中国招商银行</option>
                <option value="中国交通银行">中国交通银行</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-4 control-label">支行名称</label>
            <div class="col-sm-8">
              <input id="branchName" class="form-control" name="branchName" type="text" value />
            </div>
          </div>
          <div class="form-group">
            <button
              type="submit"
              id="asubmit"
              class="btn btn-primary col-sm-offset-4"
              data-loading-text="正在提交"
            >
              <i class="icon-ok"></i> 绑定银行卡
            </button>
          </div>
        </fieldset>
      </form>
    </div>
    <!-- 银行卡信息添加面板 End -->
  </div>
</template>

<script>
export default {

}
</script>

<style>
</style>