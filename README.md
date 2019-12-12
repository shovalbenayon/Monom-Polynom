# objectoriented
This project contains 4 main classes, that every class represent a different object. This project is continue of Ex0.

The classes in the Project -
Function_Gui- This class contains a collection of functions that's Drawing all the functions in it by parameters. this class can save and write from file and Json files.
ComplexFunction- This class represents an object with 2 function and operation between them. Every Complex Function have function left, function right and one of this operations : Plus, Times, Divid, Max, Min, Comp , None, Error.
Polynom-this class represents a polynom object.
Monom- this class represents a monom object.

Function_GUI:
This class is Drawing the graphs from the collection. This class have the methods : initFromFile(String file)  , saveToFile(String file) , drawFunctions(int width, int height, Range rx, Range ry, int resolution) , drawFunctions(String json_file). and have the methods of ArrayList.

ComplexFunction:

This class is a Format of : operation(function left, function right).
This class have the Method of : ComplexFunction (String op, function left , function right), ComplexFunction (String s), ComplexFunction (function left), f(double x), initFromString(String s), copy, plus(function f1), mul(function f1), div(function f1), max(function f1), min(function f1), comp(function f1), left, right, getOp, equals(Object other), toString().
Attention: In the method equals we had a problem that we didn't know how to write the function so the function is working by computing the f(x) values of every function and see if it's equals.
For example : f(x) = mul(x , x) and f(x) = none(x^2 , null) should be equals and this is why the function computing f(x).

Monom:

This class represents a simple Monom as "ax^b" where a is an int and b is double.For any other shape, the class is throwing runtime exception. This class have the methods :  constructor , get_power, get_coefficient, derivative, f , isZero, Monom, add, multiply, toString , equals.

good form : "3.0x^7"
bad form : "4+2", "-1x^5.0"

Polynom:

The class represents an ArrayList of Monom and implements the interface Polynom_able. The class have the Methods :  add(Polynom_able p1) , add(Monom m1) , substract(Polynom_able p1) , multiply(Monom m1) , multiply(Polynom_able p1) , equals (Object p1), isZero() , derivative() ,  root(double x0, double x1, double eps) , area(double x0, double x1, double eps) , f(double x) , iteretor() , and have all the methods of ArrayList.

the Good Form is "ax^b+cx^d...". for any other format the class is throwing runtime exception.