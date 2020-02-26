package com.xkkj.code.controller.ybcontroller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.AccountFlow;
import com.xkkj.code.pojo.BankCard;
import com.xkkj.code.pojo.Recharge;
import com.xkkj.code.query.RechargeQuery;
import com.xkkj.code.service.RechargeService;
import com.xkkj.common.util.IdWorker;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * CREATE BY YB ON 2020/2/12 15:51
 */
@RestController
@Api(tags = "充值")
public class RechargeControllerYb {

    @Autowired
    RechargeService rechargeService;
    @ApiOperation("前台用户充值操作")
    @PostMapping("/system/recharge/add")
    public Result addCard(@RequestBody Recharge recharge) {
        rechargeService.add(recharge);
        return Result.ok();
    }

    @ApiOperation("分页查询前台用户的充值信息")
    @GetMapping ("/finance/recharge/query")
    public Result query( RechargeQuery rechargeQuery) {
        System.out.println(rechargeQuery);
        IPage<Recharge> rechargeIPage = rechargeService.rechargePage(rechargeQuery);
        return Result.ok().result(rechargeIPage);
    }


}

