package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexFunctionTest {



    @Test
    public void initFromStringtest() {
        String init = "mul(div(max(2.0x^7+1.49x^5,plus(8.0x^7-5.6x^2+4.0,3.5x^3-7.509x^2+1.0)),-1.0x^3+7.6x^2),mul(x^9+8.93x^5,-9.39393392x^4+43.0x^2))";
        function temp = new ComplexFunction();
        function init_fun = temp.initFromString(init);
        assertEquals(init_fun.toString(),init);
    }

    /**
     * Checks that the constructor that accepts 2 functions and operator works properly
     */
    @Test
    public void check_constractor()
    {
        function f1_temp = new ComplexFunction();
        function f1 = f1_temp.initFromString("3.5x^2+4.0x-5.2");
        function f2_temp = new ComplexFunction();
        function f2 = f2_temp.initFromString("7x^5-9.1x^2+1.0");


        ComplexFunction cf_plus= new ComplexFunction("plus",f1,f2);
        assertEquals("3.5x^2+4.0x-5.2",cf_plus.left().toString());
        assertEquals("7.0x^5-9.1x^2+1.0",cf_plus.right().toString());
        assertEquals("Plus",cf_plus.getOp().toString());

        ComplexFunction cf_times= new ComplexFunction("mul",f1,f2);
        assertEquals("3.5x^2+4.0x-5.2",cf_times.left().toString());
        assertEquals("7.0x^5-9.1x^2+1.0",cf_times.right().toString());
        assertEquals("Times",cf_times.getOp().toString());

        ComplexFunction cf_divid= new ComplexFunction("Div",f1,f2);
        assertEquals("3.5x^2+4.0x-5.2",cf_divid.left().toString());
        assertEquals("7.0x^5-9.1x^2+1.0",cf_divid.right().toString());
        assertEquals("Divid",cf_divid.getOp().toString());

        ComplexFunction cf_max= new ComplexFunction("Max",f1,f2);
        assertEquals("3.5x^2+4.0x-5.2",cf_max.left().toString());
        assertEquals("7.0x^5-9.1x^2+1.0",cf_max.right().toString());
        assertEquals("Max",cf_max.getOp().toString());

        ComplexFunction cf_min= new ComplexFunction("Min",f1,f2);
        assertEquals("3.5x^2+4.0x-5.2",cf_min.left().toString());
        assertEquals("7.0x^5-9.1x^2+1.0",cf_min.right().toString());
        assertEquals("Min",cf_min.getOp().toString());

        ComplexFunction cf_comp= new ComplexFunction("Comp",f1,f2);
        assertEquals("3.5x^2+4.0x-5.2",cf_comp.left().toString());
        assertEquals("7.0x^5-9.1x^2+1.0",cf_comp.right().toString());
        assertEquals("Comp",cf_comp.getOp().toString());
    }

    /**
     *test for copy function
     */
    @Test
    public void copy_complexfunction() {
        function cf1 = new ComplexFunction();
        function cf2 = new ComplexFunction();
        function cf1_copy = new ComplexFunction();
        function cf2_copy = new ComplexFunction();
        String s1 = "mul(div(max(plus(8.0x^7-5.6x^2+4.0,3.5x^3-7.50x^2+1.0),-1.0x^3+7.6x^0),-9.39393392x^4+43x^0),2x^5+4.0)";
        String s2 = "mul(min(max(comp(4.555x^7-1.9x^3+4.34,6.99x^4-7x^2+1),plus(-4.54x+2.0x^0,4.5x^3)),-5.324x^4+4x^0),3x^7+4.0)";
        cf1.initFromString(s1);
        cf2.initFromString(s2);
        cf1_copy = cf1.copy();
        cf2_copy = cf2.copy();
        assertEquals(cf1 , cf1_copy);
        assertEquals(cf2 , cf2_copy);
    }

    @Test
    public void plus() {
        String s1 = "plus(x^5-5.87x,-1.0x^3-4.0)";
        String s2 = "x^5-5.87x";
        String s3 = "-1.0x^3-4.0";
        function cf1 = new ComplexFunction();

        function c1 = cf1.initFromString(s1);
        Polynom c2 = new Polynom(s2);
        Polynom c3 = new Polynom(s3);
        function plusfunction = new ComplexFunction(c2);
        ((ComplexFunction) plusfunction).plus(c3);

        System.out.println(plusfunction.toString());
        if (!plusfunction.equals(c1))
            fail("functions should be equals");
    }



    @Test
    public void mul() {
        String s1 = "mul(x^5-5.87x,-1.0x^3-4.0)";
        String s2 = "x^5-5.87x";
        String s3 = "-1.0x^3-4.0";
        function cf1 = new ComplexFunction();

        function c1 = cf1.initFromString(s1);
        Polynom c2 = new Polynom(s2);
        Polynom c3 = new Polynom(s3);
        function mulfunction = new ComplexFunction(c2);
        ((ComplexFunction) mulfunction).mul(c3);

        System.out.println(mulfunction.toString());
        if (!mulfunction.equals(c1))
            fail("functions should be equals");

    }

    @Test
    public void div() {
        String s1 = "div(x^5-5.87x,-1.0x^3-4.0)";
        String s2 = "x^5-5.87x";
        String s3 = "-1.0x^3-4.0";
        function cf1 = new ComplexFunction();

        function c1 = cf1.initFromString(s1);
        Polynom c2 = new Polynom(s2);
        Polynom c3 = new Polynom(s3);
        function divfunction = new ComplexFunction(c2);
        ((ComplexFunction) divfunction).div(c3);

        System.out.println(divfunction.toString());
        if (!divfunction.equals(c1))
            fail("functions should be equals");
    }

    @Test
    public void max() {String s1 = "max(x^5-5.87x,-1.0x^3-4.0)";
        String s2 = "x^5-5.87x";
        String s3 = "-1.0x^3-4.0";
        function cf1 = new ComplexFunction();

        function c1 = cf1.initFromString(s1);
        Polynom c2 = new Polynom(s2);
        Polynom c3 = new Polynom(s3);
        function maxfunction = new ComplexFunction(c2);
        ((ComplexFunction) maxfunction).max(c3);

        System.out.println(maxfunction.toString());
        if (!maxfunction.equals(c1))
            fail("functions should be equals");
    }

    @Test
    public void min() {
        String s1 = "min(x^5-5.87x,-1.0x^3-4.0)";
        String s2 = "x^5-5.87x";
        String s3 = "-1.0x^3-4.0";
        function cf1 = new ComplexFunction();

        function c1 = cf1.initFromString(s1);
        Polynom c2 = new Polynom(s2);
        Polynom c3 = new Polynom(s3);
        function minfunction = new ComplexFunction(c2);
        ((ComplexFunction) minfunction).min(c3);

        System.out.println(minfunction.toString());
        if (!minfunction.equals(c1))
            fail("functions should be equals");
    }

    @Test
    public void comp() {
        String s1 = "comp(x^5-5.87x,-1.0x^3-4.0)";
        String s2 = "x^5-5.87x";
        String s3 = "-1.0x^3-4.0";
        function cf1 = new ComplexFunction();

        function c1 = cf1.initFromString(s1);
        Polynom c2 = new Polynom(s2);
        Polynom c3 = new Polynom(s3);
        function compfunction = new ComplexFunction(c2);
        ((ComplexFunction) compfunction).comp(c3);

        System.out.println(compfunction.toString());
        if (!compfunction.equals(c1))
            fail("functions should be equals");
    }

    @Test
    public void left() {
        String s1 = "max(-15x^7+5.6x^2+3.1,0.3333x^5-1.56666x+4.5)";
        function f1 = new ComplexFunction();
        function f1_init = f1.initFromString(s1);
        assertEquals("-15.0x^7+5.6x^2+3.1" , ((ComplexFunction) f1_init).left().toString());
    }

    @Test
    public void right() {
        String s1 = "max(-15x^7+5.6x^2+3.1,0.3333x^5-1.56666x+4.5)";
        function f1 = new ComplexFunction();
        function f1_init = f1.initFromString(s1);
        assertEquals("0.3333x^5-1.56666x+4.5" , ((ComplexFunction) f1_init).right().toString());
    }

    @Test
    public void getOp() {
        String s1 = "max(-15x^7+5.6x^2+3.1,0.3333x^5-1.56666x+4.5)";
        function f1 = new ComplexFunction();
        function f1_init = f1.initFromString(s1);
        assertEquals("Max" , ((ComplexFunction) f1_init).getOp().toString());
    }

    @Test
    public void f() {
        String s1 = "min(x^2,7.444x^5)";
        String s2 = "plus(5.7x^4,3.0)";
        function s1_temp = new ComplexFunction();
        function s2_temp = new ComplexFunction();
        function f1 = s1_temp.initFromString(s1);
        function f2 = s2_temp.initFromString(s2);

        for (int i = 0; i < 10 ; i++) {
            double ans_f1 = f1.f(i);
            double exp_ans = Math.pow(i,2);
            assertEquals(ans_f1 , exp_ans);
            double ans_f2 = f2.f(i);
            exp_ans = 5.7*Math.pow(i,4) + 3.0;
            assertEquals(ans_f2 , exp_ans);

        }
    }


    @Test
    public void copy() {
        String s1 = "min(-1.0x^7+5.7x^4+3.7,6.7x^5-1.8x+6.7)";
        String s2 = "comp(div(x+1.0,min(5.7x^4,1.0x-4.0)),3.0)";

        function s1_temp = new ComplexFunction();
        function s2_temp = new ComplexFunction();
        function f1 = s1_temp.initFromString(s1);
        function f2 = s2_temp.initFromString(s2);

        function f1_copy = f1.copy();
        function f2_copy = f2.copy();

        assertEquals(f1, f1_copy);
        assertEquals(f2, f2_copy);

    }
    
}
