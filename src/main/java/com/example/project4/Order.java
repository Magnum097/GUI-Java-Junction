package com.example.project4;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * DB class of single Order with methods to handle with Coffee and Donut.
 * @author Minseok Park, Amogh Sarangdhar
 */
public class Order implements Customizable
{
    public static final double NJ_SALES_TAXRATE = 0.06625;

    private int orderNumber;
    private ArrayList<MenuItem> itemList = new ArrayList<MenuItem>();

    private ArrayList<Coffee> coffeeOrders = new ArrayList<Coffee>();
    private ArrayList<Donut> donutOrders = new ArrayList<Donut>();


    /**
     * Getter for Number of Orders
     * @return int - number of orders
     */
    public int getNumOfOrders()
    {
        return itemList.size();
    }


    /**
     * Getter for Order Number
     * @return int - unique number for each order that is placed.
     */
    public int getOrderNumber()
    {
        return orderNumber;
    }

    /**
     * Setter for OrderNumber
     * @param orderNumber order number.
     */
    public void setOrderNumber(int orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    /**
     * Subtotal for Orders
     * @return double - subtotal of the orders added to the cart
     */
    public double getsubTotal()
    {
        double result = 0.00;
        for (int i = 0; i < itemList.size(); i++)
        {
            result = result + itemList.get(i).itemPrice();
        }
        return result;
    }

    /**
     * Getter for TAXed expense only of the order.
     * @return double, taxed part of the total price
     */
    public double getTax()
    {
        return getsubTotal() * NJ_SALES_TAXRATE;
    }

    /**
     * Getter for actual Grand Total of the order.
     * @return double, Grand total of the Order
     */
    public double getTotalPrice()
    {
        return getsubTotal() + getTax();
    }

    /**
     * Getter for ArrayList reference of itemList of the order
     * @return ArrayList<MenuItem>, itemlist of the order.
     */
    public ArrayList<MenuItem> getItemList()
    {
        return itemList;
    }

    /**
     * Getter for ArrayList reference of DonutList of the Order
     * @return ArrayList<Donut>, Donut Orders of the order
     */
    public ArrayList<Donut> getDonutOrders()
    {
        return donutOrders;
    }

    /**
     * Getter for ArrayList reference of CoffeeList of the Order
     * @return ArrayList<Coffee>, Coffee orders of the order
     */
    public ArrayList<Coffee> getCoffeeOrders()
    {
        return coffeeOrders;
    }


    /**
     * Adding/Placing an Order
     * @param obj Object
     * @return true, if order placed, false, otherwise.
     */
    @Override
    public boolean add(Object obj)
    {
        if (itemList.contains(obj) == false)
        {
            itemList.add((MenuItem) obj);
            if (obj instanceof Coffee)
            {
                coffeeOrders.add((Coffee) obj);
            }
            else
            {
                donutOrders.add((Donut) obj);
            }
        }
        else
        {
            MenuItem target = itemList.get(itemList.indexOf((MenuItem) obj));
            target.addNumOfItems(((MenuItem) obj).getNumOfItems());
        }
        return true;
    }

    /**
     * Remove an Order that is added to the cart
     * @param obj Object
     * @return true, if order is removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) //no matter the number of items of obj, just delete the order.
    {
        if (itemList.contains((MenuItem) obj))
        {
            itemList.remove(obj);
            if (obj instanceof Coffee)
            {
                coffeeOrders.remove((Coffee) obj);
            }
            else if (obj instanceof Donut)
            {
                donutOrders.remove((Donut) obj);
            }
            else
            {
                //error case throw exception.
                throw new NoSuchElementException("Debug! only Coffee/Donut can be parameter for Order.remove");
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Get all items in the order in a single String
     * @return String contains all items in the order.
     */
    @Override
    public String toString()
    {
        return itemList.toString();
    }

    /**
     * Getter for ArrayList of all items of the order in String
     * Helper method to printing, searching, and debugging.
     * @return ArrayList<String> all items of the order in String.
     */
    public ArrayList<String> getStringArrayOfItems()
    {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < itemList.size(); i++)
        {
            result.add(itemList.get(i).toString());
        }
        return result;
    }
}
