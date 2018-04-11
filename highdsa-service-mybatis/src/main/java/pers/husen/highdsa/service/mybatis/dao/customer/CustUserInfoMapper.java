package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;
import pers.husen.highdsa.common.entity.po.customer.CustUserInfo;

public interface CustUserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(CustUserInfo record);

    CustUserInfo selectByPrimaryKey(Integer userId);

    List<CustUserInfo> selectAll();

    int updateByPrimaryKey(CustUserInfo record);
}