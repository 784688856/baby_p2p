package com.xkkj.code.controller.licontroller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xkkj.code.pojo.RepaymentDetail;
import com.xkkj.code.query.RepaymentQuery;
import com.xkkj.code.service.RepaymentDetailService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@RestController
@Api("还款明细管理")
@RequestMapping("/finance/repayment/detail")
public class RepaymentDetailController {
    @Autowired
    RepaymentDetailService repaymentDetailService;

    @ApiOperation("分页查询前台用户的收款明细信息")
    @PostMapping("query")
    public Result getQuery(@ApiParam(name = "repaymentQuery",value = "还款查询条件实体") RepaymentQuery repaymentQuery){
        IPage<RepaymentDetail> repaymentDetailIPage = repaymentDetailService.getQuery(repaymentQuery);
        Map<String, Object> map = new HashMap<>();
        map.put("listData", repaymentDetailIPage.getRecords());
        //总页数
        map.put("totalPage", repaymentDetailIPage.getPages());
        //总数据条数
        map.put("totalCount", repaymentDetailIPage.getTotal());
        //当前页码
        map.put("nowPage", repaymentDetailIPage.getCurrent());
        //每页大小
        map.put("pageSize", repaymentDetailIPage.getSize());
        return Result.ok().result(map);
    }

}

