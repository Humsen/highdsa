package pers.husen.highdsa.common.entity.po.system;

import java.util.List;

public class Navigation {
	private String navigationName;
	private List<SysPermission> childNavigations;
	
	public String getNavigationName() {
		return navigationName;
	}
	public void setNavigationName(String navigationName) {
		this.navigationName = navigationName;
	}
	public List<SysPermission> getChildNavigations() {
		return childNavigations;
	}
	public void setChildNavigations(List<SysPermission> childNavigations) {
		this.childNavigations = childNavigations;
	}
}