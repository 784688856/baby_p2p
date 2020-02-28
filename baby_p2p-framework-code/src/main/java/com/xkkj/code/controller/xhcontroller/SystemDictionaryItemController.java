package com.xkkj.code.controller.xhcontroller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.SystemDictionaryItem;
import com.xkkj.code.query.DictQuery;
import com.xkkj.code.service.SystemDictionaryItemService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
 *字典项管理
 * @author xkkj
 * @since 2020-02-11
 */
@Slf4j
@Api(tags = "字典项")
@RestController
@RequestMapping("/code/dictionaryItem")
public class SystemDictionaryItemController {

    @Autowired
    private SystemDictionaryItemService SystemDictionaryItemService;
    /**
     *新增数据项---xh
     * @return
     */
    @ApiOperation(value = "新增数据项----xh")
    @PostMapping("add")
    public Result add(@ApiParam(name = "SystemDictionaryItemItem", value = "字典项实体") SystemDictionaryItem systemDictionaryItem){
        try {
            boolean save = SystemDictionaryItemService.save(systemDictionaryItem);
            if (save==true){
                log.info("新增数据项成功！");
                return Result.ok().success(true);
            }else {
                log.info("新增数据项失败!");
                return Result.error().success(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }

    @ApiOperation(value = "查询全部数据项----xh")
    @PostMapping("getAll")
    public Result getAll(){
        try {
            List<SystemDictionaryItem> dictionaryList = SystemDictionaryItemService.list(null);
            return Result.ok().result(dictionaryList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }

    @ApiOperation(value = "分页查询数据项----xh")
    @PostMapping("query")
    public Result query(@ApiParam(name = "dictQuery", value = "字典项查询实体") DictQuery dictQuery){
        try {
            Page<SystemDictionaryItem> page=new Page<>(dictQuery.getCurrentPage(),dictQuery.getPageSize());
            IPage<SystemDictionaryItem> pages=SystemDictionaryItemService.query(page,dictQuery);
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
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }

    @ApiOperation(value = "修改数据项----xh")
    @PostMapping("update")
    public Result update(@ApiParam(name = "SystemDictionaryItem" ,value = "字典项实体") SystemDictionaryItem SystemDictionaryItem){

        try {
            boolean b = SystemDictionaryItemService.updateById(SystemDictionaryItem);
            if (b==true){
                log.info("修改数据项成功！");
                return Result.ok().success(true);
            }else {
                log.info("修改数据项失败!");
            }
            return Result.error().success(false);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }
}

