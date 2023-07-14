package com.example.project4;

import java.text.DecimalFormat;
import java.util.NoSuchElementException;

/**
 * This class is for Ordering Donut
 * @author Amogh Sarangdhar, Minseok Park
 */
public class Donut extends MenuItem implements Customizable
{

    private static final double PRICE_YEAST_DONUT = 1.59;
    private static final double PRICE_CAKE_DONUT = 1.79;
    private static final double PRICE_DONUT_HOLE = 0.39;

    public static final int YEAST_DONUT = 0;
    public static final int CAKE_DONUT = 1;
    public static final int DONUT_HOLE = 2;

    private int typeOfDonut = -1;
    private Donut_flavor flavor;


    /**
     * Default constructor for Donut class
     */
    public Donut()
    {

    }

    /**
     * Parametrized constructor for Donut class
     * @param typeOfDonut type of Donut
     * @param flavor donut flavor
     * @param numOfItems num of donut items
     */
    public Donut(int typeOfDonut, Donut_flavor flavor, int numOfItems)
    {
        this.typeOfDonut = typeOfDonut;
        this.flavor = flavor;
        this.numOfItems = numOfItems;
    }


    /**
     * Getter for donut flavors as a string
     * @return flavor as a String
     */
    public String getFlavor_String()
    {
        if (flavor != null)
        {
            return flavor.name();
        }
        else
        {
            return "no flavor";
        }
    }

    /**
     * Type of donut as a String
     * @return String representation of the type of donut
     */
    public String getTypeOfDonut_String()
    {
        if (typeOfDonut == YEAST_DONUT)
        {
            return "Yeast";
        }
        else if (typeOfDonut == CAKE_DONUT)
        {
            return "Cake";
        }
        if (typeOfDonut == DONUT_HOLE)
        {
            return "Donut hole";
        }
        else
        {
            throw new NoSuchElementException("Debug! typeOfDonut must be initialized before initiating");
            //error case. throw error.
        }
    }

    /**
     * Equals method to check if the two donuts are equal
     * @param comparative Donut as an object
     * @return true, if donuts are equal, false otherwise.
     */
    @Override
    public boolean equals(Object comparative)  //required to have "equals" method to properly use Arraylist.contains
    {
        if(comparative instanceof Donut == false)
        {
            return false;
        }
        if (((Donut)comparative).typeOfDonut == this.typeOfDonut && this.flavor == (((Donut)comparative).flavor))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    /**
     * Return the Donut object into a String, in format of "NUM FLAVOR TYPE"
     * Implemented to utilize ArrayList's function properly.
     * @return
     */
    @Override
    public String toString()
    {
        return getNumOfItems() + " " + getFlavor_String() + " " + getTypeOfDonut_String();
    }

    /**
     * Item price for Donuts
     * @return double - itemprice of Donuts
     */
    @Override
    public double itemPrice()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        if (typeOfDonut == -1)
        {
            return Double.parseDouble(df.format(-1.00));
        }
        else if (typeOfDonut == YEAST_DONUT)
        {
            return Double.parseDouble(df.format(PRICE_YEAST_DONUT * numOfItems));
        }
        else if (typeOfDonut == CAKE_DONUT)
        {
            return Double.parseDouble(df.format(PRICE_CAKE_DONUT * numOfItems));
        }
        else if (typeOfDonut == DONUT_HOLE)
        {
            return Double.parseDouble(df.format(PRICE_DONUT_HOLE * numOfItems));
        }
        else
        {
            throw new NoSuchElementException("Debug! the typeOfDonut is not in the range or -1~2 to process Donut.itemPrice");
        }

    }

    /**
     * Add number of Donut items
     * @param num number of donut items
     */
    public void addNum(int num)
    {
        numOfItems = numOfItems + num;
    }


    /**
     * Add/Set Donut flavors for the donut object.
     * @param obj Donut as an object
     * @return true, if flavor is added, false otherwise
     */
    @Override
    public boolean add(Object obj)
    {
        return false;
    }

    /**
     * Remove the Donut flavors for the donut object
     * @param obj target Donut flavor to delete.
     * @return
     */
    @Override
    public boolean remove(Object obj)
    {
        return false;
    }
}
