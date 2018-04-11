package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;
import pers.husen.highdsa.common.entity.po.customer.CustPermission;

public interface CustPermissionMapper {
    int deleteByPrimaryKey(Long permissionId);

    int insert(CustPermission record);

    CustPermission selectByPrimaryKey(Long permissionId);

    List<CustPermission> selectAll();

    int updateByPrimaryKey(CustPermission record);
}