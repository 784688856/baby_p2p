<template>
  <div class="person-center">
    <div class="person-header">个人信息</div>
    <div class="base-section clearfix">
      <div class="avatar fl">
        <img class="avatar-img" :src="userInfo.HeadUrl" />
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
          <dd>昵称：{{userInfo.RealName}}</dd>
          <dd>性别：{{userInfo.Sex}}</dd>
          <dd>我的生日：{{userInfo.Birthday}}</dd>
          <dd>我的手机：{{userInfo.Mobile}}</dd>
          <dd>电子邮箱：{{userInfo.Email}}</dd>
          <dd>公司名称：{{userInfo.GName}}</dd>
          <dd>我的工号：{{userInfo.SKYNO}}</dd>
        </dl>
      </div>
      <div class="oprator">
        <a href="javascript:void(0)" class="base-a" @click="updateInfoDialog = true">
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
          <el-form-item label="我的姓名" class="np-item" prop="common">
            <el-input v-model="infoForm.RealName" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="我的性别" class="np-item" prop="common">
            <el-radio-group v-model="infoForm.Sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="我的生日" class="np-item" prop="common">
            <el-date-picker
              v-model="infoForm.Birthday"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="我的手机" class="np-item" prop="Mobile">
            <el-input v-model="infoForm.Mobile" placeholder="请输入您的手机号码"></el-input>
          </el-form-item>
          <el-form-item label="电子邮箱" class="np-item" prop="Email">
            <el-input v-model="infoForm.Email" placeholder="请输入您的电子邮箱"></el-input>
          </el-form-item>
          <el-form-item label="公司名称" class="np-item" prop="common">
            <el-input v-model="infoForm.GName" placeholder="请输入您的公司名称"></el-input>
          </el-form-item>
          <el-form-item label="我的工号" class="np-item" prop="common">
            <el-input v-model="infoForm.SKYNO" placeholder="请输入您的工号"></el-input>
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
      imageUrl: '',
      updateInfoDialog: false,
      infoForm: {
        RealName: '',
        Sex: '',
        Birthday: '',
        Mobile: '',
        Email: '',
        GName: '',
        SKYNO: ''
      },
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
      let authParams = {
        sign: this.$store.state.token.token,
        userid: this.$store.state.token.userid,
        pwd: this.$store.state.token.pwd
      }
      let dat = JSON.parse(JSON.stringify(this.$store.state.user.userInfo))
      dat.RealName = this.infoForm.RealName
      dat.Sex = this.infoForm.Sex
      dat.Birthday = this.infoForm.Birthday
      dat.Mobile = this.infoForm.Mobile
      dat.Email = this.infoForm.Email
      dat.GName = this.infoForm.GName
      dat.SKYNO = this.infoForm.SKYNO
      let form = new FormData()
      form.append("json", JSON.stringify(dat))
      this.updateInfoDialog = false
      service.updateMember(form, authParams).then(res => {
        if (res.state != 0) {
          this.$message.error(res.msg)
        } else {

          this.$store.commit('user/updateUserInfo', this.infoForm)
          this.$message.success('更新成功!')
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
      service.uploadFilesSave(formData, { sign: this.$store.state.token.token, userid: this.$store.state.token.userid, pwd: this.$store.state.token.pwd }).then(res => {
        let headUrl = "https://pic.skyworthds.com/File/Member/ineigopj/" + res.fname
        that.$store.dispatch('user/updatedUserInfoAvatar', headUrl)

      });
    },
    handleAvatarSuccess(res, file) {

    },
    correspondence() {
      this.infoForm.RealName = this.userInfo.RealName
      this.infoForm.Sex = this.userInfo.Sex
      this.infoForm.Birthday = this.userInfo.Birthday
      this.infoForm.Mobile = this.userInfo.Mobile
      this.infoForm.Email = this.userInfo.Email
      this.infoForm.GName = this.userInfo.GName
      this.infoForm.SKYNO = this.userInfo.SKYNO
    }
  },
  mounted() {
    this.correspondence()
  },
  computed: {
    userInfo() {
      let info = {
        RealName: '',
        Sex: '',
        Birthday: '',
        Mobile: '',
        Email: '',
        GName: '',
        SKYNO: '',
        HeadUrl: ''
      }
      if (this.$store.state.user.userInfo) {
        let ui = this.$store.state.user.userInfo
        info.RealName = ui.RealName
        info.Mobile = ui.Mobile
        info.Email = ui.Email
        info.GName = ui.GName
        info.SKYNO = ui.SKYNO
        info.Birthday = ui.Birthday
        info.Sex = ui.Sex == 1 ? "男" : "女"
        if (/^data/.test(ui.HeadUrl)) {
          info.HeadUrl = ""
        } else {
          info.HeadUrl = ui.HeadUrl
        }
      }
      return info
    }
  }

}

</script>

<style lang="scss" scoped>
.person-center {
  height: 914px;
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
