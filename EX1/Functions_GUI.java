package Ex1;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Functions_GUI implements functions {
    ArrayList<function> function_gui = new ArrayList<>();

    /**
     * init the fuction_gui Collection from the file
     * @param file - the file name
     * @throws IOException if the file do not exist
     */
    public void initFromFile(String file) throws IOException {
        FileReader myFile = new FileReader(file);
        BufferedReader myBuffer = new BufferedReader(myFile);
        String line_reader = myBuffer.readLine();

        while (line_reader != null ) {
            line_reader = line_reader.replaceAll("\\s", ""); // remove all spaces
            ComplexFunction cf = new ComplexFunction();
            function f = cf.initFromString(line_reader);
            function_gui.add(f);
            line_reader = myBuffer.readLine();

        }
        myBuffer.close();
        myFile.close();
    }

    /**
     * save to file
     * @param file - the file name
     * @throws IOException
     */
    public void saveToFile(String file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        Iterator<function> iter = iterator();
        while (iter.hasNext()){
            function f = iter.next();
            writer.write(f + "\n");
        }
        writer.close();
    }

    /**
     * Drawing the function from received values
     * @param width - the width of the window - in pixels
     * @param height - the height of the window - in pixels
     * @param rx - the range of the horizontal axis
     * @param ry - the range of the vertical axis
     * @param resolution - the number of samples with in rx: the X_step = rx/resulution
     */
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        StdDraw.setCanvasSize(width, height);
        // rescale the coordinate system
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());

        // vertical lines
        StdDraw.setPenColor(Color.BLACK);
        for (double i = ry.get_min(); i <= ry.get_max(); i++) {
            StdDraw.line(rx.get_min(), i, rx.get_max(), i);
        }
        // horizontal  lines
        for (double i = rx.get_min(); i <= rx.get_max(); i++) {
            StdDraw.line(i, ry.get_min(), i, ry.get_max());
        }

        StdDraw.setPenRadius(0.005);
        StdDraw.line(rx.get_min(),0, rx.get_max(), 0); // draw the x axis
        StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
        for (double i = rx.get_min(); i <= rx.get_max(); i++) { // Writes the x values​in 1-point differentials
            StdDraw.text(i,-0.4, Double.toString(i)); //Write down the values ​​below the axis
        }

        StdDraw.line(0, ry.get_min(), 0, ry.get_max()); //draw the y axis
        for (double i = ry.get_min(); i <= ry.get_max(); i++) {// Writes the y values​in 1-point differentials
            StdDraw.text(-0.4, i, Double.toString(i));//Write down the values below the y axis
        }
        Iterator<function> iter = iterator();
        // plot the approximation to the function
        for (function function : function_gui) {
            double step = ( ( Math.abs(rx.get_min()) ) + ( Math.abs(rx.get_max()) )) / resolution; // resolution drawing
            //random color
            int Red = (int) (Math.random()*256);
            int Green = (int) (Math.random()*256);
            int Blue = (int) (Math.random()*256);
            Color color_line = new Color (Red , Green , Blue);

            StdDraw.setPenColor(color_line);
            for (double i = rx.get_min(); i < rx.get_max(); i+=step) {
                StdDraw.line(i, function.f(i), i+step, function.f(i+step));
            }
            // print the color values and the function
            ComplexFunction cf = new ComplexFunction() ;
            function f = cf.initFromString(iter.next().toString());
            System.out.println(color_line.toString() + "\t" + "f(x) = " + f.toString());
        }
    }

    /**
     * Drawing the function from json file
     * @param json_file - the file with all the parameters for the GUI window.
     */
    public void drawFunctions(String json_file) {
       JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(json_file));
            JSONObject jsonObject = (JSONObject) obj;

            int width = Math.toIntExact((long) jsonObject.get("Width")) ;
            int height =  Math.toIntExact((long) jsonObject.get("Height")) ;
            int reso =  Math.toIntExact((long) jsonObject.get("Resolution")) ;

            JSONArray range_x = (JSONArray) jsonObject.get("Range_X");
            Iterator<Long> iter_x = range_x.iterator();
           double min = iter_x.next().doubleValue();
           double max = iter_x.next().doubleValue();
           Range x_r = new Range(min ,max);

           JSONArray range_y = (JSONArray) jsonObject.get("Range_Y");
            Iterator<Long> iter_y = range_y.iterator();
            min = iter_y.next().doubleValue();
            max = iter_y.next().doubleValue();
            Range y_r = new Range(min ,max);
            drawFunctions(width , height , x_r , y_r , reso);


        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
            Range rx = new Range(-10,10);
            Range ry = new Range(-10,10);
            drawFunctions(1000,600,rx,ry,200);
        }
    }

    /**
     * size function
     * @return the size of this Arraylist
     */
    public int size() {
        return function_gui.size();
    }

    /**
     * Check if this ArrayList is Empty
     * @return true, if the ArrayList is Empty, false otherwise
     */
    public boolean isEmpty() {
        if (function_gui.size() == 0)
            return false;
        return false;
    }

    /**
     * check if the object exist in the Array List
     * @param o - the receiving object
     * @return true if the object exist, false otherwise.
     */
    public boolean contains(Object o) {
        if (!(o instanceof function))
            throw new RuntimeException("ERR: not a function type");
        else{
            return function_gui.contains(o);
        }
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return iterator
     */
    public Iterator<function> iterator() {
        Iterator<function> iter = function_gui.iterator();
        return iter;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * @return Array
     */
    public Object[] toArray() {
        return function_gui.toArray();
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element)
     * the runtime type of the returned array is that of the specified array.
     * @param a Runtime type
     * @param <T>
     * @return Runtime of the array
     */
    public <T> T[] toArray(T[] a) {
        return function_gui.toArray(a);
    }

    /**
     * Add the function to the ArrayList
     * @param function to Add
     * @return Boolean if the function added
     */
    public boolean add(function function) {
        return function_gui.add(function);
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param o the object to remove
     * @return true if the object exist and removed
     */
    public boolean remove(Object o) {
        return  function_gui.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return function_gui.containsAll(c);
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list,
     * in the order that they are returned by the specified collection's Iterator.
     * @param c if the Collection is all added
     */
    public boolean addAll(Collection<? extends function> c) {
        return function_gui.addAll(c);
    }

    /**
     * Removes from this list all of its elements that are contained in the specified collection.
     * @param c the collection to remove
     * @return if all the Collection is removed
     */
    public boolean removeAll(Collection<?> c) {
        return function_gui.removeAll(c);
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     * @param c the Collection to retain
     * @return if all the Collection is retain
     */
    public boolean retainAll(Collection<?> c) {
        return function_gui.retainAll(c);
    }

    /**
     * clear the array list function_gui
     */
    public void clear() {
        function_gui.clear();
    }

}
