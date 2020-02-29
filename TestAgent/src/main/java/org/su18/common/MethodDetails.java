package org.su18.common;

/**
 * 定义一个类用于存放方法信息，用于展示
 */
public class MethodDetails {

	/**
	 * 方法所属类的相关信息
	 */
	private final ClassDetails classDetails;

	/**
	 * 方法访问级别
	 */
	private final int methodAccess;

	/**
	 * 方法签名
	 */
	private final String methodSignature;

	/**
	 * 方法异常
	 */
	private final String[] exceptions;

	/**
	 * 方法名
	 */
	private final String methodName;

	/**
	 * 方法参数描述符
	 */
	private final String methodArgsDesc;


	public MethodDetails(ClassDetails classDetails, int methodAccess, String methodSignature,
	                     String[] exceptions, String methodName, String methodArgsDesc) {

		this.classDetails = classDetails;
		this.methodAccess = methodAccess;
		this.methodSignature = methodSignature;
		this.exceptions = exceptions;
		this.methodName = methodName;
		this.methodArgsDesc = methodArgsDesc;
	}

	/**
	 * 重写 toString 方法用于展示
	 *
	 * @return 返回方法信息详情
	 */
	@Override
	public String toString() {
		StringBuilder info = new StringBuilder();
		info.append(String.format("|%55s|\n", "||||| Method Details |||||"));
		info.append(String.format("|%-55s|\n", " Method Name:" + (methodName.equals("<init>") ? "Constructor" : methodName)));
		info.append(String.format("|%-55s|\n", " Class Name:" + classDetails.getClassName()));
		info.append(String.format("|%-55s|\n", " Method Access:" + methodAccess));
		info.append(String.format("|%-55s|\n", " Method Signature:" + methodSignature));
		info.append(String.format("|%-55s|\n", " Method Args Descriptor:" + methodArgsDesc));
		info.append(String.format("|----------%45s|\n", "----------"));
		return info.toString();
	}

	public ClassDetails getClassDetails() {
		return classDetails;
	}

	public int getMethodAccess() {
		return methodAccess;
	}

	public String getMethodSignature() {
		return methodSignature;
	}

	public String[] getExceptions() {
		return exceptions;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getMethodArgsDesc() {
		return methodArgsDesc;
	}
}
