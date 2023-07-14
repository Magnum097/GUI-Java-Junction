package com.example.project4;

import java.util.ArrayList;

/**
 * Store Order which contain all DB of a program and methods to handle Order objects.
 * @author Minseok Park, Amogh Sarangdhar
 */
public class StoreOrder implements Customizable{

    private ArrayList<Order> orderLists = new ArrayList<Order>();

    /**
     * Get String ArrayList to export all data into a file.
     * @return ArrayList<String> which contains all data lines which is converted to write a file.
     */
    public ArrayList<String> getStoreOrderExport()
    {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < orderLists.size(); i++)
        {
            Order currentOrder = orderLists.get(i);
            result.add("Order No. : " + currentOrder.getOrderNumber() + "\n");
            result.add("Subtotal: " + currentOrder.getsubTotal() + "\n");
            result.add("Tax: " + currentOrder.getTax() + "\n");
            result.add("TotalPrice: " + currentOrder.getTotalPrice() + "\n\n");
            result.add("Order Detail Below: " + "\n\n");

            ArrayList<MenuItem> selectedItemList = currentOrder.getItemList();

            for (int k = 0; k < selectedItemList.size(); k++)
            {
                result.add(selectedItemList.get(k).toString() + "\n");
            }
            result.add("------------End of OrderList------------- \n\n");
        }
        return result;
    }

    /**
     * Get Orderlists of the StoreOrder
     * @return ArrayList<Order>, All orders in this program.
     */
    public ArrayList<Order> getOrderLists()
    {
        return orderLists;
    }

    /**
     *  Get actual number of Orders in DB
     * @return int actual number of orders in DB
     */
    public int getNumOfOrders()
    {
        return orderLists.size();
    }

    /**
     * Add an order to Orderlist of the StoreOrder
     * @param obj Object, newly placed order.
     * @return
     */
    @Override
    public boolean add(Object obj)
    {
        orderLists.add((Order) obj);
        return true;
    }

    /**
     * Remove target Order object from Orderlist of the StoreOrder
     * @param obj Object selected Order Object.
     * @return
     */
    @Override
    public boolean remove(Object obj)
    {
        if (orderLists.remove((Order) obj) == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Readability enhancer method which is equal to orderList.toString.
     * @return orderLists.toString();
     */
    @Override
    public String toString()
    {
        return orderLists.toString();
    }
}
