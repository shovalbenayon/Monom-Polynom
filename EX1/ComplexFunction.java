package Ex1;

import com.sun.deploy.security.SelectableSecurityManager;

import java.util.Stack;

public class ComplexFunction implements complex_function {
    private function left;
    private function right;
    private Operation op;

    /**
     * default constructor
     */
    public ComplexFunction() {
        this.left = null;
        this.right = null;
        this.op = Operation.None;
    }

    /**
     * constructor that received string op and to function and init the complex function
     * @param op - the operation to init
     * @param function_Left - the left function
     * @param function_Right - the right function
     */
    public ComplexFunction(String op, function function_Left, function function_Right) {
        op = op.toLowerCase();
        switch (op) {
            case "plus": {
                this.left = function_Left;
                this.right = function_Right;
                this.op = Operation.Plus;
                break;
            }
            case "mul": {
                this.left = function_Left;
                this.right = function_Right;
                this.op = Operation.Times;
                break;
            }
            case "div": {
                this.left = function_Left;
                this.right = function_Right;
                this.op = Operation.Divid;
                break;
            }
            case "max": {
                this.left = function_Left;
                this.right = function_Right;
                this.op = Operation.Max;
                break;
            }
            case "min": {
                this.left = function_Left;
                this.right = function_Right;
                this.op = Operation.Min;
                break;
            }
            case "comp": {
                this.left = function_Left;
                this.right = function_Right;
                this.op = Operation.Comp;
                break;
            }
            default:
                System.out.println("ERR : operation is invalid");
                break;

        }
    }

    /**
     * init complex function with only left function
     * @param function_Left - the received function
     */
    public ComplexFunction(function function_Left) {
        this.left=function_Left;
        this.right=null;
        this.op=Operation.None;
    }

    /**
     * Copy constructor
     * @param other The complex function object to copy
     */
    public ComplexFunction(ComplexFunction other) {
        this.op=other.getOp();
        this.left=other.left();
        this.right=other.right();
    }

    public ComplexFunction(Operation op, Polynom p1, Polynom p2) {
        this.op = op;
        this.left = new ComplexFunction(p1);
        this.right = new ComplexFunction(p2);
    }

    /**
     * init the complex function with plus operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function which will be added to this complex_function.
     */
    public void plus(function f1) {
        if (this.getOp() == Operation.Error)
            throw new RuntimeException("ERR: can't resolve error operation");
        /**
         * if there isn't operation it means that we have only left function and then the new function will
         * get in the right side
         */
        if(this.getOp() == Operation.None) {
                this.right = f1;
                this.op = Operation.Plus;
        }
        /**
         * if the operation isn't error or none the algorithm will build new left function with the
         * existing function and adding the new one to the right
         */
        else{
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Plus;
        }

    }

    /**
     * * init the complex function with mulitply operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function which will be multiply be this complex_function.
     */
    public void mul(function f1) {
        if (this.getOp() == Operation.Error)
            throw new RuntimeException("ERR: can't resolve error operation");
        /**
         * if there isn't operation it means that we have only left function and then the new function will
         * get in the right side
         */
        if(this.getOp() == Operation.None) {
            this.right = f1;
            this.op = Operation.Times;
        }
        /**
         * if the operation isn't error or none the algorithm will build new left function with the
         * existing function and adding the new one to the right
         */
        else{
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Times;
        }

    }
    /**
     * * init the complex function with divide operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function which will be divide be this complex_function.
     */
    public void div(function f1) {
        if (this.getOp() == Operation.Error)
            throw new RuntimeException("ERR: can't resolve error operation");
        /**
         * if there isn't operation it means that we have only left function and then the new function will
         * get in the right side
         */
        if(this.getOp() == Operation.None) {
            this.right = f1;
            this.op = Operation.Divid;
        }
        /**
         * if the operation isn't error or none the algorithm will build new left function with the
         * existing function and adding the new one to the right
         */
        else{
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Divid;
        }
    }

    /**
     * init the complex function with max operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
     */
    public void max(function f1) {
        if (this.getOp() == Operation.Error)
            throw new RuntimeException("ERR: can't resolve error operation");
        /**
         * if there isn't operation it means that we have only left function and then the new function will
         * get in the right side
         */
        if(this.getOp() == Operation.None) {
            this.right = f1;
            this.op = Operation.Max;
        }
        /**
         * if the operation isn't error or none the algorithm will build new left function with the
         * existing function and adding the new one to the right
         */
        else{
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Max;
        }
    }

