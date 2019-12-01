
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		if (a == 0)
			b = 0;
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() { return this._coefficient; }

	public int get_power() {
		return this._power;
	}

	/**
	 * this method returns the derivative monom of this.
	 * @return new monom after derivative
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}

	/**
	 * this method returns conputes the value of f(x).
	 * @return double as y value
	 */
	public double f(double x) {
		double ans;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	}
	public boolean isZero() {return this.get_coefficient() == 0;}

	/**
	 * this method converts string to new monom by spliting the string to coefficient and power
	 * @param s - the string we will convert in to monom
	 */
	public Monom(String s) {
		s = s.toLowerCase();
		if (!s.contains("x")) {
			if (!isNumeric(s))
				throw new RuntimeException("ERR : invalid input , should get double , got : " + s);
			this.set_coefficient(Double.parseDouble(s));
			this.set_power(0);
			return;
		}
		StringBuilder coeff = new StringBuilder();
		String pow = "";
		int temp = 0;
		boolean b = true;
		for (int i = 0; i < s.length() && b ; i++) {
			if (s.charAt(i) != 'x')
				coeff.append(s.charAt(i));
			else {
				b = false;
			}
			temp = i;
		}
		Double coeffdouble = 1.0;

		try {
			if (coeff.toString().equals("-"))
				coeffdouble = Double.parseDouble("-1");
			else if ((coeff.length() > 0) && !coeff.toString().equals("-") )
				coeffdouble = Double.parseDouble(coeff.toString());

		}
		catch (Exception e){
			System.out.println("ERR : didn't get double for coefficient. got : " + coeff + "	from string	" + s);
		}

		int x_location = 0;

		for (int i = temp ; i < s.length() ; i++) {
			x_location = s.indexOf('x');
			if (x_location != s.length() -1) {
				if ( s.charAt(x_location + 1) == '^') {
					if (isNumeric(s.substring(x_location + 2, s.length())))
						pow = s.substring(x_location + 2, s.length());
					else throw new RuntimeException("ERR : invalid input , should get Integer , got : " +
							s.substring(x_location + 2, s.length()));
				}
				else throw new RuntimeException("ERR : invalid input , should get power for monom ");
			}
		}
		Integer powInteger = 1;
		try {
			if (!pow.isEmpty())
				powInteger = new Integer(pow);
			if (temp == s.length() - 1 && s.charAt(s.length() - 1) != 'x')
				powInteger = 0;
		}
		catch (Exception e){
			System.out.println("ERR : invalid input , should get power for monom ");
		}
		if (coeffdouble == 0){
			powInteger = 0;
		}
		this.set_power(powInteger);
		this.set_coefficient(coeffdouble);
	}
	/**
	 * this method summarize between monoms only if there powers are eqaul
	 * @param m - the new monom to sum up.
	 */
	public void add(Monom m) {
		double new_coeff ;
		if (this.get_power() == m.get_power()) {
			new_coeff = this.get_coefficient() + m.get_coefficient();
			this.set_coefficient(new_coeff);
		}
		else throw new RuntimeException("ERR the powers of Monom are not equal");
	}

	/**
	 * his method multipy between monoms . summarize between the powers and multipy the coefficient
	 * @param d - the new monom to mulitpy
	 */
	public void multipy(Monom d) {
		double new_coeff = this.get_coefficient() * d.get_coefficient();
		int new_pow = this.get_power() + d.get_power();
		this.set_coefficient(new_coeff);
		this.set_power(new_pow);
	}
	/**
	 * this method prints the monom
	 */
	public String toString() {
		String ans = "";
		if (this.get_coefficient() == 1)
			if (this.get_power() == 1)
				ans += "x";
			else if (this.get_power() == 0)
				ans += "1";
			else
				ans += "x^" + get_power();
		else if (this.get_coefficient() == 0)
			ans += "0";
		else if (this.get_coefficient() == -1)
			if (this.get_power() == 1 )
				ans += "-1.0x";
			else if ( this.get_power() == 0)
				ans += "-1";
			else
				ans += "-x^" + this.get_power();
		else if (this.get_power() == 1)
			ans += this.get_coefficient() + "x";
		else if (this.get_power() == 0)
			ans += this.get_coefficient();
		else
				ans += this.get_coefficient() + "x^" + this.get_power();
		return ans;
	}

	@Override
	public function initFromString(String s) {
		return null;
	}

	public void set_coefficient(double a){
		this._coefficient = a;
	}
	public void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}

	/**
	 * this method calculating if str is a number
	 * @param str - string to check if its a number
	 * @return boolean
	 */
	private boolean isNumeric (String str){
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	/**
	 * this method copy monom to new monom
	 * @return Monom
	 */
	public Monom copy(){
		Monom new_monom = new Monom(this.get_coefficient() , this.get_power());
		return new_monom;
	}

	/**
	 * this method check if tow monom are equals
	 * @param m1 - new monom to check if equal
	 * @return true if equal
	 */
	public boolean equals(Monom m1){
		if (m1.get_coefficient() == this.get_coefficient() && m1.get_power() == this.get_power())
			return true;
		if (Math.abs(m1.get_coefficient() - this.get_coefficient()) <= EPSILON )
			return true;
		return false;
	}

	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient;
	private int _power;


}
