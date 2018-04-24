package pers.husen.highdsa.common.entity.po.customer;

import java.io.Serializable;
import java.util.List;

/**
 * @Desc 客户管理导航栏实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月24日 上午10:22:35
 * 
 * @Version 1.0.0
 */
public class CustNavigation implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 主菜单名称 */
	private String navigationName;
	/** 子菜单名称 列表 */
	private List<CustPermission> childNavigations;

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

	public List<CustPermission> getChildNavigations() {
		return childNavigations;
	}

	public void setChildNavigations(List<CustPermission> childNavigations) {
		this.childNavigations = childNavigations;
	}
}