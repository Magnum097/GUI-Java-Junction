package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Controller class to execute donut related functions
 * @author Minseok Park, Amogh Sarangdhar
 */
public class DonutController
{

    private Donut finalDonutOrder = new Donut();  //full order can have multiple donuts

    @FXML
    private Button button_addDonut;

    @FXML
    private Button button_addToOrder;

    @FXML
    private Button button_removeDonut;

    @FXML
    private TextField donutSubtotal;

    @FXML
    public ComboBox<String> donutType;
    ObservableList<String> obs_donutName = FXCollections.observableArrayList("Yeast", "Cake", "Donut hole");

    @FXML
    public ComboBox<Integer> donutNum;
    ObservableList<Integer> obs_donutNum = FXCollections.observableArrayList(1, 2, 3, 4, 5);

    @FXML
    private ListView<String> donutOrder;
    ObservableList<String> obs_donutOrder = FXCollections.observableArrayList();

    @FXML
    private ImageView donutImage;

    private MainViewController mainViewController;
    private ArrayList<Donut> donutList = new ArrayList<Donut>();

    private Image yeastdonut = new Image(getClass().getResourceAsStream("yeastdonut.jpg"));
    private Image cakedonut = new Image(getClass().getResourceAsStream("cakedonut.jpeg"));
    private Image donuthole = new Image(getClass().getResourceAsStream("donuthole.jpg"));

    @FXML
    private ListView<String> flavors;
    ObservableList<String> flavorNames = FXCollections.observableArrayList("Glaze", "Chocolate", "Strawberry");

    /**
     * Controller method to change image of donut depending on selected type of donut.
     * @param event of action
     */
    @FXML
    void displayImage(ActionEvent event)
    {
        String selected = donutType.getValue();
        if (selected.equals("Yeast"))
        {
            donutImage.setImage(yeastdonut);
        }
        else if (selected.equals("Cake"))
        {
            donutImage.setImage(cakedonut);
        }
        else if (selected.equals("Donut hole"))
        {
            donutImage.setImage(donuthole);
        }
    }


    /**
     * Initialize certain references which are needed to be instanciated once the controller is created.
     * Create, initialize, and set properties of donutType, flavor, donutOrder, and donutNum.
     */
    @FXML
    private void initialize()
    {
        donutType.setOnAction(this::displayImage);
        donutType.setValue("Yeast");
        donutType.setItems(obs_donutName);

        flavors.getItems().addAll(flavorNames);
        donutNum.setValue(1);
        donutNum.setItems(obs_donutNum);
        donutOrder.setItems(obs_donutOrder);
    }

