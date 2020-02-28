package com.xkkj.code.controller.ybcontroller;

import com.xkkj.code.pojo.UserWallet;
import com.xkkj.code.service.UserWalletService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATE BY YB ON 2020/2/12 15:48
 */
@Api(tags = "钱包")
@RestController
public class UserWalletControllerYB {

    @Autowired
    private UserWalletService userWalletService;

    @ApiOperation("获取前台用户的钱包信息")
    @GetMapping("/user/wallet/get/{id}")
    public Result getWalletInfo(@PathVariable String id){
        UserWallet wallet = userWalletService.getById(id);
        return Result.ok().result(wallet);
    }


}
