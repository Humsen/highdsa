package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.husen.highdsa.common.entity.po.customer.CustUserRole;

public interface CustUserRoleMapper {
    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("roleId") Long roleId);

    int insert(CustUserRole record);

    List<CustUserRole> selectAll();
}