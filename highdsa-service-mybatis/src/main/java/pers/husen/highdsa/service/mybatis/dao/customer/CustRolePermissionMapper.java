package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.husen.highdsa.common.entity.po.customer.CustRolePermission;

public interface CustRolePermissionMapper {
    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    int insert(CustRolePermission record);

    List<CustRolePermission> selectAll();
}