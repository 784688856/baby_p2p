package com.xkkj.code.controller.xhcontroller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.BankCard;
import com.xkkj.code.pojo.Recharge;
import com.xkkj.code.pojo.UserWallet;
import com.xkkj.code.query.RechargeQuery;
import com.xkkj.code.service.BankCardService;
import com.xkkj.code.service.RechargeService;
import com.xkkj.code.service.UserWalletService;
import com.xkkj.common.constants.ResultCodeEnum;
import com.xkkj.common.exception.BabyP2pException;
import com.xkkj.common.util.IdWorker;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *充值管理Controller
 * @author xkkj
 * @since 2020-02-11
 */
@Api(tags = "充值管理")
@RestController
@RequestMapping("/code/recharge")
@Slf4j
public class RechargeController {

    /**
     * 充值账户
     */
    @Autowired
    private RechargeService rechargeService;

    /**
     * 用户账户
     */
    @Autowired
    private UserWalletService userWalletService;

    //银行卡信息
    @Autowired
    private BankCardService bankCardService;
    /**
     * 新增充值信息
     * @return
     */
    @ApiOperation(value = "用户充值信息新增----xh")
    @PostMapping("addRecharge")
    public Result addRecharge(@ApiParam(name = "recharge", value = "用户充值信息管理实体") Recharge recharge){
        try {
            QueryWrapper<BankCard> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("user_id",recharge.getUserId());
            BankCard one = bankCardService.getOne(queryWrapper);
            if (one.getBalance()<recharge.getAmount()){
                log.debug("银行卡金额不足");
                return Result.error().message("银行卡金额不足");
            }
            //非空判断
            if (recharge.getAmount()==null||recharge.getUsername()==null||recharge.getBankCardId()==null||recharge.getRemark()==null){
                throw new BabyP2pException(ResultCodeEnum.PARAM_ERROR);
            }
            //生成流水号
            IdWorker idWorker=new IdWorker(0,0);
            String tradeNo = String.valueOf(idWorker.nextId());
            recharge.setTradeNo(tradeNo);
            //默认审核中
            recharge.setState(1);
            //充值时间
            boolean save = rechargeService.save(recharge);
            if (save==true){
                one.setBalance(one.getBalance()-recharge.getAmount());
                bankCardService.updateById(one);
                log.debug("充值成功！");
            }else {
                log.debug("充值失败");
            }
            return Result.ok().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }

    /**
     * 用户充值信息列表查询
     * @param rechargeQuery
     * @return
     */
    @ApiOperation(value = "用户充值信息列表查询----xh")
    @PostMapping("query")
    public Result query(@ApiParam(name = "rechargeQuery", value = "用户充值信息查询对象") RechargeQuery rechargeQuery){
        Page<Recharge> pageParam = new Page<>(rechargeQuery.getCurrentPage(), rechargeQuery.getPageSize());
        IPage<Recharge> pages = rechargeService.query(pageParam, rechargeQuery);
        Map<String, Object> map = new HashMap<>();
        map.put("listData", pages.getRecords());
        //总页数
        map.put("totalPage", pages.getPages());
        //总数据条数
        map.put("totalCount", pages.getTotal());
        //当前页码
        map.put("nowPage", pages.getCurrent());
        //每页大小
        map.put("pageSize", pages.getSize());

        return Result.ok().result(map);
    }

    /**
     * 充值审核
     * @param recharge
     * @return
     */
    @ApiOperation(value = "充值审核")
    @PostMapping("audit")
    public Result audit(@ApiParam(name = "recharge", value = "用户充值信息管理实体") Recharge recharge){
        try {
            //参数校验
            if(recharge.getId()==null||recharge.getState()==null){
                throw new BabyP2pException(ResultCodeEnum.PARAM_ERROR);
            }
            boolean b = rechargeService.saveOrUpdate(recharge);
            if (b==true){
                //获取充值信息
                Recharge recharge1 = rechargeService.getById(recharge.getId());
                //如果审核通过
                if (recharge.getState()==2){
                    //获取账户信息
                    UserWallet wallet = userWalletService.getById(recharge1.getUserId());
                    UserWallet userWallet=new UserWallet();
                    userWallet.setAccountId(recharge1.getUserId());
                    userWallet.setAvailableAmount(wallet.getAvailableAmount()+recharge1.getAmount());
                    userWalletService.saveOrUpdate(userWallet);
                }else if(recharge.getState()==0){
//                    QueryWrapper<BankCard> queryWrapper=new QueryWrapper<>();
//                    queryWrapper.eq("user_id",recharge1.getUserId());
//                    BankCard one = bankCardService.getOne(queryWrapper);
//                    BankCard bankCard=new BankCard();
//                    bankCard.setBalance(one.getBalance()+recharge1.getAmount());
//                    bankCardService.update(bankCard,queryWrapper);
                }
                log.debug("充值审核成功！");

            }else {
                log.debug("充值审核失败");
            }
            return Result.ok().success(true);
        } catch (BabyP2pException e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }

    @ApiOperation(value = "新增充值信息（2）----xh")
    @PostMapping("saveRecharge")
    public Result saveRecharge(@ApiParam(name = "recharge", value = "用户充值信息管理实体") Recharge recharge){
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
            try {
                Date date = sdf1.parse(recharge.getRechargeTime());//拿到Date对象
                String str = sdf2.format(date);//输出格式：2017-01-22 09:28:33
              recharge.setRechargeTime(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //充值时间
            boolean save = rechargeService.save(recharge);
            if (save==true){
                log.debug("充值成功！");
            }else {
                log.debug("充值失败");
            }
            return Result.ok().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }
}

