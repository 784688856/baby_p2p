package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.mapper.RepaymentDetailMapper;
import com.xkkj.code.pojo.Bid;
import com.xkkj.code.pojo.Borrow;
import com.xkkj.code.pojo.Repayment;
import com.xkkj.code.mapper.RepaymentMapper;
import com.xkkj.code.pojo.RepaymentDetail;
import com.xkkj.code.query.RepaymentQuery;
import com.xkkj.code.service.BidService;
import com.xkkj.code.service.BorrowService;
import com.xkkj.code.service.RepaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Service
public class RepaymentServiceImpl extends ServiceImpl<RepaymentMapper, Repayment> implements RepaymentService {
    @Autowired
    RepaymentMapper repaymentMapper;
    @Autowired
    BidService bidService;
    @Autowired
    BorrowService borrowService;
    @Autowired
    RepaymentDetailMapper repaymentDetailMapper;

    /**
     * 分页查询前台用户的还款信息
     * @param repaymentQuery 还款请求类
     * @return
     */
    @Override
    public IPage<Repayment> getQuery(RepaymentQuery repaymentQuery) {
        Page<Repayment> repaymentPage = new Page<>(repaymentQuery.getCurrentPage(),repaymentQuery.getPageSize());
        QueryWrapper<Repayment> queryWrapper = new QueryWrapper<>();
        // 根据还款期数,还款状态排序
        queryWrapper.orderByDesc("state","period");
        //判断时间
        if (StringUtils.isNotBlank(repaymentQuery.getBeginDate())){
            queryWrapper.gt("deadline",repaymentQuery.getBeginDate());
        }
        if (StringUtils.isNotBlank(repaymentQuery.getEndDate())){
            queryWrapper.lt("deadline",repaymentQuery.getEndDate());
        }
        //判断用户
        if (StringUtils.isNotEmpty(repaymentQuery.getUserId())){
            queryWrapper.eq("borrow_user_id",repaymentQuery.getUserId());
        }
        //判断用户还款状态
        if (repaymentQuery.getState()!=null){
            queryWrapper.eq("state",repaymentQuery.getState());
        }
        IPage<Repayment> repaymentIPage = repaymentMapper.selectPage(repaymentPage, queryWrapper);
        return repaymentIPage;
    }

    /**
     * 查询标的的还款列表信息
     * @param borrowId 借款标id
     * @return
     */
    @Override
    public List<Repayment> getByBorrowId(String borrowId) {
        return repaymentMapper.getByBorrowId(borrowId);
    }

    /**
     *还款操作
     * @param id 还款信息id
     * @param borrowUserId 借款人
     */
    @Override
    public void getRepay(String id,String borrowUserId) {
        Repayment repayment = repaymentMapper.selectById(id);
        Borrow borrow = borrowService.getById(repayment.getBorrowId());
        if (borrowUserId.equals(repayment.getBorrowUserId())){
            repayment.setState(4);
            //修改还款表(repayment)
            repaymentMapper.updateById(repayment);
            //添加还款记录
            RepaymentDetail repaymentDetail = new RepaymentDetail();
            repaymentDetail.setId(repayment.getId()+repayment.getState());
            repaymentDetail.setBorrowId(repayment.getBorrowId());
            repaymentDetail.setRepaymentId(repayment.getId());
            repaymentDetail.setBorrowUserId(repayment.getBorrowUserId());
            repaymentDetail.setBorrowTitle(repayment.getBorrowTitle());
            repaymentDetail.setPeriod(repayment.getPeriod());
            repaymentDetail.setDeadline(repayment.getDeadline());
            repaymentDetail.setRepaymentTime(new Date());
            repaymentDetail.setRepaymentType(repayment.getRepaymentType());
            repaymentDetail.setCreateTime(repayment.getCreateTime());
            //通过借款人id找到投标人有哪些
            List<Bid> bidList = bidService.getByBorrowId(repayment.getBorrowId());
            //然后根据每个投资人投的钱来计算其收益
            for (Bid bid:bidList) {
                repaymentDetail.setBidId(bid.getId());//标的id
                repaymentDetail.setBidUserId(bid.getBidUserId());//投标人id
                if (repayment.getRepaymentType()==1){//判断还款方式
                    Map<String, Integer> integerMap = getrepaymentType1(borrow.getRepaymentMonth(), bid.getBidInterest(), repayment.getPeriod(), bid.getBidAmount());
                    repaymentDetail.setPrincipal(integerMap.get("bengjin"));//本期还款本金
                    repaymentDetail.setInterest(integerMap.get("ylixi"));//本期还款总利息
                    repaymentDetail.setTotalAmount(integerMap.get("ylixi")+integerMap.get("bengjin"));//本期还款总利息
                }else {
                    Map<String, Integer> integerMap = getrepaymentType2(borrow.getRepaymentMonth(), bid.getBidInterest(), repayment.getPeriod(), bid.getBidAmount());
                    repaymentDetail.setPrincipal(integerMap.get("bengjin"));//本期还款本金
                    repaymentDetail.setInterest(integerMap.get("ylixi"));//本期还款总利息
                    repaymentDetail.setTotalAmount(integerMap.get("ylixi")+integerMap.get("bengjin"));//本期还款总利息
                }
                //添加每个投资人的收款明细
                int insert = repaymentDetailMapper.insert(repaymentDetail);
                if (insert>0){
                    System.out.println("添加成功");
                }
                //修改每个投资人和还款人的余额
                //Integer totalAmount = repaymentDetail.getTotalAmount();//本次还了多少钱
                //调用用户接口，查询出还款人和每一位投资人修改其余额
            }
        }
    }

    /**
     *等额本息还款金额计算
     * @param repaymentMonth 还款期数
     * @param bidInterest 年利化率
     * @param period 第几期
     * @param bidAamount 投标金额
     * @return
     */
    @Override
    public Map<String,Integer> getrepaymentType1(int repaymentMonth,int bidInterest,int period,int bidAamount) {
        Integer ylixi = null;
        Integer bengjin = bidAamount;
        Map<String,Integer> map = new HashMap<>();
        if (repaymentMonth==1){
            ylixi = bengjin*bidInterest/12;
            map.put("bnegjin",bengjin);
            map.put("ylixi",ylixi);
        }else{
            Integer yhbengjin = 0;
            Integer yhuankuan =(bengjin*bidInterest/12)*((1+bidInterest/12)^repaymentMonth)/((1+bidInterest/12)^repaymentMonth-1);
            for (int a=1;a<=period;a++){
                ylixi = ((bengjin-yhbengjin)*bidInterest/12);
                yhbengjin = yhuankuan-ylixi;
            }
            map.put("bnegjin",yhbengjin);
            map.put("ylixi",ylixi);
        }
        return map;
    }

    /**
     *先息后本还款金额计算
     * @param repaymentMonth 还款期数
     * @param bidInterest 年利化率
     * @param period 第几期
     * @param bidAamount 投标金额
     * @return
     */
    @Override
    public Map<String,Integer> getrepaymentType2(int repaymentMonth,int bidInterest,int period,int bidAamount) {
        Integer ylixi = bidAamount*bidInterest/12;
        Integer bengjin = 0;
        if (repaymentMonth==period){
            bengjin = bidAamount;
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("ylixi",ylixi);
        map.put("bnegjin",bengjin);
        return map;
    }

}
