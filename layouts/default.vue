<template>
  <div class="in-wrap">
    <header>
      <Header />
    </header>
    <!-- 内容区域 -->
    <nuxt />
    <!-- /内容区域 -->

    <!-- 公共底 -->
    <footer class="footer">
      <Footer />
    </footer>
    <!-- /公共底 -->
  </div>
</template>

<script>
import '~/assets/css/reset.css'
import '~/assets/css/theme.css'
import '~/assets/css/global.css'
import '~/assets/css/web.css'
import Header from '@/components/Header'
import Footer from '@/components/Footer'
// import wx from '@/api/ucenter/wx'
export default {
  components: {
    Header, Footer
  },
  data() {
    return {
      jwtToken: '',
      loginInfo: {}
    }
  },
  created() {
  },
  methods: {

  }
}

</script>
<style scoped>
.footer {
  text-align: center;
  position: fixed;
  bottom: 0px;
  margin-left: 38%;
}
</style>