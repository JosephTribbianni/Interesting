package org.su18.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.su18.common.ClassDetails;
import org.su18.common.MethodDetails;


public class MyClassVisitor extends ClassVisitor {

	private final int api;

	private final ClassWriter classWriter;

	private final String className;

	private final ClassLoader loader;

	/**
	 * 类字节码
	 */
	private final byte[] classfileBuffer;

	private ClassDetails classDetails;

	/**
	 * 构造方法，调用父类的构造方法，并储存相关数据
	 *
	 * @param api             asm 版本号
	 * @param cw              ClassWriter
	 * @param className       类名
	 * @param loader          类加载器
	 * @param classfileBuffer 类字节码
	 */
	public MyClassVisitor(final int api, final ClassWriter cw, final String className,
	                      final ClassLoader loader, final byte[] classfileBuffer) {
		super(api, cw);
		this.api = api;
		this.classWriter = cw;
		this.className = className;
		this.loader = loader;
		this.classfileBuffer = classfileBuffer;
	}

	/**
	 * 重写父类 visit 方法，将相关信息进行记录
	 *
	 * @param version    jdk版本
	 * @param access     类访问级别
	 * @param name       类名
	 * @param signature  类签名
	 * @param superName  父类名
	 * @param interfaces 实现的接口
	 */
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		classDetails = new ClassDetails(api, version, access, className.replace("/", "."),
				signature, superName.replace("/", "."), interfaces, loader, classfileBuffer, classWriter);
		// 打印类信息
		System.out.println(classDetails);
		super.visit(version, access, name, signature, superName, interfaces);

	}

	/**
	 * 重写 visitMethod 方法，在访问方法时，调用我们自己的 MyMethodVisitor
	 *
	 * @param access     访问级别
	 * @param name       方法名
	 * @param descriptor 描述符
	 * @param signature  签名
	 * @param exceptions 异常
	 * @return 返回 MethodVisitor
	 */
	@Override
	public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
		// 储存方法信息
		MethodDetails methodDetails = new MethodDetails(classDetails, access, signature, exceptions, name, descriptor);

		// 打印方法信息
		System.out.println(methodDetails);

		// 执行父类 visitMethod 方法，返回 MethodVisitor 对象
		MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);

		// 返回自己的 MethodVisitor 方法
		return new MyMethodVisitor(methodDetails, mv);
	}
}
