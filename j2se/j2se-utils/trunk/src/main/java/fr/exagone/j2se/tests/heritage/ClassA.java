package fr.exagone.j2se.tests.heritage;

public class ClassA {

	int i = 0;
	
	public ClassA() {
		System.out.println("Constructeur A");
		print();
	}
	
	static{
		System.out.println("Static A");
	}
	
	{
		System.out.println("Non Static A");
	}
	
	public void print() {
		System.out.println("Print i (A): " + i);
	}

	public static void main(String[] args) {
		new ClassA();
	}
	
}
