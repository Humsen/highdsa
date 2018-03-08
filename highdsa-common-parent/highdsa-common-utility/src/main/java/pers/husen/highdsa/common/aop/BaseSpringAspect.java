package pers.husen.highdsa.common.aop;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * @Desc 与Spring 切面相关的工具
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月4日 下午9:53:11
 * 
 * @Version 1.0.0
 */
public abstract class BaseSpringAspect {
	/**
	 * Pointcut 定义Pointcut，Pointcut名称为aspectjMethod,必须无参，无返回值 只是一个标识，并不进行调用
	 */
	protected abstract void aspectJMethod();

	/**
	 * 获取Spring 切面目标函数的参数名称和值(返回格式化String)
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	protected String getFieldsNameValueString(JoinPoint joinPoint) throws Exception {
		Object[] args = joinPoint.getArgs();
		
		String classType = joinPoint.getTarget().getClass().getName();
		Class<?> clazz = Class.forName(classType);
		String clazzName = clazz.getName();
		// 获取方法名称
		String methodName = joinPoint.getSignature().getName();

		StringBuilder stringBuilder = new StringBuilder();

		ClassPool pool = ClassPool.getDefault();
		ClassClassPath classPath = new ClassClassPath(this.getClass());
		pool.insertClassPath(classPath);
		CtClass cc = pool.get(clazzName);
		CtMethod cm = cc.getDeclaredMethod(methodName);
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			throw new RuntimeException();
		}
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;

		int paramListLength = cm.getParameterTypes().length;
		for (int i = 0; i < paramListLength - 1; i++) {
			// paramNames即参数名
			stringBuilder.append(attr.variableName(i + pos));
			stringBuilder.append("=");
			stringBuilder.append(args[i]);
			stringBuilder.append(",");
			
			//System.out.println("参数键值对：" +attr.variableName(i + pos) + "=" + args[i]);
		}
		stringBuilder.append(attr.variableName(paramListLength - 1 + pos));
		stringBuilder.append("=");
		stringBuilder.append(args[paramListLength - 1]);

		return stringBuilder.toString();
	}

	/**
	 * 获取Spring 切面目标函数的参数名称和值(返回Map)
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> getFieldsNameValueMap(JoinPoint joinPoint) throws Exception {
		Object[] args = joinPoint.getArgs();
		String classType = joinPoint.getTarget().getClass().getName();
		Class<?> clazz = Class.forName(classType);
		String clazzName = clazz.getName();
		// 获取方法名称
		String methodName = getAimMethodName(joinPoint);

		Map<String, Object> map = new HashMap<String, Object>(4);
		ClassPool pool = ClassPool.getDefault();
		ClassClassPath classPath = new ClassClassPath(this.getClass());
		pool.insertClassPath(classPath);
		CtClass cc = pool.get(clazzName);
		CtMethod cm = cc.getDeclaredMethod(methodName);
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			throw new RuntimeException();
		}
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < cm.getParameterTypes().length; i++) {
			// paramNames即参数名
			map.put(attr.variableName(i + pos), args[i]);
		}

		return map;
	}

	/**
	 * 得到目标函数的名称
	 * 
	 * @param joinPoint
	 * @return
	 */
	protected String getAimMethodName(JoinPoint joinPoint) {
		return joinPoint.getSignature().getName();
	}

	/**
	 * 得到目标函数的参数值
	 * 
	 * @param joinPoint
	 * @return
	 */
	protected Object[] getArgs(JoinPoint joinPoint) {
		Object[] result = joinPoint.getArgs();
		
		if(result != null && result.length != 0) {
			return result;
		}
		
		return null;
	}

	/**
	 * 目标函数操作之前
	 * 
	 * @param joinPoint
	 */
	public abstract void doBefore(JoinPoint joinPoint);

	/**
	 * 目标函数操作的环绕通知
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	public abstract Object doAround(ProceedingJoinPoint joinPoint) throws Throwable;

	/**
	 * 目标函数操作之后
	 * 
	 * @param joinPoint
	 */
	public abstract void doAfter(JoinPoint joinPoint);

	/**
	 * 目标函数返回之后
	 * 
	 * @param joinPoint
	 * @param returnValue
	 */
	public abstract void doReturn(JoinPoint joinPoint, Object returnValue);

	/**
	 * 目标函数抛出异常之后
	 * 
	 * @param joinPoint
	 * @param e
	 */
	public abstract void doThrowing(JoinPoint joinPoint, Exception e);
}