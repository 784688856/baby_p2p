package com.xkkj.code.controller.ybcontroller;

import com.xkkj.code.pojo.BankCard;
import com.xkkj.code.service.BankCardService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATE BY YB ON 2020/2/12 15:51
 */
@Api(tags = "银行卡")
@RestController
public class BankCardControllerYB {
    @Autowired
    private BankCardService bankCardService;


    @ApiOperation("前台用户添加/绑定银行卡信息")
    @PostMapping("/user/bankcard/add")
    public Result addCard(BankCard bankCard) {
        bankCardService.save(bankCard);
        return Result.ok();
    }


    @ApiOperation(value = "获取前台用户的银行卡信息")
    @GetMapping("/user/bankcard/get/{accountId}")
    public Result getCard(@PathVariable String accountId) {
        BankCard card = bankCardService.getById(accountId);
        return Result.ok().result(card);
    }




}
