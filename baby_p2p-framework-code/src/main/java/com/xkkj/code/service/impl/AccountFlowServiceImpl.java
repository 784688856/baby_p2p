package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.AccountFlow;
import com.xkkj.code.mapper.AccountFlowMapper;
import com.xkkj.code.service.AccountFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Service
public class AccountFlowServiceImpl extends ServiceImpl<AccountFlowMapper, AccountFlow> implements AccountFlowService {

    // TODO ----- yb专区，卢本伟专业护法，外来人员不得入侵 -----
    @Override
    public IPage<AccountFlow> getAccountFlowPage(Page<AccountFlow> pageParam, AccountFlow accountFlow) {
        // 条件
        QueryWrapper<AccountFlow> wrapper = new QueryWrapper<>();
        // 根据时间排序
        //wrapper.orderByDesc("create_time");
        //Integer id = activityQueryParam.getId();
        //String activityName = activityQueryParam.getActivityName(); // 模糊查询活动名称
        //Integer status = activityQueryParam.getAuditStatus(); // 审核状态
        //Integer baseId = activityQueryParam.getBaseId(); // 基地编号
        //Integer organizationId = activityQueryParam.getOrganizationId(); // 承办基地
        // 如果查询条件为空，则不需要根据查询条件查询了

        //// 主题编号
        //if (id != null) {
        //    wrapper.eq("id", id);
        //}
        //// 模糊查询研学主题
        //if (activityName != null) {
        //    wrapper.like("activity_name", activityName);
        //}
        //
        //// 状态
        //if (status != null) {
        //    wrapper.eq("audit_status", status);
        //}
        //
        //// 基地
        //if (baseId != null) {
        //    wrapper.eq("base_id", baseId);
        //}
        //
        //// 机构
        //if (organizationId != null) {
        //    wrapper.eq("organization_id", organizationId);
        //}

        // 分页
        IPage<AccountFlow> pages = baseMapper.selectPage(pageParam, wrapper);

        return pages;
    }
    // TODO yb专区结束 ----- ----- ----- ----- ----- ----- -
}
