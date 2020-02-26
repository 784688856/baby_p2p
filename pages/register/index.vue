<template >
  <div class="d1">
    <el-form ref="form" :model="form" label-width="150px">
      <el-form-item label="请填写用户信息" />

      <el-form-item label="用户名">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input v-model="form.password2"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">同意协议并注册</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import service from '~/api'
export default {
  data() {
    return {
      form: {
        username: '',
        password: '',
        password2: ''
      }
    }
  },
  methods: {
    onSubmit() {
      service.registerMember(this.form).then(res => {

        if (res.success) {
          this.$message.success('注册成功!')
           this.$router.replace("/home")
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.d1 {
  width: 400px;
  margin-left: 450px;
}
</style>