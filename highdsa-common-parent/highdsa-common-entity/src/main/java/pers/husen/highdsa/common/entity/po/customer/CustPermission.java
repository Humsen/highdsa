package pers.husen.highdsa.common.entity.po.customer;

public class CustPermission {
    private Long permissionId;

    private String permissionName;

    private String permissionDesc;

    private Boolean permissionValid;

    public CustPermission(Long permissionId, String permissionName, String permissionDesc, Boolean permissionValid) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.permissionDesc = permissionDesc;
        this.permissionValid = permissionValid;
    }

    public CustPermission() {
        super();
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc == null ? null : permissionDesc.trim();
    }

    public Boolean getPermissionValid() {
        return permissionValid;
    }

    public void setPermissionValid(Boolean permissionValid) {
        this.permissionValid = permissionValid;
    }
}