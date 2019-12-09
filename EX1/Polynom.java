package Ex1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	private List<Monom> Polynom = new ArrayList<>();
	private final Monom_Comperator mc = new Monom_Comperator();

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() { ; }
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4*X^3-34*x", "2*x^2-4-1.2*x-7.1", "3-3.4*x+1+3.1*x-1.3-3*X^2-3.1"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		String temp = "";
		int start = 0  ;
		Monom constarctor;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+' ) {
				temp = s.substring(start, i);
				constarctor = new Monom(temp);
				add(constarctor);
				start = i + 1;
			}
			if (s.charAt(i) == '-' && i > 0){
				temp = s.substring(start, i);
				constarctor = new Monom(temp);
				add(constarctor);
				start = i ;
			}
		}
		constarctor = new Monom(s.substring(start , s.length() ));
		add(constarctor);
		this.Polynom.sort(mc);
	}

	public String toString (){
		StringBuilder polynom_string = new StringBuilder();
		Iterator<Monom> iter = iteretor();
		int i=0;
		while (iter.hasNext()){
			Monom temp = iter.next();
			if (i == 0)
				polynom_string.append(temp);
			if (temp.get_coefficient() > 0 && i != 0 )
				polynom_string.append('+').append(temp.toString());
			else if (temp.get_coefficient() != 0 && i != 0)
				polynom_string.append(temp.toString());
			i++;
		}
		return polynom_string.toString();
	}

	@Override
	public function initFromString(String s) {
		function new_func = new Polynom(s);
		return new_func;
	}

	/**
	 * this method computes the value of polynom by given x.
	 * @param x - value for x to calculate the f(x) value
	 * @return double , as the y value
	 */
	public double f(double x) {
		double ans = 0;
		Iterator<Monom> iter = iteretor();
		while (iter.hasNext()) {
			Monom m = iter.next();
			ans += m.f(x);
		}
		return ans;
	}

	/**
	 * this method is adding the givven polynom able to the origin polynom
	 * @param p1 - polynom to add
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		while (iter.hasNext()){
			this.add(iter.next());
		}
	}

	/**
	 * this method add new monom to the polynom
	 * @param m1 Monom
	 */
	public void add(Monom m1) {
		if (m1.get_coefficient() == 0)
			return;
		if (Polynom.isEmpty()){
			Polynom.add(new Monom(m1));
			return;
		}
		Iterator<Monom> it  = iteretor();
		while (it.hasNext()) {
			Monom m = it.next();
			if (m.get_power() == m1.get_power()){
				m.set_coefficient(m.get_coefficient() + m1.get_coefficient());
				if (m.get_coefficient() == 0)
					it.remove();
				return;
			}
		}
		Polynom.add(new Monom(m1));
		Polynom.sort(mc);
	}

	/**
	 * this method substract between two polynomos
	 * @param p1 - polynom to substract
	 */
	public void substract(Polynom_able p1) {
		Polynom np = (Polynom)(p1.copy());
		Iterator<Monom> iter = np.iteretor();
		while (iter.hasNext()){
			Monom minus = new Monom(Monom.MINUS1);
			Monom temp = new Monom(iter.next());
			temp.multipy(minus);
			this.add(temp);
		}
	}

	/**
	 * this method multiply polynom by another polynom
	 * @param p1 - polynom to multiply
	 */
	public void multiply(Polynom_able p1) {
		Iterator<Monom> iter = iteretor();
		Iterator<Monom> iter2 = p1.iteretor();
		Polynom new_polymon = new Polynom();
		while (iter.hasNext()) {
			Monom m = new Monom(iter.next());
				while (iter2.hasNext()){
					Monom temp = new Monom(iter2.next());
					Monom x = new Monom(m);
					x.multipy(temp);
					new_polymon.add(x);
				}
				iter2 = p1.iteretor();
		}
		this.Polynom.sort(mc);
		this.Polynom = new_polymon.Polynom;
	}

	/**
	 * method checks if two polynomos are equals
	 * @param p1 - polynom to check if equals
	 * @return - boolean
	 */
	public boolean equals(Object p1) {
		if (p1 instanceof Polynom) {
			Iterator<Monom> iter = ((Polynom_able) p1).iteretor();
			Iterator<Monom> iter2 = iteretor();
			while (iter.hasNext() && iter2.hasNext()) {
				if (!iter.next().equals(iter2.next()))
					return false;
			}
		}
		if (p1 instanceof Monom){
			Polynom temp = new Polynom();
			temp.add((Monom) p1);
			if (!this.equals(temp))
				return false;
		}
		return true;
	}

	/**
	 * Test if this is the Zero Polynom
	 * @return return true if is zero
	 */
	public boolean isZero() {
		Iterator<Monom> iter = iteretor();
		if (this.Polynom.size() == 1 && iter.next().isZero())
			return true;
		if(this.Polynom.isEmpty())
			return true;
		return false;
	}

	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * @return an approximated value (root) for this (cont.) function
	 */
	public double root(double x0, double x1, double eps) {
		double mid = 0;
		double start = x0;
		double end = x1;
		if (f(start)*f(end) > 0)
			throw new RuntimeException("ERR : bad input , f(x0)*f(x1) should be <= 0");
		while (end - start >= eps){
			mid = (end + start)/2;
			if (f(mid) == 0)
				return mid;
			if (f(start) * f(mid) > 0)
				start = mid;
			else
				end = mid;
		}
		return mid;
	}

	/**
	 * this method copy the polynom to new polynom and returns polynom_able
	 * @return - new  Polynom_able
	 */
	public function copy() {
		function polynom_copy = new Polynom();
		Iterator<Monom> iter = iteretor();
		while (iter.hasNext()){
			Monom temp = iter.next();
			if (polynom_copy instanceof Polynom_able) {
				((Polynom_able)polynom_copy).add(new Monom(temp));
			}
		}
		return polynom_copy;
	}

	/**
	 * this method Compute a new Polynom which is the derivative of this Polynom
	 * @return new polynom_able
	 */
	public Polynom_able derivative() {
		Polynom derivative_pol = new Polynom();
		Iterator<Monom> iter = iteretor();
		while (iter.hasNext()) {
			Monom der = iter.next();
			derivative_pol.add(new Monom(der.derivative()));
		}
		return derivative_pol;
	}

	/**
	 * Compute a Riman's integral from x0 to x1 in eps steps.
	 * if x0>=x1 function will return 0
	 * @param x0 starting pooint
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the approximated area above X-axis below this function bounded in the range of [x0,x1]
	 */
	public double area(double x0, double x1, double eps) {
		if (eps <= 0) {
			try {
				throw new Exception("eps suppose to be bigger then 0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (x0 >= x1) {
			return 0;
		}
		double rymanSum = 0;
		while (x0 < x1) {
			if ((this.f(x0) * eps) > 0)
				rymanSum += this.f(x0) * eps;
			x0 += eps;
		}
		return rymanSum;
	}

	/**
	 * this method create iterator for the polynom
	 * @return iterator
	 */
	public Iterator<Monom> iteretor() {
		Iterator<Monom> iter = Polynom.iterator();
		return iter;
	}

	/**
	 * this method multiply the polynom by the monom
	 * @param m1 monom to multiply
	 */
	public void multiply(Monom m1) {
		if (m1.get_coefficient() == 0)
			this.Polynom.clear();
		if (Polynom.isEmpty()) {
			Polynom.add(new Monom(m1));
			return;
		}
		Iterator<Monom> it  = iteretor();
		while (it.hasNext()) {
			Monom m = it.next();
			m.multipy(m1);
			if (m.get_coefficient() == 0){
				it.remove();
				return;
			}
		}
		Polynom.sort(mc);
	}
}