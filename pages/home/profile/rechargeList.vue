<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-size:16px; ">时间范围&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <el-date-picker
          unlink-panels
          v-model="rangeDate"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          format="yyyy 年 MM 月 dd 日"
        ></el-date-picker>
        <span style="font-size:16px; ">&nbsp;&nbsp;&nbsp;&nbsp;状态：</span>
        <el-select v-model="queryForm.state" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
        <el-button type="success" style="margin-left: 12px;" @click="submit">查询</el-button>
      </div>
      <div slot="header" class="clearfix">
        <div class="dd">
          <span style="font-size:16px;">&nbsp;&nbsp;&nbsp;&nbsp;充值明细</span>
          <el-button type="danger" style="margin-left: 733px;">
            <nuxt-link to="/home/profile/recharge">
              <span class="tt">账户充值</span>
            </nuxt-link>
          </el-button>
        </div>
      </div>
      <el-table :data="tableData" style="width: 100%" stripe>
        <el-table-column prop="tradeNo" label="交易号" width="180" align="center"></el-table-column>
        <el-table-column prop="rechargeTime" label="充值时间" width="180" align="center"></el-table-column>
        <el-table-column prop="amount" label="充值金额" align="center"></el-table-column>
        <el-table-column prop="state" label="充值状态" align="center"></el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination background layout="prev, pager, next" :total="1000"></el-pagination>
    </el-card>
  </div>
</template>

<script>
import { add, page } from '@/api/recharge'
export default {
  data() {
    return {
      rangeDate: [],
      queryForm: {
        state: '',
        beginDate: '',
        endDate: ''
      },
      queryList: {
        currentPage: 1,
        pageSize: 10
      },

      options: [
        {
          value: '1',
          label: '审核中'
        },
        {
          value: '2',
          label: '审核通过'
        },
        {
          value: '0',
          label: '审核拒绝'
        }
      ],
      tableData: []

    };
  }, methods: {
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
    selectTime() {
      let data = {}, times = {}
      // time是个数组，分别存在开始和结束时间
      // this.queryForm &&非空验证，防止undefined.xxx报错
      if (this.rangeDate) {
        times = {
          beginDate: this.rangeDate[0],
          endDate: this.rangeDate[1]
        }
      }
      // 合并条件
      data = { ...this.queryForm, ...times, ...this.queryList }
      return data

    }, submit() {
      let data = this.selectTime()
      page(data).then(res => {
      this.tableData=res.data.records
        console.log(res)
      })
    }
  },
  created() {
    let data = this.selectTime()
    page(data).then(res => {
      this.tableData=res.data.records
    })
  }
}
</script>

<style scoped>
.box-card {
  width: 958px;
  height: 666px;
}
.dd {
  background-color: rgb(241, 243, 244);
  margin-top: 40px;
}
.tt {
  color: black;
}
</style>