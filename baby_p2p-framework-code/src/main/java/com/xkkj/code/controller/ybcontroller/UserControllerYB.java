package com.xkkj.code.controller.ybcontroller;

import com.xkkj.code.pojo.UserAccount;
import com.xkkj.code.pojo.UserInfo;
import com.xkkj.code.pojo.UserWallet;
import com.xkkj.code.service.UserAccountService;
import com.xkkj.code.service.UserInfoService;
import com.xkkj.code.service.UserWalletService;
import com.xkkj.common.util.IdWorker;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * CREATE BY YB ON 2020/2/12 13:17
 */
@Api(tags = "用户信息")
@RestController
@Transactional
public class UserControllerYB {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserWalletService userWalletService;

    @Autowired
    private IdWorker idWorker;

    @ApiOperation("用户注册")
    @PostMapping("/user/register")
    public Result register(@RequestBody UserAccount userAccount) {
        System.out.println(userAccount);
        String id = idWorker.nextId() + "";
        userAccount.setId(id);
        userAccount.setAccountStatus(1);
        userAccount.setAccountType(1);
        userAccount.setFillUserinfo(0);
        // 用户基本信息
        boolean b = userAccountService.save(userAccount);
        if (b) {
            // 用户详细信息
            UserInfo userInfo = new UserInfo(id);
            userInfoService.save(userInfo);
            // 用户钱包信息,注册时新增10000元到可用余额中
            UserWallet wallet = new UserWallet(id,10000L);
            userWalletService.save(wallet);
        }
        return Result.ok();
    }


    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public Result login(@RequestBody UserAccount userAccount) {
        UserAccount user = userAccountService.checkLogin(userAccount);
        if (user != null) {
            return Result.ok().result(user);
        }
        return Result.error();
    }

    @ApiOperation("获取用户基本信息")
    @GetMapping("/user/get/{id}")
    public Result getBasicInfo(@PathVariable String id) {
        UserAccount user = userAccountService.getById(id);
        return Result.ok().result(user);
    }


    @ApiOperation("返回用户详细信息")
    @GetMapping("/user/userinfo/get/{accountId}")
    public Result getDetailInfo(@PathVariable String accountId) {
        UserInfo userInfo = userInfoService.getById(accountId);
        return Result.ok().result(userInfo);
    }

    @ApiOperation("修改用户详细信息")
    @PutMapping("/user/userinfo/update")
    public Result updateDetailInfo(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo);
        userInfoService.saveOrUpdate(userInfo);
        return Result.ok();
    }

}
