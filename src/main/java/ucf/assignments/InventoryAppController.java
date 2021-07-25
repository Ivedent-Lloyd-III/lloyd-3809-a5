package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Ivedent Lloyd III
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryAppController implements Initializable {

        @FXML
        private TableView<InventoryItems> tableView;

        @FXML
        private TableColumn<InventoryItems, String> itemPriceColumn;

        @FXML
        private TableColumn<InventoryItems, String> itemSerialNumberColumn;

        @FXML
        private TableColumn<InventoryItems, String> itemNameColumn;

        @FXML
        private Button addItemButton;

        @FXML
        private Button itemDeleteButton;

        @FXML
        private TextField newItemPriceTextField;

        @FXML
        private TextField newItemSerialNumberTextField;

        @FXML
        private TextField newItemNameTextField;

        @FXML
        private TextField itemSearchTextField;

        @FXML
        private Button searchButton;

        @FXML
        private TextField itemSearchDisplay;

        @FXML
        private Button inventorySaveButton;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                // fill data table with sample data
                // data must be filled using arrays
                // table must be editable
                // buttons have to be used to add and delete rows
                loadTableSampleData();
                initializeTable();

        }

        public void initializeTable(){
                initializeColumns();
        }

        public void initializeColumns(){
                // columns must be formatted using items class
                // format using getters and setters in another class for the items
                itemPriceColumn.setCellValueFactory(new PropertyValueFactory<InventoryItems, String>("itemPrice"));
                itemSerialNumberColumn.setCellValueFactory(new PropertyValueFactory<InventoryItems, String>("itemSerialNumber"));
                itemNameColumn.setCellValueFactory(new PropertyValueFactory<InventoryItems, String>("itemName"));

                // edit the columns using edit columns method
                editColumns();
        }

        public void editColumns(){

                // this method is to allows the items in the columns to be edited by the user
                // allow the user to click the items on the table to edit them
                itemPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                itemNameColumn.setOnEditCommit(e->{
                        e.getTableView().getItems().get(e.getTablePosition().getRow()).setItemPrice(e.getNewValue());
                });
                itemSerialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                itemSerialNumberColumn.setOnEditCommit(e->{
                        e.getTableView().getItems().get(e.getTablePosition().getRow()).setItemSerialNumber(e.getNewValue());
                });
                itemNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                itemNameColumn.setOnEditCommit(e->{
                        e.getTableView().getItems().get(e.getTablePosition().getRow()).setItemName(e.getNewValue());
                });

                tableView.setEditable(true);

        }

        public void loadTableSampleData(){
                // load sample data
                // data must also be edited by the user and can be deleted completely
                ObservableList<InventoryItems> sampleData = FXCollections.observableArrayList();
                sampleData.add(new InventoryItems("$499.99", "12345678", "PS5"));

                tableView.setItems(sampleData);
        }

        @FXML
        void addButtonClicked(ActionEvent event) {
                // the button must add a new item name, due date, and description
                // the user then can edit the status upon completion
                // get the text input from the user
                // after the user clicks button, array values must be populated and displayed correctly
                // after the user enters, the text windows must be cleared
                InventoryItems newItem = new InventoryItems();
                newItem.setItemPrice(newItemPriceTextField.getText());
                newItem.setItemSerialNumber(newItemSerialNumberTextField.getText());
                newItem.setItemName(newItemNameTextField.getText());
                tableView.getItems().addAll(newItem);

                newItemPriceTextField.clear();
                newItemSerialNumberTextField.clear();
                newItemNameTextField.clear();

        }

        @FXML
        void deleteButtonClicked(ActionEvent event) {

                // allow the user to highlight a row in the column and delete it from the array
                // remove all items from the array index
                ObservableList<InventoryItems> itemSelected, allItems;
                allItems = tableView.getItems();
                itemSelected = tableView.getSelectionModel().getSelectedItems();

                itemSelected.forEach(allItems::remove);

        }

        @FXML
        void saveButtonClicked(ActionEvent event) {

        }

        @FXML
        void searchButtonClicked(ActionEvent event) {

        }

}

