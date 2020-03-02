package com.xkkj.code.controller.licontroller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xkkj.code.pojo.Repayment;
import com.xkkj.code.query.RepaymentQuery;
import com.xkkj.code.service.RepaymentService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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
@Api(tags = "还款模块接口")
@RequestMapping("/finance/repayment")
public class RepaymentController {
    @Autowired
    RepaymentService repaymentService;

    @ApiOperation("分页查询前台用户的还款信息")
    @PostMapping("query")
    public Result getQuery(@ApiParam(name = "repaymentQuery",value = "还款查询条件实体") RepaymentQuery repaymentQuery){
        IPage<Repayment> repaymentIPage = repaymentService.getQuery(repaymentQuery);
        Map<String, Object> map = new HashMap<>();
        map.put("listData", repaymentIPage.getRecords());
        //总页数
        map.put("totalPage", repaymentIPage.getPages());
        //总数据条数
        map.put("totalCount", repaymentIPage.getTotal());
        //当前页码
        map.put("nowPage", repaymentIPage.getCurrent());
        //每页大小
        map.put("pageSize", repaymentIPage.getSize());
        return Result.ok().result(map);
    }

    @ApiOperation("查询标的的还款列表信息")
    @PostMapping("getByBorrowId")
    public Result getByBorrowId(@ApiParam(name = "borrowId",value = "借款标id")String borrowId){
        List<Repayment> byBorrowId = repaymentService.getByBorrowId(borrowId);
        return Result.ok().result(byBorrowId);
    }

    @ApiOperation("还款操作")
    @PostMapping("repay")
    public Result getRepat(@ApiParam(name = "id",value = "还款信息id") String id,
                           @ApiParam(name = "borrowUserId",value = "借款人id") String borrowUserId){
        Repayment repayment = repaymentService.getById(id);
        repaymentService.getRepay(id,borrowUserId);
        return Result.ok();
    }


}

