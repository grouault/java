package fr.exagone.j2se.tests.heritage;

public class ClassB extends ClassA {

	int i = 10;
	
	static {
		System.out.println("Static B");
	}
	
	{
		System.out.println("Non static B");
	}
	
	public ClassB() {
		System.out.println("Constructeur B");
		print();
	}
	
	@Override
	public void print() {
		System.out.println("Print i (B): " + i);
		super.print();
	}
	
	public static void main(String[] args) {
		new ClassB();
		System.out.println("---");
		new ClassB();
	}

}
