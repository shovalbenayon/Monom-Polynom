package Ex1Testing;

import Ex1.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class PolynomTest {

    @Test
    public void add_and_sub (){
        String [][] Monoms = {{"4x^2" , "2x^2" , "-3.4x^2" , "-1X^2"},
                { "-3x^7", "-x", "x^8", "7", "-1X^3", "x^1", "x^0"}};
        Polynom p1 = new Polynom();
        Polynom p2 = new Polynom();
        for (int i = 0; i < Monoms[0].length; i++) {
            Monom m = new Monom(Monoms[0][i]);
            p1.add(m);
        }

        for (int i = 0; i < Monoms[1].length; i++) {
            Monom m = new Monom(Monoms[1][i]);
            p2.add(m);
        }
        Polynom_able temp = (Polynom_able) p1.copy();
        Polynom_able t = (Polynom_able) p2.copy();

        t.substract(p1);
        temp.substract(p2);
        temp.add(t);
        if (!temp.isZero())
            fail("ERR : polynom should be zero");
    }
    @Test
    public void iszero_mul(){
      Polynom p1 = new Polynom();
      Polynom p2 = new Polynom();
      String [][] m = {{"3x^8" , "4.12x^3" , "1" , "x^6" , "-7x^2" , "9.3x" , "3.7x^3"},
              {"8x^5" , "6.9x^2" , "8" , "5.3x^1"} };
        for (int i = 0; i <m[0].length ; i++) {
           Monom temp = new Monom(m[0][i]);
           p1.add(temp);
        }
        for (int i = 0; i <m[1].length ; i++) {
            Monom temp = new Monom(m[1][i]);
            p2.add(temp);
        }
        p1.multiply(p2);
        Monom y = new Monom("0");

        p1.multiply(y);

        if (!p1.isZero())
            fail();
    }
    @Test
    public void area_root(){
        Polynom mp = new Polynom ("0.173x^77+4x^6+3x^0");
        double expected = -1.025390625;
        double actual = mp.root(-30, 30, 0.04);
        assertEquals(expected, actual);

        expected = -1.04296875;
        actual = mp.root(-6, 30, 0.04);
        assertEquals(expected, actual);

        expected = 73.82870541469453;
        Polynom p1 = new Polynom("5x^6+3x^2-4x^4");
        actual = p1.area(0, 2, 0.000001);
        assertEquals(expected, actual);

        Polynom p2 = new Polynom();
        String [] t = {"4x^6", "-5x^5", "1"};
        for (int i = 0; i <t.length ; i++) {
            Monom temp = new Monom(t[i]);
            p2.add(temp);
        }

        expected = 150.28713393240898;
        actual = p2.area(-2, 2, 0.000001);
        assertEquals(expected, actual);

    }

    @Test
    public void selfcopy_copy(){
        String [] Monoms = {"2.0x^8","-6.0x^7","6.0x^3","-x^2","4.0x","14.0"};
        Polynom equal = new Polynom("2.0x^8-6.0x^7+6.0x^3-x^2+4.0x+14.0");
        Polynom p = new Polynom();
        Polynom q =new Polynom("3x^2+1.8x-5.80x+0");
        for (int i = 0; i <Monoms.length ; i++) {
            Monom temp = new Monom(Monoms[i]);
            p.add(temp);
        }

        Polynom p_copy = (Polynom)p.copy();
        Polynom q_copy = (Polynom)q.copy();
        assertEquals(p, p_copy);

        assertEquals(q, q_copy);
        assertEquals(p, equal);

    }
    @Test
    public void equals_init(){
        Polynom p1 = new Polynom("3.0x^5");
        Monom m = new Monom("3.0x^5");
        if (!p1.equals(m))
            fail();

        Polynom temp = new Polynom();
        Polynom equal = new Polynom("2.0x^8-6.0x^7+6.0x^3-x^2+4.0x+14.0");
        String s = equal.toString();
        temp.initFromString(s);
        assertEquals(temp , equal);

        String [] mon = {"4.678x^8" , "-1.0x^7" , "-9x^1" , "0" , "1.0x^0"};
        Polynom init = new Polynom();
        for (int i = 0 ; i < mon.length ; i++) {
            init.initFromString(mon[i]);
        }
        Polynom initstring = new Polynom("4.678x^8   -1.0x^7-9x^1+0+1.0x^0");
        assertEquals(init , initstring);
        function f = new ComplexFunction();
        s = "4.678x^8-1.0x^7-9x^1+0+1.0x^0";
        f.initFromString(s);
        if (!initstring.equals(f))
            fail();
    }

}
