<template>
  <div>
    <section class="container">
      <h1 id="logo">
        <a href="#" title="Baby投">
          <img src="~/assets/img/logo.png" width="100%" alt="Baby投" />
        </a>
      </h1>
      <div class="h-r-nsl">
        <ul class="nav">
          <nuxt-link to="/home" tag="li" >
            <a>首页</a>
          </nuxt-link>
          <nuxt-link to="/home/investment" tag="li" active-class="current">
            <a>我要投资</a>
          </nuxt-link>
          <nuxt-link to="/home/loan" tag="li" active-class="current">
            <a>我要借款</a>
          </nuxt-link>
          <nuxt-link to="/home/profile" tag="li" active-class="current">
            <a>个人中心</a>
          </nuxt-link>
        </ul>
      </div>
      <!-- 清除浮动还是什么，此代码很重要，否则格式都错乱啦 -->
      <div class="clear" />
    </section>
    <!-- 嵌套入口 -->
    <nuxt />
  </div>
</template>

<script>
export default {
  
}
</script>

<style>
</style>