package Ex1;

import Ex1.Monom;
import Ex1.Polynom;

public class MyTests {
    public static void main(String[] args) {
        goodmonomTests();
        PolynomTests();
    }
    /*
    good_form = {"0" , "-3*x^7" , "-x" , "x^8" , "7" , "-1*X^3" , "x^1"};
    bad_form = {"0x^5" , "-4x7" , "-x^-6" , "y^8", "5x" };
    should be-
    *****  Test of good forms of Monom:  *****
    0)current monom is : 0
    coefficient = 0.0
    power = 0
    monom * monom = 0
    f(x) = 0.0
    f'(x) = 0
    monom is zero? true
    equals = true
    1)current monom is : -3x^7
    coefficient = -3.0
    power = 7
    monom * monom = 9.0x^14
    f(x) = -3.0
    f'(x) = -21.0x^6
    monom is zero? false
    equals = true
    2)current monom is : -x
    coefficient = -1.0
    power = 1
    monom * monom = x^2
    f(x) = -2.0
    f'(x) = -1
    monom is zero? false
    equals = true
    3)current monom is : x^8
    coefficient = 1.0
    power = 8
    monom * monom = x^16
    f(x) = 6561.0
    f'(x) = 8.0x^7
    monom is zero? false
    equals = true
    4)current monom is : 7
    coefficient = 7.0
    power = 0
    monom * monom = 49.0
    f(x) = 7.0
    f'(x) = 0
    monom is zero? false
    equals = true
    5)current monom is : -1X^3
    coefficient = -1.0
    power = 3
    monom * monom = x^6
    f(x) = -125.0
    f'(x) = -3.0x^2
    monom is zero? false
    equals = true
    6)current monom is : x^1
    coefficient = 1.0
    power = 1
    monom * monom = x^2
    f(x) = 6.0
    f'(x) = 1
    monom is zero? false
    equals = true
    7)current monom is : x^0
    coefficient = 1.0
    power = 0
    monom * monom = 1
    f(x) = 1.0
    f'(x) = 0
    monom is zero? false
    equals = true
    ERR : powers are not equals, got    0    and     7
    ERR : powers are not equals, got    7    and     1
    ERR : powers are not equals, got    1    and     8
    ERR : powers are not equals, got    8    and     0
    ERR : powers are not equals, got    0    and     3
    ERR : powers are not equals, got    3    and     1
    ERR : powers are not equals, got    1    and     0
    ERR : didn't get double for coefficient. got : 0*	from string	0*x^5
    ERR : didn't get double for coefficient. got : -4*	from string	-4*x7
    ERR : can't build monom from string     -4*x7
    ERR : can't build monom from string     -x^-6
    ERR : can't build monom from string     y^8
    ERR : didn't get double for coefficient. got : 5*	from string	5*x
    ERR : didn't get double for coefficient. got : 3*	from string	3*x^2
     ************Polynomtest***********
    polynom p is : x^8-3.0x^7+3.0x^3+7.0
    polynom p and copy of polynom p are equals? true
    polynom p after substract  x^2-4.0x      is : x^8-3.0x^7+3.0x^3-x^2+4.0x+7.0
    polynom p after add  x^8-3.0x^7+3.0x^3+7.0    is : 2.0x^8-6.0x^7+6.0x^3-x^2+4.0x+14.0
    polynom p after multiply by  5.0x^4+8.0x^2-7.0       is : 2.0x^8-6.0x^7+6.0x^3-x^2+4.0x+14.0
    f(2.0x^8-6.0x^7+6.0x^3-x^2+4.0x+14.0) at point  0       is : 14.0
    f(2.0x^8-6.0x^7+6.0x^3-x^2+4.0x+14.0) at point  1       is : 19.0
    f(2.0x^8-6.0x^7+6.0x^3-x^2+4.0x+14.0) at point  2       is : -190.0
    f(2.0x^8-6.0x^7+6.0x^3-x^2+4.0x+14.0) at point  3       is : 179.0
    f(2.0x^8-6.0x^7+6.0x^3-x^2+4.0x+14.0) at point  4       is : 33166.0
    polynom 0.173x^77+4.0x^6+3.0 by given the values (-30, 30, 0.04) root at  -1.025390625
    polynom 0.173x^77+4.0x^6+3.0by given the values (-30, 30, 0.04) area is 3.835237082527351E112
    ****** area test  *******
    the area of this polynom 5.0x^6-4.0x^4+3.0x^2 between 0 to 2 is 73.8286
    so if eps value is 0.000001 so we suppose to get approximately the same:
    73.82870541469453

    the area of this polynom x^3-2.0x+1 between -2 to 2  (above axis x)is 4.54508
    so if eps value is 0.000001 so we suppose to get approximately the same:
    4.545087471322554

     */

