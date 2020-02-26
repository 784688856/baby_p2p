<template>
  <div class="person-center">
    <div class="person-header">个人信息</div>
    <div class="base-section">
      <div class="avatar fl">
        <img class="avatar-img" :src="userInfo.avatar" />
        <el-upload
          class="avatar-uploader"
          action
          :multiple="false"
          :show-file-list="false"
          :http-request="avatarUploadSuccessHandler"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <a class="avatar-a-btn" href="javascript:void(0)">编辑头像</a>
        </el-upload>
      </div>
      <div class="base-text fl">
        <dl>
          <dt>基本信息</dt>
          <dd>昵称：{{this.$store.state.user.userInfo.username}}</dd>
          <dd>真实姓名：{{userInfo.realname}}</dd>
          <dd>身份证号：{{userInfo.idCardNumber}}</dd>
          <dd>手机号码：{{userInfo.phoneNumber}}</dd>
          <dd>个人学历：{{userInfo.eduBackgroundId}}</dd>
          <dd>年收入：{{userInfo.incomeLevelId}}</dd>
          <!-- <dd>婚姻情况：{{userInfo.marriageId}}</dd> -->
          <dd>住房条件：{{userInfo.houseConditionId}}</dd>
          <dd>邮箱：{{userInfo.email}}</dd>
        </dl>
      </div>
      <div class="oprator">
        <a href="javascript:void(0)" class="base-a" @click="edit">
          <i class="el-icon-edit-outline" />编辑
        </a>
      </div>
    </div>

    <el-dialog title="更新个人信息" class="user-dialog" :visible.sync="updateInfoDialog" width="600px">
      <div class="address-dialog—content">
        <el-form
          ref="infoForm"
          :model="infoForm"
          :rules="info_rules"
          label-width="75px"
          class="clearfix"
        >
          <!-- <el-form-item label="昵称" class="np-item" prop="common">
            <el-input v-model="infoForm.realname" placeholder="请输入昵称"></el-input>
          </el-form-item>-->
          <el-form-item label="真实姓名" class="np-item" prop="common">
            <el-input v-model="infoForm.realname" placeholder="请输入真实姓名"></el-input>
          </el-form-item>
          <el-form-item label="身份证号" class="np-item" prop="common">
            <el-input v-model="infoForm.idCardNumber" placeholder="请输入身份证号"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" class="np-item" prop="common">
            <el-input v-model="infoForm.phoneNumber" placeholder="请输入您的手机号码"></el-input>
          </el-form-item>
          <!-- <el-form-item label="电子邮箱" class="np-item" prop="Email">
            <el-input v-model="infoForm.Email" placeholder="请输入您的电子邮箱"></el-input>
          </el-form-item>-->
          <el-form-item label="个人学历" class="np-item" prop="common">
            <el-input v-model="infoForm.eduBackgroundId" placeholder="请输入您的个人学历"></el-input>
          </el-form-item>
          <el-form-item label="年收入" class="np-item" prop="common">
            <el-input v-model="infoForm.incomeLevelId" placeholder="请输入您的年收入"></el-input>
          </el-form-item>
          <el-form-item label="婚姻情况" class="np-item" prop="common">
            <el-input v-model="infoForm.marriageId" placeholder="请输入您的婚姻情况"></el-input>
          </el-form-item>
          <el-form-item label="住房条件" class="np-item" prop="common">
            <el-input v-model="infoForm.houseConditionId" placeholder="请输入您的住房条件"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer clearfix">
        <span class="dialog-btn save double-click-none" @click="updateUserInfoData()">保存</span>
        <span class="dialog-btn cancel double-click-none" @click="updateInfoDialog = false">取消</span>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import service from '~/api'
export default {
  name: 'PersonInformation',
  data() {
    var check_phone = (rule, value, callback) => {
      if (value != '') {
        const regMobile = /^1[0-9]{10}$/
        if (!regMobile.test(value)) {
          return callback(new Error('手机号码格式错误'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    var check_email = (rule, value, callback) => {
      if (value != '') {
        const regEmail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/
        if (!regEmail.test(value)) {
          return callback(new Error('Email格式错误'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    return {
      userInfo: '',
      imageUrl: '',
      updateInfoDialog: false,
      infoForm: '',
      info_rules: { // 登录验证  
        Mobile: [
          { validator: check_phone, trigger: 'blur' },
        ],
        Email: [
          { validator: check_email, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    edit() {
      // 通过Json使得值传递，不穿的引用
      this.infoForm = JSON.parse(JSON.stringify(this.userInfo));
      this.updateInfoDialog = true

    },
    updateUserInfoData() {
      let that = this
      this.$refs['infoForm'].validate((valid) => {
        if (valid) { // 校验通过
          this.handleUpdateUserInfoData()
        } else {
          return false;
        }
      });
    },
    handleUpdateUserInfoData() {
      service.updateMember(this.infoForm).then(res => {
        if (res.success) {
          this.$store.commit('user/updateUserInfo', this.infoForm)
          this.$message.success('更新成功!')
          this.updateInfoDialog = false
          this.$router.go("0")

        } else {
          this.$message.error(res.msg)
        }
      })
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isPNG = file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!(isJPG || isPNG)) {
        this.$message.error('上传头像图片需是 jpeg/png 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return (isJPG || isPNG) && isLt2M
    },
    avatarUploadSuccessHandler(event) {
      let that = this
      let formData = new FormData()
      formData.append("file", event.file)
      //图片提交服务器
      service.uploadFilesSave(formData).then(res => {
        console.log(res.data)
        this.userInfo.avatar = res.data.url

        // that.$store.dispatch('user/updatedUserInfoAvatar', headUrl)

      });
    },
    handleAvatarSuccess(res, file) {
      console.log('666')
    },
  },
  mounted() {

  },
  created() {
    // 返回用户详细信息
    service.getDetailInfo(this.$store.state.user.userInfo.id).then(res => {
      console.log(res.data)
      this.userInfo = res.data
    })
  },
}

</script>

<style lang="scss" scoped>
.person-center {
  height: 600px;
  .person-header {
    height: 88px;
    line-height: 88px;
    color: #333;
    font-size: 18px;
    padding-left: 25px;
  }
  .base-section {
    margin-top: 120px;
    .avatar {
      width: 140px;
      margin-left: 102px;
      text-align: center;
      .avatar-img {
        width: 140px;
        height: 140px;
        border-radius: 70px;
      }
      .avatar-a-btn {
        margin-top: 34px;
        display: block;
        color: #333;
      }
    }
    .base-text {
      margin-left: 133px;
      width: 450px;
      dl {
        color: #333;
        dt {
          font-size: 16px;
          margin-bottom: 36px;
        }
        dd {
          font-size: 14px;
          margin-bottom: 29px;
        }
      }
    }
    .oprator {
      .base-a {
        color: #333;
        font-size: 14px;
        .el-icon-edit-outline {
          color: #4091ff;
        }
      }
    }
  }
}
.user-dialog {
  & /deep/ .el-dialog__footer {
    padding: 0 20px 40px !important;
  }
  .dialog-btn {
    display: block;
    float: left;
    width: 160px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    font-size: 14px;
    border-radius: 20px;
    cursor: pointer;
  }
  .save {
    color: #fff;
    background: linear-gradient(
      90deg,
      rgba(255, 78, 80, 1),
      rgba(252, 145, 58, 1)
    );
    margin-left: 106px;
    margin-right: 26px;
  }
  .cancel {
    background-color: #ededed;
    color: #333;
  }
}
</style>
