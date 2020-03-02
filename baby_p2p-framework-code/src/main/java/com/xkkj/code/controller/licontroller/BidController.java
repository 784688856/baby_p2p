package com.xkkj.code.controller.licontroller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xkkj.code.pojo.Bid;
import com.xkkj.code.query.BidQuery;
import com.xkkj.code.service.BidService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@RestController
@Api(tags = "前台投资接口")
@RequestMapping("/finance/bid")
public class BidController {
    @Autowired
    BidService bidService;

    @ApiOperation("投资明细分页查询")
    @PostMapping("/query")
    public Result bidPage(@ApiParam(name = "BidQuery",value = "标明细查询条件实体") BidQuery bidQuery){
        IPage<Bid> pages = bidService.page(bidQuery);
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

    @ApiOperation("查询标的投标列表信息")
    @PostMapping("/getByBorrowId")
    public Result getByBorrowId(@ApiParam(name = "borrowId",value = "借款id")String borrowId){
        List<Bid> byBorrowId = bidService.getByBorrowId(borrowId);
        return Result.ok().result(byBorrowId);
    }

    @ApiOperation("发起投标")
    @PostMapping("/add")
    public Result add(
            @ApiParam(name = "bidUserId",value = "投标人id")String bidUserId,
            @ApiParam(name = "bidUsername",value = "投标人用户名")String bidUsername,
            @ApiParam(name = "borrowId",value = "借款标id")String borrowId,
            @ApiParam(name = "bidAmount",value = "投资金额( 单位：分 )")Integer bidAmount){
        bidService.add(bidUserId,bidUsername,borrowId,bidAmount);
        return Result.ok();
    }
}

