package org.su18.common;


import org.objectweb.asm.ClassWriter;

/**
 * 定义一个类存放相关信息，用于展示
 */
public class ClassDetails {


	/**
	 * ASM版本
	 */
	private final int api;

	/**
	 * JDK版本
	 */
	private final int version;


	/**
	 * 类名
	 */
	private final String className;

	/**
	 * 类加载器
	 */
	private ClassLoader classLoader;

	/**
	 * 类访问级别
	 */
	private final int access;

	/**
	 * 签名
	 */
	private final String signature;


	/**
	 * 父类名
	 */
	private final String superClassName;


	/**
	 * 实现的接口
	 */
	private final String[] interfacesClass;

	/**
	 * 类字节码
	 */
	private final byte[] classfileBuffer;

	private final ClassWriter classWriter;

	public ClassDetails(final int api, final int version, final int access,
	                    final String className, final String signature,
	                    final String superClassName, final String[] interfacesClass,
	                    final ClassLoader classLoader, final byte[] classfileBuffer,
	                    final ClassWriter classWriter) {

		this.api = api;
		this.version = version;
		this.access = access;
		this.className = className;
		this.signature = signature;
		this.superClassName = superClassName;
		this.interfacesClass = interfacesClass;
		this.classLoader = classLoader;
		this.classfileBuffer = classfileBuffer;
		this.classWriter = classWriter;
	}

	/**
	 * 重写 toString 方法用于展示
	 *
	 * @return 返回类信息详情
	 */
	@Override
	public String toString() {
		StringBuilder info = new StringBuilder();

		info.append(String.format("|%55s|\n", "||||| Class Details |||||"));
		info.append(String.format("|%-55s|\n", " ASM Version:" + api));
		info.append(String.format("|%-55s|\n", " JDK Version:" + version));
		info.append(String.format("|%-55s|\n", " Class Name:" + className));
		info.append(String.format("|%-55s|\n", " Class Loader:" + classLoader));
		info.append(String.format("|%-55s|\n", " Class Access:" + access));
		info.append(String.format("|%-55s|\n", " Class Signature:" + signature));
		info.append(String.format("|%-55s|\n", " Super Class Name:" + superClassName));
		info.append(String.format("|----------%45s|\n", "----------"));

		return info.toString();
	}

	public int getApi() {
		return api;
	}

	public int getVersion() {
		return version;
	}

	public String getClassName() {
		return className;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public int getAccess() {
		return access;
	}

	public String getSignature() {
		return signature;
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public String[] getInterfacesClass() {
		return interfacesClass;
	}

	public byte[] getClassfileBuffer() {
		return classfileBuffer;
	}

	public ClassWriter getClassWriter() {
		return classWriter;
	}
}
