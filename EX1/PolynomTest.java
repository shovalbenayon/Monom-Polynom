package EX1;

public class PolynomTest {
	public static void main(String[] args) {
		test1();
		test2();
	}
	public static void test1() {
		Polynom r = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		r.add(m);
		double aa = r.area(0, 1, 0.0001);
		r.substract(r);
		System.out.println("r:"+r);
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		System.out.println(p1.toString());
		for(int i=0;i<monoms1.length;i++) {
		Monom m = new Monom(monoms1[i]);
		p1.add(m);
		}
	for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1.toString());
		System.out.println("p2: "+p2.toString());
		p1.add(p2);
		System.out.println("p1+p2: "+p1.toString());
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1.toString());
		String s1 = p1.toString();
		//Polynom_able pp1 = Polynom.parse(s1);
		//System.out.println("from string: "+pp1);
	}
}
