package com.xkkj.code.controller.xhcontroller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.AdminBankCard;
import com.xkkj.code.query.AdminBankCardQuery;
import com.xkkj.code.service.AdminBankCardService;
import com.xkkj.common.constants.ResultCodeEnum;
import com.xkkj.common.exception.BabyP2pException;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xkkj
 * @since 2020-02-25
 */
@Api(tags = "系统银行卡管理")
@RestController
@RequestMapping("/code/adminBankCard")
@Slf4j
public class AdminBankCardController {

    @Autowired
    private AdminBankCardService adminBankCardService;

    /**
     * 系统银行卡列表查询
     *
     * @param
     * @return
     */
    @ApiOperation(value = "系统银行卡列表查询----xh")
    @PostMapping("query")
    public Result query(@ApiParam(name = "adminBankCardQuery", value = "系统银行卡查询对象") AdminBankCardQuery adminBankCardQuery) {
        Page<AdminBankCard> pageParam = new Page<>(adminBankCardQuery.getCurrentPage(), adminBankCardQuery.getPageSize());
        IPage<AdminBankCard> pages = adminBankCardService.query(pageParam, adminBankCardQuery);
        Map<String, Object> map = new HashMap<>();
        map.put("listData", pages.getRecords());
        map.put("totalPage", pages.getPages());
        map.put("totalCount", pages.getTotal());
        map.put("nowPage", pages.getCurrent());
        map.put("pageSize", pages.getSize());
        return Result.ok().result(map);
    }

    /**
     * 系统银行卡新增/修改
     *
     * @param
     * @return
     */
    @ApiOperation(value = "系统银行卡新增/修改----xh")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@ApiParam(name = "adminBankCardQuery", value = "系统银行卡新增/修改对象") AdminBankCard adminBankCard) {
        try {
            //非空判断
            if (adminBankCard.getBankName() == null || adminBankCard.getBranchName() == null || adminBankCard.getCardNumber() == null || adminBankCard.getRealname() == null) {
                throw new BabyP2pException(ResultCodeEnum.PARAM_ERROR);
            }
            boolean save = adminBankCardService.saveOrUpdate(adminBankCard);
            if (save == true) {
                log.info("新增/修改银行卡成功！");
                return Result.ok().success(true);
            } else {
                log.info("新增/修改银行卡失败");
                return Result.error().success(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }

    /**
     * 系统银行卡删除
     *
     * @param
     * @return
     */
    @ApiOperation(value = "系统银行卡删除----xh")
    @GetMapping("delete")
    public Result delete(@ApiParam(name = "id", value = "1", required = true) Integer id) {
        try {
            boolean save = adminBankCardService.removeById(id);
            if (save == true) {
                log.info("删除银行卡成功！");
                return Result.ok().success(true);
            } else {
                log.info("删除银行卡失败");
                return Result.error().success(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }
}

