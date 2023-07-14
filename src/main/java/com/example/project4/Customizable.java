package com.example.project4;

/**
 * Interface class to have general methods for StoreOrder, Order, Coffee, and Donut.
 * @author Amogh Sarangdhar, Minseok Park
 */
public interface Customizable {

    /**
     * Add Items in the order
     * @param obj Object
     * @return true, if successfully added, false otherwise.
     */
    boolean add(Object obj);

    /**
     * Remove Items from the order
     * @param obj Object
     * @return true, if successfully removed, false otherwise.
     */
    boolean remove(Object obj);
}
