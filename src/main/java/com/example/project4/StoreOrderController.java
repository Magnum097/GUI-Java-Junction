package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Controller class to execute StoreOrder related functions
 * @author Minseok Park, Amogh Sarangdhar
 */
public class StoreOrderController {

    @FXML
    private Button button_cancel;

    @FXML
    private Button button_export;

    @FXML
    private Button button_getDetail;

    @FXML
    private ComboBox<String> cmbox_orderNo;
    ObservableList<String> obs_orderNos = FXCollections.observableArrayList();

    @FXML
    private ListView<String> listView_orderDetail;
    ObservableList<String> obs_orderDetails = FXCollections.observableArrayList();

    @FXML
    private TextField tfield_total;

    private MainViewController mainViewController;
    private StoreOrder storeOrder;
    private ArrayList<Order> orderLists;

    /**
     * Data communicating helper method.
     * Enable this controller class to reach to the DB
     * @param controller
     */
    public void setMainController(MainViewController controller)
    {
        this.mainViewController = controller;
        nonFXinitialize();
    }

    /**
     * NON-JFX initialize() method to bypass initialize() method's limitation of blackbox programming.
     * Initialize certain references which are needed to be instantiated once the controller is created.
     * Create and initialize obs_itemList.
     */
    private void nonFXinitialize()
    {
        storeOrder = mainViewController.getStoreOrder();
        orderLists = storeOrder.getOrderLists();

        for (int i = 0; i < orderLists.size(); i++)
        {
            obs_orderNos.add(Integer.toString(orderLists.get(i).getOrderNumber()));
            cmbox_orderNo.setItems(obs_orderNos); //OnAction point
            cmbox_orderNo.setValue(obs_orderNos.get(0));
        }
        if (obs_orderNos.isEmpty())
        {
            obs_orderDetails.add("NO PLACED ORDERS...");
            listView_orderDetail.setItems(obs_orderDetails);
        }
        else
        {
            obs_orderDetails.add("Select Order number from the Drop-down menu and Click on Get Detail");
            listView_orderDetail.setItems(obs_orderDetails);
        }
    }

    /**
     * Controller method to show and change detail of the order respecting to selected order number.
     */
    @FXML
    public void displayOrderDetail()
    {
        obs_orderDetails.clear();
        if (storeOrder.getNumOfOrders() > 0)
        {
            String selectedOrderNo = cmbox_orderNo.getSelectionModel().getSelectedItem();
            Order thisOrder = findOrderByOrderNo(selectedOrderNo);
            ArrayList<String> thisOrder_str = thisOrder.getStringArrayOfItems();
            for (int i = 0; i < thisOrder_str.size(); i++)
            {
                obs_orderDetails.add(thisOrder_str.get(i));
            }
            DecimalFormat df = new DecimalFormat("0.00");
            tfield_total.setText(df.format(thisOrder.getTotalPrice()));
        }
        else
        {
            tfield_total.clear();
            obs_orderDetails.add("NO PLACED ORDERS...");
        }
        listView_orderDetail.setItems(obs_orderDetails);
    }

    /**
     * Helper method to find and get corresponded Order object from selected Order number in GUI.
     * @param selectedOrderNo order that has been selected from the ListView
     * @return
     */
    private Order findOrderByOrderNo(String selectedOrderNo)
    {
        for (int i = 0; i < orderLists.size(); i++)
        {
            if (orderLists.get(i).getOrderNumber() == Integer.parseInt(selectedOrderNo))
            {
                return orderLists.get(i);
            }
        }
        return null;
    }

    /**
     * Controller class to cancel selected Order.
     * @param event of action
     */
    @FXML
    void ctrl_cancel(ActionEvent event)
    {
        if (storeOrder.getNumOfOrders() > 0)
        {
            String selectedOrderNo = cmbox_orderNo.getSelectionModel().getSelectedItem();
            orderLists.remove(findOrderByOrderNo(selectedOrderNo));
            obs_orderNos.remove(selectedOrderNo);
            obs_orderDetails.clear();
            if (obs_orderNos.size() > 0)
            {
                cmbox_orderNo.setValue(obs_orderNos.get(0));
            }
            else
            {
                tfield_total.clear();
                obs_orderDetails.add("NO PLACED ORDERS...");
                listView_orderDetail.setItems(obs_orderDetails);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cancel Order");
            alert.setHeaderText("The Order number [" + selectedOrderNo + "] " + "has been Canceled");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cancel Order");
            alert.setHeaderText("There is no order to cancel");
            alert.showAndWait();
        }
    }


    /**
     * Controller class to export ALL orders into a txt file.
     * @param event of action
     */
    @FXML
    public void ctrl_export(ActionEvent event)
    {
        if (orderLists.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Store Order");
            alert.setHeaderText("There is no order to export...");
            alert.showAndWait();
        }
        else
        {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Target File for the Export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File targeFile = chooser.showSaveDialog(stage); //get the reference of the target file
            if (targeFile == null)
            {
                return;
            }
            exportDB(targeFile.getAbsolutePath());
        }
    }

    /**
     * Helper method which write data of StoreOrder into a file.
     * @param path
     */
    private void exportDB(String path)
    {
        if (path == null)
        {
            return;
        }
        ArrayList<String> storeOrderExport = storeOrder.getStoreOrderExport();
        File target = new File(path);
        try
        {
            FileWriter writer = new FileWriter(path);
            for (int i = 0; i < storeOrderExport.size(); i++)
            {
                writer.write(storeOrderExport.get(i));
            }
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
