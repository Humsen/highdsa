package pers.husen.highdsa.common.entity;

import pers.husen.highdsa.common.entity.constants.SysUserState;

/**
 * @Desc 测试枚举
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月27日 下午12:52:57
 * 
 * @Version 1.0.0
 */
public class TestEnum {
	public static void main(String[] args) {
		System.out.println(SysUserState.INVALID);
		System.out.println(SysUserState.INVALID.getStateCode());
		System.out.println(SysUserState.INVALID.getDescrition());
	}
}