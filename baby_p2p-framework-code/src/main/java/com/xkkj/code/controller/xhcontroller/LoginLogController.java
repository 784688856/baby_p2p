package com.xkkj.code.controller.xhcontroller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.LoginLog;
import com.xkkj.code.query.LoginLogQuery;
import com.xkkj.code.service.LoginLogService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Api(tags = "登录日志管理")
@RestController
@RequestMapping("/code/loginlog")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录日志记录
     * @param loginLog
     * @return
     */
    @ApiOperation(value = "登录日志记录----xh")
    @PostMapping("loginLogSave")
    public Result LoginLogSave(@ApiParam(name = "loginLog", value = "系统用户登录日志实体")LoginLog loginLog){
        loginLogService.save(loginLog);
        return Result.ok();
    }

    @ApiOperation(value = "查询登录日志列表----xh")
    @PostMapping("query")
    public Result query(@ApiParam(name = "loginLogQuery",value = "登录日志查询条件实体") LoginLogQuery loginLogQuery){
        Page<LoginLog> pageParam = new Page<>(loginLogQuery.getCurrentPage(), loginLogQuery.getPageSize());
        IPage<LoginLog> pages = loginLogService.query(pageParam, loginLogQuery);
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
}

