<template>
  <div class="container">
    <h1 v-if="error.statusCode === 404">你居然敢访问黄色网站！！</h1>
    <h1 v-else>不晓得出什么问题的</h1>
  </div>
</template>

<script>
export default {
  props: ['error'],
  layout: 'common' // 你可以为错误页面指定自定义的布局
}
</script>
<style scoped>
.container {
  background-color: yellow;
}
</style>