    public static void goodmonomTests() {
        System.out.println("*****  Test of good forms of Monom:  *****");
        String[] good_form = {"0", "-3x^7", "-x", "x^8", "7", "-1X^3", "x^1", "x^0"};
        for (int i = 0; i < good_form.length; i++) {
            Monom m = new Monom(good_form[i]);
            String s = m.toString();
            Monom k = new Monom(s);
            Monom n = new Monom(m.copy());
            n.multipy(n);
            double f = m.f(i);
            System.out.println(i + ")" + "current monom is : " + good_form[i] + "\n coefficient = " + m.get_coefficient() +
                    "\n power = " + m.get_power() + "\n monom * monom = " + n.toString() + "\n f(x) = "
                    + f + "\n f'(x) = " + m.derivative().toString() + "\n monom is zero? " + m.isZero() + "\n equals = " + m.equals(k));
        }
        for (int j = 0; j < good_form.length -1; j++) {
            Monom p = new Monom(good_form[j]);
            Monom l = new Monom(good_form[j + 1]);
            try {
                p.add(l);
            }
            catch (Exception e) {
                System.out.println("ERR : powers are not equals, got    " + p.get_power() + "    and     " + l.get_power());
            }
        }

        String[] bad_form = {"0*x^5", "-4*x7", "-x^-6", "y^8", "5*x"};
        for (int i = 0; i < bad_form.length; i++) {
            try {
                Monom m = new Monom(bad_form[i]);
            } catch (Exception e) {
                System.out.println("ERR : can't build monom from string     " + bad_form[i]);
            }
        }

    }


    public static void PolynomTests(){
        System.out.println("************Polynomtest***********");
        String [] good_form = {"0" , "-3x^7" , "-x" , "x^8" , "7" , "-1X^3" , "x^1" ,"4x^3"};
        Polynom p =new Polynom();
        for (int i = 0; i < good_form.length ; i++) {
            p.add(new Monom(good_form[i]));
        }
        System.out.println("polynom p is : " + p.toString());
        Polynom q =new Polynom("3*x^2+1.8x-5.80x+0");
        Polynom sub = (Polynom)(p.copy());
        System.out.println("polynom p and copy of polynom p are equals? " + sub.equals(p));
        sub.substract(q);
        System.out.println("polynom p after substract  " + q.toString() + "      is : "+ sub.toString() );
        sub.add(p);
        System.out.println("polynom p after add  " + p.toString() + "    is : "+ sub.toString() );
        Polynom o = new Polynom("5x^4+8x^2-7");
        System.out.println("polynom p after multiply by  " + o.toString() + "       is : "+ sub.toString() );
        for (int i = 0; i < 5 ; i++) {
            System.out.println("f("+sub.toString()+")"+" at point  " + i + "       is : "+ sub.f(i));
        }
        Polynom mp = new Polynom ("0.173x^77+4x^6+3x^0");
        System.out.println("polynom "+ mp.toString() + " by given the values (-30, 30, 0.04) root at  "+
                mp.root(-30, 30, 0.04));
        System.out.println("polynom "+ mp.toString() + "by given the values (-30, 30, 0.04) area is "+
                mp.area(-6, 30, 0.04));

        System.out.println("****** area test  *******");
        Polynom p1 = new Polynom("5x^6+3x^2-4x^4");
        System.out.println("the area of this polynom "+p1+" between 0 to 2 is 73.8286");
        System.out.println("so if eps value is 0.000001 so we suppose to get approximately the same: ");
        System.out.println(p1.area(0, 2, 0.000001));
        System.out.println();

        p1 = new Polynom("1x^3-2x^1+1x^0");
        System.out.println("the area of this polynom "+p1+" between -2 to 2  (above axis x)is 4.54508 ");
        System.out.println("so if eps value is 0.000001 so we suppose to get approximately the same: ");
        System.out.println(p1.area(-2, 2, 0.000001));

    }
}
