package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Controller class to execute Order related functions
 * @author Minseok Park, Amogh Sarangdhar
 */
public class OrderController {


    @FXML
    private Button button_RemoveSelectedItem;

    @FXML
    private Button button_placeOrder;

    @FXML
    private ListView<String> listView_itemList;
    private ObservableList<String> obs_itemList = FXCollections.observableArrayList();

    @FXML
    private TextField tField_saleTax;

    @FXML
    private TextField tField_subTotal;

    @FXML
    private TextField tField_total;

    private MainViewController mainViewController;

    private Order currentOrder;
    private ArrayList<MenuItem> menuItemList;
    private ArrayList<Coffee> coffeeList;
    private ArrayList<Donut> donutList;

    /**
     * Data communicating helper method.
     * Enable this controller class to reach to the DB
     * @param controller
     */
    public void setMainController(MainViewController controller)
    {
        this.mainViewController = controller;

        this.currentOrder = controller.getCurrentOrder();
        this.menuItemList = currentOrder.getItemList();
        this.coffeeList = currentOrder.getCoffeeOrders();
        this.donutList = currentOrder.getDonutOrders();
        itemListInitialize();
        liveUpdate();
    }

    /**
     * Controller method to live_change data in OrderView respecting to current data.
     */
    private void liveUpdate()
    {
        DecimalFormat df = new DecimalFormat("0.00");
        listView_itemList.setItems(obs_itemList);
        tField_subTotal.setText(df.format(currentOrder.getsubTotal()));
        tField_saleTax.setText(df.format(currentOrder.getTax()));
        tField_total.setText(df.format(currentOrder.getTotalPrice()));
    }


/*  This is purposely annotated to tell what the actual execution order of controller classes with initialize() method.
    public void initialize()
    {
        //initialize() run first when create this controller object
        //then we run setMainController().
        //we cannot even set datas in this field.
        //if we can just have static for mainController, then it will be fine
        //but lol idk why it is not allowed (or I just dont know) in JFX. I know that works in JSwing.
    }
*/

    /**
     * NON-JFX initialize() method to bypass initialize() method's limitation of blackbox programming.
     * Initialize certain references which are needed to be instanciated once the controller is created.
     * Create and initialize obs_itemList.
     */
    private void itemListInitialize()
    {
        if (menuItemList != null && menuItemList.size() > 0)
        {
            for (int i = 0; i < menuItemList.size(); i++)
            {
                obs_itemList.addAll(menuItemList.get(i).toString());
            }
        }
        else
        {
            //debugger SOUT lines goes here.
        }
    }


    /**
     * Controller method to place current order to StoreOrder.
     * @param event
     */
    @FXML
    void ctrl_placeOrder(ActionEvent event)
    {
        if (currentOrder.getNumOfOrders() > 0)
        {
            mainViewController.incrementNumOfOrders();
            currentOrder.setOrderNumber(mainViewController.getNumOfOrders());
            mainViewController.getStoreOrder().add(currentOrder);
            Order newOrder = new Order(); //depoint and create new one.
            mainViewController.setCurrentOrder(newOrder); //depoint and create new one. (main pointer)
            currentOrder = mainViewController.getCurrentOrder(); //depoint and create new one. (appendix pointer)
            obs_itemList.clear();
            tField_saleTax.clear();
            tField_subTotal.clear();
            tField_total.clear();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Your Order is Placed!");
            alert.setHeaderText("Your Order is placed successfully!");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Order Basket");
            alert.setHeaderText("There is no item to place your order!");
            alert.showAndWait();
        }
    }

    /**
     * Controller method to remove selected item from the currentOrder.
     * @param event
     */
    @FXML
    void ctrl_removeItem(ActionEvent event)
    {
        try
        {
            String selectedItem_str = listView_itemList.getSelectionModel().getSelectedItem();
            if (menuItemList.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty Data");
                alert.setHeaderText("There is no item to remove.");
                alert.showAndWait();
            }
            else if (selectedItem_str.equals(null))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Data");
                alert.setHeaderText("Please select an item to remove.");
                alert.showAndWait();
            }
            else
            {
                int selectedIndex = listView_itemList.getSelectionModel().getSelectedIndex();
                MenuItem selectedItem = getSelectedItemFromStr(selectedItem_str);
                menuItemList.remove(selectedItem);
                obs_itemList.remove(selectedItem_str);
                listView_itemList.setItems(obs_itemList);
            }

            liveUpdate();
        }
        catch (NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Data");
            alert.setHeaderText("Please select an item to remove.");
            alert.showAndWait();
        }

    }

    /**
     * Helper method to find and get MenuItem object converting from string of selected item in GUI.
     * @param selectedItem_str
     * @return corresponded MenuItem object of the selected item in GUI
     */
    private MenuItem getSelectedItemFromStr(String selectedItem_str)
    {
        MenuItem result = null;
        for (int i = 0; i < menuItemList.size(); i++)
        {
            if (menuItemList.get(i).toString().equals(selectedItem_str))
            {
                result = menuItemList.get(i);
            }
        }
        return result;
    }


}
