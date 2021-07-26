package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Ivedent Lloyd III
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryAppController implements Initializable {

        //use scene builder to generate controllers using skeleton view
        @FXML
        private TableView<InventoryItems> tableView;
        @FXML
        private TableColumn<InventoryItems, String> itemPriceColumn;
        @FXML
        private TableColumn<InventoryItems, String> itemSerialNumberColumn;
        @FXML
        private TableColumn<InventoryItems, String> itemNameColumn;
        @FXML
        private TextField newItemPriceTextField;
        @FXML
        private TextField newItemSerialNumberTextField;
        @FXML
        private TextField newItemNameTextField;
        @FXML
        private TextField itemSearchTextField;

        // initialize an observable list for the tableview
        // initialize the file chooser
        // file writer function for saving data
        ObservableList<InventoryItems> tableData = FXCollections.observableArrayList();
        FileChooser fileChooser = new FileChooser();

        public void saveFile(File file, ObservableList<InventoryItems> content) {
                // print to a file using a file writer
                // exception is needed try/catch
                try {
                        PrintWriter printWriter = new PrintWriter(file);
                        printWriter.write(String.valueOf(content));
                        printWriter.close();
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                // use scene builder to create a gui with table view
                // the table view must be editable and must be able to add data to the table
                // must delete data from table
                // must be able to look up data and sort data
                // all controls must be extracted to another function in order for testing

                // get the user directory for the save button
                String currentDirectory = System.getProperty("user.dir");
                fileChooser.setInitialDirectory(new File(""+currentDirectory+""));

                // populate the table with sampled data but adding to the list
                tableData.add(new InventoryItems("$499.99", "0123456789", "PS5"));
                tableData.add(new InventoryItems("$399.99", "Asd1234gg1", "Xbox One"));
                tableData.add(new InventoryItems("$599.99", "zBBi3345aa", "Samsung TV"));
                tableData.add(new InventoryItems("$199.99", "5129387jwe", "Nitendo Switch Lite"));
                tableData.add(new InventoryItems("$899.99", "3fju4HHjdA", "Macbook Air"));
                tableData.add(new InventoryItems("$1,099.99", "k55ghSSwe", "Macbook Pro"));

                tableView.setItems(tableData);

                itemPriceColumn.setCellValueFactory(new PropertyValueFactory<InventoryItems, String>("itemPrice"));
                itemSerialNumberColumn.setCellValueFactory(new PropertyValueFactory<InventoryItems, String>("itemSerialNumber"));
                itemNameColumn.setCellValueFactory(new PropertyValueFactory<InventoryItems, String>("itemName"));

                // allow the table to be editable also set editable to true
                // the user edits the table by clicking the element
                itemPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                itemPriceColumn.setOnEditCommit(e->{
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

                //create a filtered list to only show items the user searches
                //make sure to make all strings lowercase
                FilteredList<InventoryItems> filteredItems = new FilteredList<>(this.tableData, b -> true);
                itemSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                        filteredItems.setPredicate(items -> {
                                if (newValue == null || newValue.isEmpty() || newValue.isBlank()){
                                        return true;
                                }
                                String filterLowerCase = newValue.toLowerCase();
                                if (items.getItemSerialNumber().toLowerCase().contains(filterLowerCase)){
                                        return true;
                                }
                                else if(items.getItemName().toLowerCase().contains(filterLowerCase)){
                                        return true;
                                }
                                else
                                        return false;
                        });
                });
                SortedList<InventoryItems> sortedItems = new SortedList<>(filteredItems);
                sortedItems.comparatorProperty().bind(tableView.comparatorProperty());
                tableView.setItems(sortedItems);
        }

        // extract methods in order to test
        @FXML
        public void addButtonClicked(ActionEvent event) {
                addButton();
        }
        @FXML
        public void deleteButtonClicked(ActionEvent event) {
                deleteButton();
        }
        @FXML
        public void openFileClicked(ActionEvent event) {
                openButton();
        }
        @FXML
        public void saveFileClicked(ActionEvent event) {
                saveButton();
        }

        public void addButton(){
                // when clicking the button the text box contents need to be added to the list
                // make sure to clear the boxes
                InventoryItems newItem = new InventoryItems();
                newItem.setItemPrice(newItemPriceTextField.getText());
                newItem.setItemSerialNumber(newItemSerialNumberTextField.getText());
                newItem.setItemName(newItemNameTextField.getText());

                tableData.add(newItem);

                newItemPriceTextField.clear();
                newItemSerialNumberTextField.clear();
                newItemNameTextField.clear();
        }

        public void deleteButton(){
                // when the button is pressed remove the item from the list
                ObservableList<InventoryItems> itemSelected;
                itemSelected = tableView.getSelectionModel().getSelectedItems();

                tableData.removeAll(itemSelected);
        }

        public void openButton(){
                /*
                File file = fileChooser.showOpenDialog(new Stage());
                try {
                        Scanner scanner = new Scanner(file);
                        while(scanner.hasNextLine()){}
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }

                 */
        }

        public void saveButton(){
                // when the button is clicked the file chooser needs to be opened in a new window
                File file = fileChooser.showSaveDialog(new Stage());
                if(file != null){
                        saveFile(file, tableData);
                }
        }
}

