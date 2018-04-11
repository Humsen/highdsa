package pers.husen.highdsa.common.entity.po.customer;

public class CustRole {
    private Long roleId;

    private String roleName;

    private String roleDesc;

    private Boolean roleValid;

    public CustRole(Long roleId, String roleName, String roleDesc, Boolean roleValid) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.roleValid = roleValid;
    }

    public CustRole() {
        super();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Boolean getRoleValid() {
        return roleValid;
    }

    public void setRoleValid(Boolean roleValid) {
        this.roleValid = roleValid;
    }
}