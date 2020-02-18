package com.xkkj.code.controller.ybcontroller;

import com.xkkj.code.pojo.UserAccount;
import com.xkkj.code.pojo.UserInfo;
import com.xkkj.code.service.UserAccountService;
import com.xkkj.code.service.UserInfoService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * CREATE BY YB ON 2020/2/12 13:17
 */
@Api(tags = "用户信息")
@RestController
public class UserControllerYB {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserInfoService userInfoService;


    @ApiOperation("用户注册")
    @PostMapping("/user/register")
    public Result register(@RequestBody UserAccount userAccount) {
        userAccountService.save(userAccount);
        return Result.ok();
    }


    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public Result login(@RequestBody UserAccount userAccount) {
        UserAccount user = userAccountService.checkLogin(userAccount);
        if (user!=null) {
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

    //// 测试
    //@GetMapping("/user/get/test")
    //public Result getBasicInfo() {
    //    System.out.println("我执行了！");
    //    return Result.ok();
    //}


    @ApiOperation("返回用户详细信息")
    @GetMapping("/user/userinfo/get/{accountId}")
    public Result getDetailInfo(@PathVariable String id) {
        UserInfo userInfo = userInfoService.getById(id);
        return Result.ok().result(userInfo);
    }

    @ApiOperation("修改用户详细信息")
    @PutMapping("/user/userinfo/update")
    public Result updateDetailInfo(UserInfo userInfo) {
        userInfoService.saveOrUpdate(userInfo);
        return Result.ok();
    }

}
