package org.su18.agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.su18.asm.MyClassVisitor;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
	                        ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

		// 将常用的类名转换为 JVM 认识的类名
		className = className.replace("/", ".");

		// 如果类名为我们指定的类
		if (className.equals("org.su18.Son")) {

			// 处理逻辑

			// 创建 ClassReader，传入类字节码
			ClassReader classReader = new ClassReader(classfileBuffer);
			// 使用 ClassWriter.COMPUTE_MAXS 自动计算局部变量表和操作数栈
			ClassWriter cw = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
			// 实例化自己的 ClassVisitor
			MyClassVisitor classVisitor = new MyClassVisitor(Opcodes.ASM7, cw, className, loader, classfileBuffer);
			// 使用 accept 方法解析字节码中元素，传入自定义 classVisitor，执行逻辑
			classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);

			// 使用 ClassWriter 返回修改后的类字节码
			return cw.toByteArray();


		}

		return classfileBuffer;
	}
}
