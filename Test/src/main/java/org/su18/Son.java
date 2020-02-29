package org.su18;

public class Son extends Father {


	public static void main(String[] args) {
		Son son = new Son();
		son.say();
	}

	@Override
	public void say() {
		super.say();
		System.out.println("Son says:I see dead people!");
	}

}
