package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;

/**
 *  Controller class to execute coffee related functions
 *  @author Minseok Park, Amogh Sarangdhar
 */
public class CoffeeController
{

    private MainViewController mainViewController;
    private Coffee coffeeOrder;

    @FXML
    private Button button_addToOrder;

    @FXML
    private CheckBox caramel, cream, milk, syrup, whippedCream;

    @FXML
    private ComboBox<Integer> coffeeNum;
    ObservableList<Integer> obs_coffeeNum = FXCollections.observableArrayList(1, 2, 3, 4, 5);

    @FXML
    private ComboBox<String> coffeeSize;
    ObservableList<String> obs_coffeeSize = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");

    @FXML
    private TextField coffeeSubtotal;

    /**
     * Initialize certain references which are needed to be instanciated once the controller is created.
     * Create StoreOrder, currentOrder object.
     */
    @FXML
    public void initialize()
    {
        coffeeNum.setValue(1);
        coffeeNum.setItems(obs_coffeeNum);

        coffeeSize.setValue("Short");
        coffeeSize.setItems(obs_coffeeSize);

        coffeeOrder = new Coffee(0);

        coffeeSubtotal.setText("1.69");
    }

    /**
     * Controller method to update subtotal an coffee Order
     * It also execute other controller class  which modify coffee's propertiesof
     * @param event of action
     */
    @FXML
    private void subTotalLiveUpdate(ActionEvent event)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        ctrl_coffeeSize();
        ctrl_coffeeAddIns();
        ctrl_coffeeNum();
        coffeeSubtotal.setText(df.format(coffeeOrder.itemPrice()));
    }


    /**
     * Controller method to modify number of coffee of the coffee object
     */
    public void ctrl_coffeeNum()
    {
        coffeeOrder.setNumOfItems(coffeeNum.getValue());
    }

    /**
     * Controller method to modify size of Coffee of the coffee object
     */
    public void ctrl_coffeeSize()
    {
        coffeeOrder.setSize_String(coffeeSize.getValue());
    }

    /**
     * Controller method to modify add-ins of the coffee object
     */
    @FXML
    public void ctrl_coffeeAddIns() // probably need refactoring, since it is quite long.
    {
        if (cream.isSelected()) {
            coffeeOrder.add(Coffee_AddIn.CREAM);
        } else if (cream.isSelected() == false) {
            coffeeOrder.remove(Coffee_AddIn.CREAM);
        }
        if (syrup.isSelected()) {
            coffeeOrder.add(Coffee_AddIn.SYRUP);
        } else if (syrup.isSelected() == false) {
            coffeeOrder.remove(Coffee_AddIn.SYRUP);
        }
        if (milk.isSelected()) {
            coffeeOrder.add(Coffee_AddIn.MILK);
        } else if (milk.isSelected() == false) {
            coffeeOrder.remove(Coffee_AddIn.MILK);
        }
        if (caramel.isSelected()) {
            coffeeOrder.add(Coffee_AddIn.CARAMEL);
        } else if (caramel.isSelected() == false) {
            coffeeOrder.remove(Coffee_AddIn.CARAMEL);
        }
        if (whippedCream.isSelected()) {
            coffeeOrder.add(Coffee_AddIn.WHIPPED_CREAM);
        } else  if (whippedCream.isSelected() == false) {
            coffeeOrder.remove(Coffee_AddIn.WHIPPED_CREAM);
        }
    }

    /**
     * Controller method to add the current coffee object into currentOrder.
     * @param event of action
     */
    @FXML
    void addToOrder(ActionEvent event)
    {
        mainViewController.addCoffee(this.coffeeOrder);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Added");
        alert.setHeaderText("Order adding complete");
        alert.setContentText("Your coffee,\n" +
                coffeeOrder.toString() + " \n" +
                "is added into your orderlist successfully");
        alert.showAndWait();
        coffeeOrder = new Coffee(coffeeOrder);
    }

    /**
     * Data communicating helper method.
     * Enable this controller class to reach to the DB
     * @param controller
     */
    public void setMainController(MainViewController controller)
    {
        this.mainViewController = controller;
    }

}
