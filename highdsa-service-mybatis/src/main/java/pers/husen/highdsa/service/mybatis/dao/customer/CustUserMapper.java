package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;
import pers.husen.highdsa.common.entity.po.customer.CustUser;

public interface CustUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(CustUser record);

    CustUser selectByPrimaryKey(Long userId);

    List<CustUser> selectAll();

    int updateByPrimaryKey(CustUser record);
}