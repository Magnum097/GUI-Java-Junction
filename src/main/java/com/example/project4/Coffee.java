package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class is for ordering Coffee
 * @author Amogh Sarangdhar, Minseok Park
 */
public class Coffee extends MenuItem implements Customizable
{
    private static final double BASE_PRICE = 1.69;
    private static final double COST_SURCHARGE = 0.4;

    public static final int SIZE_SHORT = 0;
    public static final int SIZE_TALL = 1;
    public static final int SIZE_GRANDE = 2;
    public static final int SIZE_VENTI = 3;

    private ArrayList<Coffee_AddIn> addIns = new ArrayList<Coffee_AddIn>();  //arraylist for coffee Addins


    private int typeOfSize = -1;
    private int numOfAddIns = 0;

    /**
     * Default constructor for Coffee Class
     */
    public Coffee(){

    }

    /**
     * Parametrized constructor for Coffee class
     * @param typeOfSize size of coffee
     */
    public Coffee(int typeOfSize)
    {
        this.typeOfSize = typeOfSize;
        this.numOfItems = 1;
    }

    /**
     * Parametrized constructor for Coffee class
     * @param copy an instance of Coffee
     */
    public Coffee(Coffee copy)
    {
        this.typeOfSize = copy.typeOfSize;
        this.addIns = copy.addIns;
        this.numOfAddIns = copy.numOfAddIns;
        this.numOfItems = copy.numOfItems;
    }

    /**
     * Parametrized constructor for Coffee class
     * @param typeOfSize size of coffee
     * @param addIns AddIns of coffee
     * @param numOfAddIns number of coffee AddIns
     */
    public Coffee(int typeOfSize, ArrayList<Coffee_AddIn> addIns, int numOfAddIns)
    {
        this.typeOfSize = typeOfSize;
        this.addIns = addIns;
        this.numOfAddIns = numOfAddIns;
    }


    /**
     * Setter for type of coffee size
     * @param typeOfSize size of coffee (short = 0, tall = 1, grande = 2, venti = 3)
     */
    public void setTypeOfSize(int typeOfSize) {
        this.typeOfSize = typeOfSize;
    }

    /**
     * Getter for string representation of all coffee AddIns
     * @return String - coffee AddIns as a String
     */
    private String getAllAddOns_String() //seems can be reused with inheritance + generic
    {
        String result = "";
        for (int i = 0; i < addIns.size(); i++)
        {
            result = result + addIns.get(i).name();
            if (i < addIns.size() - 1)
            {
                result = result + ", ";
            }
        }
        return result;
    }

    /**
     * Getter for string representation of size of coffee
     * @return String - size of coffee
     */
    public String getTypeOfSize_String()  //seems can be reused with inheritance
    {
        if (typeOfSize == SIZE_SHORT)
        {
            return "Short";
        }
        else if (typeOfSize == SIZE_TALL)
        {
            return "Tall";
        }
        else if (typeOfSize == SIZE_VENTI)
        {
            return "Venti";
        }
        else if (typeOfSize == SIZE_GRANDE)
        {
            return "Grande";
        }
        else
        {
            throw new NoSuchElementException("Debug! typeOfSize must be initialized before initiate Coffee.getTypeOfSize_String");
            //error case;
        }
    }

    /**
     * Changing size of coffee
     * @param newSize size of coffee
     * @return true, if size changed, false, otherwise.
     */
    public boolean setSize_int(int newSize)
    {
        if (newSize == typeOfSize)
        {
            return false;
        }
        else
        {
            this.typeOfSize = newSize;
            return true;
        }
    }

    /**
     * Changing size of coffee to an integer
     * @param size size of coffee
     * @return size of coffee (short = 0, tall = 1, grande = 2, venti = 3)
     */
    private int size_toInt(String size)
    {
        if (size.equals("Short"))
        {
            return SIZE_SHORT;
        }
        else if (size.equals("Tall"))
        {
            return SIZE_TALL;
        }
        else if (size.equals("Grande"))
        {
            return SIZE_GRANDE;
        }
        else if (size.equals("Venti"))
        {
            return SIZE_VENTI;
        }
        else //error case;
        {
            throw new NoSuchElementException("Debug! inputted String, size, (parameter) is not one of {Short, Tall, Grande, Venti} to initiate Coffee.size_toInt.");
        }
    }

    /**
     * Set size of Coffee as a string
     * @param newSize new size of coffee
     * @return true if is size changed, false otherwise.
     */
    public boolean setSize_String(String newSize)
    {
        int sizeInt = size_toInt(newSize);
        return (setSize_int(sizeInt));
    }

    /**
     * Compare two coffee with each other
     * @param comparative object of type coffee
     * @return true if coffee is same, false otherwise.
     */
    @Override
    public boolean equals(Object comparative)
    {
        if(comparative instanceof Coffee == false)
        {
            return false;
        }
        if (((Coffee)comparative).typeOfSize == this.typeOfSize && hasSameAddins(((Coffee)comparative).addIns))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Checks if the Coffee has same Addins
     * @param comparative ArrayList of coffee Addins.
     * @return true, if has same Addins, false otherwise.
     */
    private boolean hasSameAddins(ArrayList<Coffee_AddIn> comparative) {
        if (addIns.size() != comparative.size())
        {
            return false;
        }
        else
        {
            for (Coffee_AddIn addin : comparative)
            {
                if (addIns.contains(addin) == false)
                {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * To String to print the Coffee Order
     * @return String representation of Coffee ordered
     */
    @Override
    public String toString()
    {
        return getNumOfItems() + " " + getTypeOfSize_String() + " Coffee, add-Ins: [" + getAllAddOns_String() + "]";
    }


    /**
     * Item price of Coffee order
     * @return double - price of coffee
     */
    @Override
    public double itemPrice()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        double addins = 0.3 * addIns.size();
        unitPrice = BASE_PRICE + (COST_SURCHARGE * typeOfSize);
        String itemPrice = df.format( (unitPrice + addins) * numOfItems);
        return Double.parseDouble(itemPrice);
    }


    /**
     * Adding addins to Coffee
     * @param obj AddIns as an object
     * @return true, if AddIn added successfully, false otherwise.
     */
    @Override
    public boolean add(Object obj)
    {
        Coffee_AddIn target = (Coffee_AddIn)obj;
        if (addIns.contains(target))
        {
            return false;
        }
        else
        {
            addIns.add(target);
            return true;
        }
    }

    /**
     * Removing addins from Coffee
     * @param obj AddIns as an object
     * @return true, if AddIn removed successfully, false otherwise.
     */
    @Override
    public boolean remove(Object obj) //for add-in; can be use in both "first initialize" & "adjustment"
    {
        Coffee_AddIn target = (Coffee_AddIn)obj;
        if (addIns.contains(target))
        {
            addIns.remove(target);
            return true;
        }
        else
        {
            return false;
        }
    }

}
