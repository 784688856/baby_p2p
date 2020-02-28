package com.xkkj.code.controller.ybcontroller;

import com.xkkj.code.pojo.MailVerify;
import com.xkkj.code.pojo.UserInfo;
import com.xkkj.code.query.MailQuery;
import com.xkkj.code.service.MailVerifyService;
import com.xkkj.code.service.UserInfoService;
import com.xkkj.common.util.QQEmail;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * CREATE BY YB ON 2020/2/24 8:25
 */
@RestController
@Api(tags = "手机，邮箱等验证")
public class CodeCheckController {


    @Autowired
    private MailVerifyService mailVerifyService;

    @Autowired
    private QQEmail qqEmail;

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("email/sendCode")
    public Result sendCode(@RequestBody MailQuery mailQuery) throws Exception {
        System.out.println(mailQuery);
        Random r = new Random();
        String code = Integer.toString(r.nextInt(9999 - 1000 + 1) + 1000);// 为变量赋随机值1000-9999
        // 将后端产生的验证码发送到指定的邮箱中
        qqEmail.sendMail(mailQuery.getNewEmail(), "你正在绑定Baby投邮箱，验证码5分钟有效！验证码：" + code);
        // 将此记录保存到数据库
        MailVerify mailVerify = new MailVerify();
        mailVerify.setRandomCode(code);
        int id = mailVerifyService.add(mailVerify);
        // 返回主键
        return Result.ok().result(id);
    }

    @PostMapping("email/checkEmail")
    public Result checkEmail(@RequestBody MailQuery mailQuery) {
        // 验证邮箱
        Boolean result = mailVerifyService.checkEmail(mailQuery);
        if (result) {
            // 更新用户
            String userId = mailQuery.getUserId();
            String newEmail = mailQuery.getNewEmail();
            UserInfo user = userInfoService.getById(userId);
            if (user != null) {
                user.setEmail(newEmail);
                userInfoService.saveOrUpdate(user);
            }
            return Result.ok();
        }
        return Result.error();
    }

}
