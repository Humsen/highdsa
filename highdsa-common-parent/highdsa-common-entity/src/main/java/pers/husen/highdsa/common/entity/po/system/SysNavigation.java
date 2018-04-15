package pers.husen.highdsa.common.entity.po.system;

import java.io.Serializable;
import java.util.List;

/**
 * @Desc 系统导航栏实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午7:21:23
 * 
 * @Version 1.0.2
 */
public class SysNavigation implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 主菜单名称 */
	private String navigationName;
	/** 子菜单名称 列表 */
	private List<SysPermission> childNavigations;

	@Override
	public String toString() {
		return "Navigation [navigationName=" + navigationName + ", childNavigations=" + childNavigations + "]";
	}

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