    /**
     * init the complex function with min operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
     */
    public void min(function f1) {
        if (this.getOp() == Operation.Error)
            throw new RuntimeException("ERR: can't resolve error operation");
        /**
         * if there isn't operation it means that we have only left function and then the new function will
         * get in the right side
         */
        if(this.getOp() == Operation.None) {
            this.right = f1;
            this.op = Operation.Min;
        }
        /**
         * if the operation isn't error or none the algorithm will build new left function with the
         * existing function and adding the new one to the right
         */
        else{
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Min;
        }
    }

    /**
     * init the complex function with comp operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function
     */
    public void comp(function f1) {
        if (this.getOp() == Operation.Error)
            throw new RuntimeException("ERR: can't resolve error operation");
        /**
         * if there isn't operation it means that we have only left function and then the new function will
         * get in the right side
         */
        if(this.getOp() == Operation.None) {
            this.right = f1;
            this.op = Operation.Comp;
        }
        /**
         * if the operation isn't error or none the algorithm will build new left function with the
         * existing function and adding the new one to the right
         */
        else{
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Comp;
        }
    }

    /** returns the left side of the complex function - this side should always exists (should NOT be null).
     * @return a function representing the left side of this complex funcation
     */
    public function left() { return this.left; }

    /** returns the right side of the complex function - this side might not exists (aka equals null).
     * @return a function representing the left side of this complex funcation
     */
    public function right() { return this.right; }

    /**
     * The complex_function oparation: plus, mul, div, max, min, comp
     * @return operation of this complex function
     */
    public Operation getOp() { return this.op; }

    /**
     * get the string of this complex function
     * @return the string of the complex function
     */
    public String toString(){
        String op_string = "";
        switch (this.op) {
            case Max:
                op_string = "max";
                break;
            case Min:
                op_string = "min";
                break;
            case Comp:
                op_string = "comp";
                break;
            case Plus:
                op_string = "plus";
                break;
            case Divid:
                op_string = "div";
                break;
            case Times:
                op_string = "mul";
                break;
            case None:
                op_string = "";
                break;
            default:
                op_string = "error";
                break;
        }
        return op_string+'('+this.left().toString()+','+this.right().toString()+')';
    }


    /**
     * comp the value of f(x) with the received operation
     * @param x - the double value
     * @return the f(x) of the complex function
     */
    public double f(double x) {
            Operation op = this.getOp();
            switch (op) {
                case Plus :
                    return this.left().f(x) + this.right().f(x);
                case Times :
                    return this.left().f(x) * this.right().f(x);
                case Divid :
                    return this.left().f(x) / this.right().f(x);
                case Min :
                    return Math.min(this.left().f(x), this.right().f(x));
                case Max :
                    return Math.max(this.left().f(x), this.right().f(x));
                case Comp :
                    return this.left().f(this.right().f(x));
                case Error :
                    throw new RuntimeException("ERR: not a valid operator");
                default :
                    return 0;
            }
    }
    /**
     * This static function returns the index of main operation (over *,-,+),
     * in case of an invalid from returns -1;
     * taken from boaz github
     * @param s: represents a form
     * @return the index of the main oparatiopn (-1 if none).
     */
    public static int main_co(String s) {
        int ans = -1;
        int c = 0;
        for(int i = 0; i < s.length() ; i++) {
            char ch = s.charAt(i);
            if(ch == '(') {c++;}
            if(ch == ')') {c--;}
            if(ch == ',' && c == 0) {
                ans = i;
            }
        }
        return ans;
    }

    /**
     *init a complex function from string
     * the function works recursive and simplify the string until we get polynom and then
     * the algorithm going up and gives as the whole function
     * @param s - the receiving string
     * @return function that represent the string
     */
    public function initFromString(String s) {
        s = s.replaceAll("\\s", "");
        function ans = null;
        int find = s.indexOf('(');
        String op = s.substring(0 , find + 1);
        if (find >= 0 && !op.isEmpty()){
            int comma_at = main_co(s.substring(op.length() , s.length() -1));
            function leftf = initFromString(s.substring(op.length() , comma_at + op.length()));
            function rightf = initFromString(s.substring(op.length() + comma_at + 1 , s.length()-1));
            op = s.substring(0 , op.length() - 1);
            ans = new ComplexFunction(op , leftf , rightf);
        }
        else{
            function temp = new Polynom();
            ans = temp.initFromString(s);
        }
        return ans;
    }

    /**
     * copy the complex function to a new one
     * @return - new complex function
     */
    public function copy() {
        if(this instanceof ComplexFunction)
        {
            function new_func=new ComplexFunction(this);
            return new_func;
        }
        else
        {
            function new_func=this.copy();
            return new_func;
        }
    }
    public boolean equals(Object obj) {
        double x = 0.0;
        if (obj instanceof function) {
            while (x < 100) {
                if (this.f(x) != ((function) obj).f(x))
                    return false;
                x += 0.001;
            }
        } else {
            throw new RuntimeException("ERR: Not a function type");
        }
        return true;
    }
}
