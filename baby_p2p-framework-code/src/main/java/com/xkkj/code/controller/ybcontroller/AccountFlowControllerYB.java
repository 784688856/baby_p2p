package com.xkkj.code.controller.ybcontroller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.AccountFlow;
import com.xkkj.code.service.AccountFlowService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATE BY YB ON 2020/2/12 16:17
 */
@Api(tags = "账号流水线")
@RestController
public class AccountFlowControllerYB {
    @Autowired
    AccountFlowService accountFlowService;


    @ApiOperation(value = "分页查询前台用户的账户流水信息")
    @GetMapping("/user/accountflow/query/{page}/{size}")
    public Result page(AccountFlow accountFlow, @PathVariable int page, @PathVariable int size) {
        Page<AccountFlow> pageParam = new Page<>(page, size);
        IPage<AccountFlow> accountFlowPage = accountFlowService.getAccountFlowPage(pageParam, accountFlow);
        return Result.ok().result(accountFlow);
    }
}