    /**
     * Controller method to add the donut object to the current temporary basket.
     * Also change subtotal respectively, after adding the donut in.
     * @param event of action
     */
    @FXML
    public void ctrl_addDonut(ActionEvent event)
    {
        int type = convertToType();
        try {
            int num = donutNum.getValue();
            Donut_flavor flavor = convertToAddIn();
            Donut newDonut = new Donut(type, flavor, num);
            String newDonut_str = null;
            if (donutList.contains(newDonut)) //already there. just add up the number of items more
            {
                Donut oldDonut = donutList.get(donutList.indexOf(newDonut));
                String oldDonut_str = oldDonut.toString();
                int i = obs_donutOrder.indexOf(oldDonut_str);
                donutList.get(donutList.indexOf(newDonut)).addNum(newDonut.getNumOfItems());
                newDonut =  donutList.get(donutList.indexOf(newDonut));
                newDonut_str = newDonut.toString();
                obs_donutOrder.set(i, newDonut_str);
                donutOrder.setItems(obs_donutOrder);
            }
            else
            {
                donutList.add(newDonut);
                newDonut_str = newDonut.toString();
                obs_donutOrder.add(newDonut_str);
                donutOrder.setItems(obs_donutOrder);
            }
        }
        catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Data");
            alert.setHeaderText("Please select a Donut flavor.");
            alert.setContentText("Select Glaze, Chocolate or Strawberry flavor.");
            alert.showAndWait();
        }
        subTotalLiveUpdate();
    }

    /**
     * Helper method to get type of donut, converting to int
     * @return int, code of type of donut.
     */
    private int convertToType()
    {
        if (donutType.getValue().equals("Yeast"))
        {
            return Donut.YEAST_DONUT;
        }
        else if (donutType.getValue().equals("Cake"))
        {
            return Donut.CAKE_DONUT;
        }
        else if (donutType.getValue().equals("Donut hole"))
        {
            return Donut.DONUT_HOLE;
        }
        else
        {
            return -1; //error case
        }
    }

    /**
     * Helper method to get Donut_flavor enum, converting to enum
     * @return flavor of the Donut
     */
    private Donut_flavor convertToAddIn()
    {
        if (flavors.getSelectionModel().getSelectedItem().equals("Glaze"))
        {
            return Donut_flavor.GLAZE;
        }
        else if (flavors.getSelectionModel().getSelectedItem().equals("Chocolate"))
        {
            return Donut_flavor.CHOCOLATE;
        }
        else
        {
            return Donut_flavor.STRAWBERRY;
        }
    }

    /**
     * Controller class to remove selected donut by selected number of donuts.
     * Also change subtotal according to change of the donut's number.
     * @param event of action
     */
    @FXML
    public void ctrl_removeDonut(ActionEvent event)
    {
        try{
            int num = donutNum.getValue();
            int selectedIndex = donutOrder.getSelectionModel().getSelectedIndex();
            String selectedDonut_str = donutOrder.getSelectionModel().getSelectedItem();
            Donut selectedDonut = getSelectedDonutFromStr(selectedDonut_str);

            if(num > selectedDonut.getNumOfItems()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Data");
                alert.setHeaderText("Cannot remove more than " + selectedDonut.getNumOfItems() + " " + selectedDonut.getFlavor_String() + " " + selectedDonut.getTypeOfDonut_String() + " donuts");
                alert.showAndWait();
            }
            else if(num == selectedDonut.getNumOfItems()){   //fully remove the item from the list
                obs_donutOrder.remove(selectedDonut_str);
                donutList.remove(selectedDonut);
            }
            else if(num < selectedDonut.getNumOfItems()){
                selectedDonut.setNumOfItems(selectedDonut.getNumOfItems() - num);
                selectedDonut_str = selectedDonut.toString();
                obs_donutOrder.set(selectedIndex, selectedDonut_str);
                donutOrder.setItems(obs_donutOrder);
            }
        }
        catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Data");
            alert.setHeaderText("Please select a donut to remove.");
            alert.showAndWait();
        }
        subTotalLiveUpdate();
    }

    /**
     * Helper method to find and get donut object from the string of selected donut in GUI
     * @param selectedDonut_str String of selected donut in GUI
     * @return corresponded donut object
     */
    private Donut getSelectedDonutFromStr(String selectedDonut_str)
    {
        Donut result = null;
        for (int i = 0; i < donutList.size(); i++)
        {
            if (donutList.get(i).toString().equals(selectedDonut_str))
            {
                result = donutList.get(i);

            }
        }
        return result;
    }

    /**
     * Controller method to add the donut object to currentOrder's itemList
     * @param event of action
     */
    @FXML
    void ctrl_addToOrder(ActionEvent event)
    {
        if (obs_donutOrder.size() > 0)
        {
            mainViewController.addDonut(donutList);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Donut Order");
            alert.setHeaderText("Order adding complete");
            alert.setContentText("Your donut(s),\n" +
                    donutList.toString() + " \n" +
                    "is added into your orderlist successfully");
            alert.showAndWait();
            donutList.clear();
            obs_donutOrder.clear();
            donutOrder.setItems(obs_donutOrder);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Donut Order");
            alert.setHeaderText("Empty Donut Order");
            alert.setContentText("There is no donut to add!");
            alert.showAndWait();
        }

    }

    /**
     * Controller method to change subtotal according to the current donuts.
     */
    public void subTotalLiveUpdate()
    {
        DecimalFormat df = new DecimalFormat("0.00");
        double subTotal = 0;
        for (int i = 0; i < donutList.size(); i++)
        {
            subTotal = subTotal + donutList.get(i).itemPrice();
        }
        donutSubtotal.setText(df.format(subTotal));
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
