package Ex1;

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
        this.op=Operation.None;
        this.right=null;
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

    /**
     * init the complex function with plus operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function which will be added to this complex_function.
     */
    public void plus(function f1) {
            if(this.getOp() == Operation.None) {
                this.right = f1;
                this.op = Operation.Plus;
            }
            if(this.getOp() != Operation.Error){
                this.left = new ComplexFunction(this);
                this.right = f1;
                this.op = Operation.Plus;
            }
            else{
                throw new RuntimeException("ERR: operation is error");
            }
    }

    /**
     * * init the complex function with mulitply operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function which will be multiply be this complex_function.
     */
    public void mul(function f1) {
        if(this.getOp() == Operation.None) {
            this.right = f1;
            this.op = Operation.Times;
        }
        if(this.getOp() != Operation.Error){
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Times;
        }
        else{
            throw new RuntimeException("ERR: operation is error");
        }

    }
    /**
     * * init the complex function with divide operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function which will be divide be this complex_function.
     */
    public void div(function f1) {
        if(this.getOp() == Operation.None) {
            this.right = f1;
            this.op = Operation.Divid;
        }
        if(this.getOp() != Operation.Error){
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Divid;
        }
        else{
            throw new RuntimeException("ERR: operation is error");
        }
    }

    /**
     * init the complex function with max operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
     */
    public void max(function f1) {
        if(this.getOp() == Operation.None) {
            this.right = f1;
            this.op = Operation.Max;
        }
        if(this.getOp() != Operation.Error){
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Max;
        }
        else{
            throw new RuntimeException("ERR: operation is error");
        }

    }

    /**
     * init the complex function with min operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
     */
    public void min(function f1) {
        if(this.getOp() == Operation.None) {
            this.right = f1;
            this.op = Operation.Min;
        }
        if(this.getOp() != Operation.Error){
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Min;
        }
        else{
            throw new RuntimeException("ERR: operation is error");
        }
    }

    /**
     * init the complex function with comp operation. if there isn't a right function we wiil add to the right
     * the new function. if there is , a new complex function will be create , that the left side is the original
     * function and the new one is on the right side.
     * @param f1 the complex_function
     */
    public void comp(function f1) {
        if(this.getOp() == Operation.None) {
            this.right = f1;
            this.op = Operation.Comp;
        }
        if(this.getOp() != Operation.Error){
            this.left = new ComplexFunction(this);
            this.right = f1;
            this.op = Operation.Comp;
        }
        else{
            throw new RuntimeException("ERR: operation is error");
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
     *
     * @param s
     * @return
     */
    public function initFromString(String s) {
        if (!IsBalanced(s)){
            throw new RuntimeException("ERR: can't build complex function, parentheses is not balanced");
        }
        StringBuilder op = new StringBuilder();
        for (int i = 0; i < s.length() ; i++) {

        }
    }

    public boolean isMatchingPair(char character1, char character2)
    {
        if (character1 == '(' && character2 == ')')
            return true;
        else
            return false;
    }

    public boolean IsBalanced(String s) {
        Stack st=new Stack();
        for(int i=0;i<s.length();i++) {
            if (s.charAt(i) == '(')
                st.push(s.charAt(i));

            if (s.charAt(i) == ')' ) {
                if (st.isEmpty()) {
                    return false;
                }

                else if ( !isMatchingPair((Character) st.pop(), s.charAt(i)) ) {
                    return false;
                }
            }
        }

        if (st.isEmpty())
            return true;
        else {
            return false;
        }
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
}
