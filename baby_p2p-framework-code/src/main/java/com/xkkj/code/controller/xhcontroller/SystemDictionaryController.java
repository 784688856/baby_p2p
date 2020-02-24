package com.xkkj.code.controller.xhcontroller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.SystemDictionary;
import com.xkkj.code.query.DictQuery;
import com.xkkj.code.service.SystemDictionaryService;
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
 *字典组管理
 * @author xkkj
 * @since 2020-02-11
 */
@Slf4j
@Api(tags = "字典组")
@RestController
@RequestMapping("/code/dictionary")
public class SystemDictionaryController {

    @Autowired
    private SystemDictionaryService systemDictionaryService;
    /**
     *新增数据组---xh
     * @return
     */
    @ApiOperation(value = "新增数据组----xh")
    @PostMapping("add")
    public Result add(@ApiParam(name = "systemDictionary", value = "字典组实体")SystemDictionary systemDictionary){
        try {
            boolean save = systemDictionaryService.save(systemDictionary);
            if (save==true){
                log.debug("新增数据组成功！");
            }else {
                log.debug("新增数据组失败!");
            }
            return Result.ok().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }

    @ApiOperation(value = "查询全部数据组----xh")
    @PostMapping("getAll")
    public Result getAll(){
        try {
            List<SystemDictionary> dictionaryList = systemDictionaryService.list(null);
            return Result.ok().result(dictionaryList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }

    @ApiOperation(value = "分页查询数据组----xh")
    @PostMapping("query")
    public Result query(@ApiParam(name = "dictQuery", value = "字典组查询实体")DictQuery dictQuery){
        try {
            Page<SystemDictionary> page=new Page<>(dictQuery.getCurrentPage(),dictQuery.getPageSize());
            IPage<SystemDictionary> pages=systemDictionaryService.query(page,dictQuery);
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

    @ApiOperation(value = "修改数据组----xh")
    @PostMapping("update")
    public Result update(@ApiParam(name = "systemDictionary" ,value = "字典组实体") SystemDictionary systemDictionary){

        try {
            boolean b = systemDictionaryService.updateById(systemDictionary);
            if (b==true){
                log.debug("修改数据组成功！");
            }else {
                log.debug("修改数据组失败!");
            }
            return Result.ok().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().success(false);
        }
    }
}

