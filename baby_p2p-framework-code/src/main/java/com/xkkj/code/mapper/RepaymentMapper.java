package com.xkkj.code.mapper;

import com.xkkj.code.pojo.Repayment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Repository
@Mapper
public interface RepaymentMapper extends BaseMapper<Repayment> {
    //查询标的的还款列表信息
    List<Repayment> getByBorrowId(String borrowId);
}
