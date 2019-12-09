package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class MonomTest {

    @Test
    public void selfcopy_equals() {
        Monom m = new Monom("-3.0x^7");
        Monom n = new Monom("x^8");
        Monom p = new Monom("-1X^3");
        String mstring = m.toString();
        String nstring = n.toString();
        String pstring = p.toString();

        Monom mcopy = new Monom(mstring);
        Monom ncopy = new Monom(nstring);
        Monom pcopy = new Monom(pstring);

      if (!m.equals(mcopy)){
          throw new RuntimeException();
      }
        if (!n.equals(ncopy)){
            throw new RuntimeException();
        }
        if (!p.equals(pcopy)){
            throw new RuntimeException();
        }
    }

    @Test
    public void add_mul(){
        Monom m = new Monom("-4.5x^3");
        Monom n = new Monom("7.9x^5");
        Monom mul = new Monom(m);
        mul.multipy(n);
        try{
            mul.add(n);
        }
        catch (Exception e){
            System.out.println(("expected - powers not equals"));
        }

        mul.multipy(Monom.ZERO);
        if (!mul.isZero()){
            throw new RuntimeException();
        }
        Monom r = new Monom("4x^5");
        try{
            r.add(n);
        }
        catch (Exception e){
            fail("ERR : powers not equals");
        }
    }

    @Test
    public void dev (){
        Monom m = new Monom(1,0);
        Monom n = new Monom(4.6 , 2);
        Monom p = new Monom(3 , 6);

        Monom expected = new Monom(Monom.ZERO);
        if (!m.derivative().equals(expected)){
            fail();
        }
        expected = new Monom(9.2,1);
        if (!n.derivative().equals(expected)){
            fail();
        }
        expected = new Monom(18,5);
        if (!p.derivative().equals(expected)){
            fail();
        }
    }

    @Test
    public void f(){
        Monom m = new Monom(1,0);
        Monom n = new Monom(4.6 , 2);
        Monom p = new Monom(3 , 6);

        double x = 5;
        if (m.f(x) != 1.0){
            fail("f (x) funcyion is not good");
        }
        if (n.f(x) != 114.99999999999999){
            fail("f (x) funcyion is not good");
        }
        if (p.f(x) != 46875.0){
            fail("f (x) funcyion is not good");
        }

        x = 3.5;
        if (m.f(x) != 1.0){
            fail("f (x) funcyion is not good");
        }
        if (n.f(x) != 56.349999999999994){
            fail("f (x) funcyion is not good");
        }
        if (p.f(x) != 5514.796875){
            fail("f (x) funcyion is not good");
        }
    }

    @Test
    public void init(){

    }
}