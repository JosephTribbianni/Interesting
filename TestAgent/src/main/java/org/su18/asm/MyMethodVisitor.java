package org.su18.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;
import org.su18.common.MethodDetails;

/**
 * 自定义 MethodVisitor，这里使用了 ASM 提供的 AdviceAdapter 工具类
 * 用于在 method 字节码中插入增强代码，核心方法在于 onMethodEnter、onMethodExit、visitMaxs
 */
public class MyMethodVisitor extends AdviceAdapter {

	/**
	 * 是否为静态方法
	 */

	private MethodDetails methodDetails;


	protected MyMethodVisitor(MethodDetails methodDetails, MethodVisitor mv) {

		// 执行 super 方法
		super(
				methodDetails.getClassDetails().getApi(), mv, methodDetails.getMethodAccess(),
				methodDetails.getMethodName(), methodDetails.getMethodArgsDesc()
		);

		this.methodDetails = methodDetails;

	}

	@Override
	public void onMethodEnter() {
		super.onMethodEnter();
		mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		String methodName = methodDetails.getMethodName();
		mv.visitLdcInsn("》》》》》 " + (methodName.equals("<init>") ? "构造方法" : methodName) + " 方法进入 Hook 《《《《《");
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

	}

	@Override
	protected void onMethodExit(final int opcode) {
		super.onMethodExit(opcode);
		mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		String methodName = methodDetails.getMethodName();
		mv.visitLdcInsn("》》》》》 " + (methodName.equals("<init>") ? "构造方法" : methodName) + " 方法退出 Hook 《《《《《");

		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

	}
}
