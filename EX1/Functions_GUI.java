package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {

    ArrayList<function> function_gui = new ArrayList<>();
    @Override
    public void initFromFile(String file) throws IOException {

    }

    @Override
    public void saveToFile(String file) throws IOException {

    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

    }

    @Override
    public void drawFunctions(String json_file) {

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
