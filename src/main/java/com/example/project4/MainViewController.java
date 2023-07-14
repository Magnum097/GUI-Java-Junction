package com.example.project4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for MainView.
 * Contain appendix stage, and methods for other views and controllers
 * @author Minseok Park, Amogh Sarangdhar
 */
public class MainViewController
{

    @FXML
    private Button button_orderCoffee;

    @FXML
    private Button button_orderDonuts;

    @FXML
    private Button button_storeOrder;

    @FXML
    private Button button_yourOrder;

    private Stage appendixStage;

    private StoreOrder storeOrder;

    private int numOfOrders = 0;

    private Order currentOrder;

    /**
     * Default constructor of MainViewController.
     */
    public MainViewController()
    {

    }

    /**
     * get number of orders which is same as Order number of currentOrder
     * @return Order number of currentOrder
     */
    public int getNumOfOrders()
    {
        return numOfOrders;
    }

    /**
     * Incrementor whenever currentOrder is processed and move to new order.
     */
    public void incrementNumOfOrders()
    {
        this.numOfOrders++;
    }

    /**
     * get reference of the currentOrder.
     * @return Order, currentOrder
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * get the StoreOrder for this program
     * @return StoreOrder.
     */
    public StoreOrder getStoreOrder()
    {
        return storeOrder;
    }

    /**
     * set an order to currentOrder
     * Used to depoint the currentOrder Object and repoint to new order object to move next order
     * @param currentOrder Order which is next order object
     */
    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * Add coffee object to currentOrder's itemList
     * @param newCoffee to be inserted into currentOrder.
     */
    public void addCoffee(Coffee newCoffee)
    {
        currentOrder.add(newCoffee);
    }

    /**
     * Add donut object to currentOrder's itemlist
     * @param newDonutList to be inserted into currentOrder.
     */
    public void addDonut(ArrayList<Donut> newDonutList)
    {
        for (int i = 0; i < newDonutList.size(); i++)
        {
            currentOrder.add(newDonutList.get(i));
        }
    }


    /**
     * Initialize certain references which are needed to be instanciated once the controller is created.
     * Create StoreOrder, currentOrder object.
     */
    @FXML
    public void initialize()
    {
        storeOrder = new StoreOrder();
        currentOrder = new Order();
    }

    /**
     * Go to Coffee UI
     * @param event of action
     */
    @FXML
    void goToCoffee(ActionEvent event)
    {
        try
        {
            if (appendixStage == null)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CoffeeView.fxml"));
                Scene coffeeView = new Scene(fxmlLoader.load(), 445, 464);
                appendixStage = new Stage();
                appendixStage.setTitle("Order Coffee");
                appendixStage.setScene(coffeeView);
                appendixStage.show();

                CoffeeController coffeeController = fxmlLoader.getController();
                coffeeController.setMainController(this);

                appendixStage.setOnCloseRequest(new EventHandler<WindowEvent>()
                {
                    @Override
                    public void handle(WindowEvent windowEvent)
                    {
                        appendixStage = null;
                    }
                });
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Go to Donut UI
     * @param event of action
     */
    @FXML
    void goToDonut(ActionEvent event)
    {
        try
        {
            if (appendixStage == null)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("DonutView.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 445, 464);
                appendixStage = new Stage();
                appendixStage.setTitle("Order Donut");
                appendixStage.setScene(scene);
                appendixStage.show();

                DonutController donutController = fxmlLoader.getController();
                donutController.setMainController(this);

                appendixStage.setOnCloseRequest(new EventHandler<WindowEvent>()
                {
                    @Override
                    public void handle(WindowEvent windowEvent)
                    {
                        appendixStage = null;
                    }
                });
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Go to Your Order UI
     * @param event of action
     */
    @FXML
    void goToYourOrder(ActionEvent event)
    {
        try
        {
            if (appendixStage == null)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OrderView.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 445, 464);
                appendixStage = new Stage();
                appendixStage.setTitle("Your Order");
                appendixStage.setScene(scene);
                appendixStage.show();

                OrderController orderController = fxmlLoader.getController();
                orderController.setMainController(this);
                appendixStage.setOnCloseRequest(new EventHandler<WindowEvent>()
                {
                    @Override
                    public void handle(WindowEvent windowEvent)
                    {
                        appendixStage = null;
                    }
                });
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Go to Store Order UI
     * @param event of action
     */
    @FXML
    void goToStoreOrder(ActionEvent event)
    {
        try
        {
            if (appendixStage == null)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("StoreOrderView.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 445, 464);
                appendixStage = new Stage();
                appendixStage.setTitle("Store Order");
                appendixStage.setScene(scene);
                appendixStage.show();

                StoreOrderController storeOrderController = fxmlLoader.getController();
                storeOrderController.setMainController(this);

                appendixStage.setOnCloseRequest(new EventHandler<WindowEvent>()
                {
                    @Override
                    public void handle(WindowEvent windowEvent)
                    {
                        appendixStage = null;
                    }
                });
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}