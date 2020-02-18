package com.xkkj.code.controller.ybcontroller;

import com.xkkj.code.pojo.Withdraw;
import com.xkkj.code.service.WithdrawService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATE BY YB ON 2020/2/12 16:14
 */
@Api(tags = "提现")
@RestController
public class WithdrawControllerYB {

    @Autowired
    private WithdrawService withdrawService;

    @ApiOperation("前台用户，发起账户提现")
    @PostMapping("/user/withdraw/add")
    public Result withdraw(Withdraw withdraw){
        withdrawService.save(withdraw);
        return Result.ok();
    }
}
