package com.xkkj.code.controller.chtcontroller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.object.QueryObject;
import com.xkkj.code.pojo.Borrow;
import com.xkkj.code.service.BorrowService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Api(tags = "借款")
@RestController
@RequestMapping("finance/borrow")
public class ChtBorrowController {

    @Autowired
    private BorrowService borrowService;

    /**
     * 分页查询前台用户的账户流水信息---cht
     * @param queryObject
     * @return
     */
    @ApiOperation("分页查询前台用户的账户流水信息")
    @PostMapping("/query")
    public Result query(
            @ApiParam(name = "queryObject", value = "查询对象", required = false)
            QueryObject queryObject) {
        if (!StringUtils.isEmpty(queryObject)){
            queryObject.setCurrentPage(0);
            queryObject.setPageSize(10);
        }
        Page<Borrow> pageParam = new Page<>(queryObject.getCurrentPage(),queryObject.getPageSize());
        IPage<Borrow> pages = borrowService.query(pageParam,queryObject);
        if (!StringUtils.isEmpty(queryObject.getUserId())){
            Map<String, Object> map = new HashMap<>();
            map.put("listData", pages.getRecords().get(0));
            map.put("totalPage", pages.getPages());
            map.put("totalCount", pages.getTotal());
            map.put("currentPage", pages.getCurrent());
            map.put("pageSize", pages.getSize());
            return Result.ok().result(map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("datalist", pages.getRecords());
        map.put("allPage", pages.getPages());
        map.put("allCount", pages.getTotal());
        map.put("nowPage", pages.getCurrent());
        map.put("pageSize", pages.getSize());
        return Result.ok().result(map);
    }


    /**
     * 前台用户，发起借款申请---cht
     * @param borrow
     * @return
     */
    @ApiOperation("前台用户，发起借款申请")
    @PostMapping("/add")
    public Result add(@RequestBody Borrow borrow){
         //获取用户资料，如果用户资料填写完整则可发起借款

        //如果借款对象id不为空则进行借款审核
        if (!StringUtils.isEmpty(borrow.getId())){

        }
        //数据库id未自增，需要自己赋值
        Integer id = UUID.randomUUID().hashCode();
        borrow.setId(id.toString());
        return borrowService.saveBorrow(borrow);
    }

    /**
     * 获取借款标的信息---cht
     * @return
     */
    @ApiOperation("获取借款标的信息")
    @PostMapping("get/{id}")
    public Result get(String id){
        Borrow borrow = borrowService.get(id);
        return Result.ok().result(borrow);
    }


    @ApiOperation("前台用户，发起借款申请")
    @PostMapping("/audit")
    public Result audit(String borrowId , Integer borrowState ) {
        Integer audit = borrowService.audit(borrowId, borrowState);
        if (audit > 0){
            return Result.ok();
        }
        return Result.ok();
    }
}

