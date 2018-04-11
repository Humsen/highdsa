package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;
import pers.husen.highdsa.common.entity.po.customer.CustRole;

public interface CustRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(CustRole record);

    CustRole selectByPrimaryKey(Long roleId);

    List<CustRole> selectAll();

    int updateByPrimaryKey(CustRole record);
}