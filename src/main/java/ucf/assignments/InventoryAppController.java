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

        ObservableList<InventoryItems> tableData = FXCollections.observableArrayList();
        FileChooser fileChooser = new FileChooser();

        public void saveFile(File file, ObservableList<InventoryItems> content) {
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
                String currentDirectory = System.getProperty("user.dir");
                fileChooser.setInitialDirectory(new File(""+currentDirectory+""));

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

        @FXML
        void addButtonClicked(ActionEvent event) {
                InventoryItems newItem = new InventoryItems();
                newItem.setItemPrice(newItemPriceTextField.getText());
                newItem.setItemSerialNumber(newItemSerialNumberTextField.getText());
                newItem.setItemName(newItemNameTextField.getText());

                tableData.add(newItem);

                newItemPriceTextField.clear();
                newItemSerialNumberTextField.clear();
                newItemNameTextField.clear();
        }

        @FXML
        void deleteButtonClicked(ActionEvent event) {
                ObservableList<InventoryItems> itemSelected;
                itemSelected = tableView.getSelectionModel().getSelectedItems();

                tableData.removeAll(itemSelected);
        }

        @FXML
        void openFileClicked(ActionEvent event) {
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

        @FXML
        void saveFileClicked(ActionEvent event) {
                File file = fileChooser.showSaveDialog(new Stage());
                if(file != null){
                        saveFile(file, tableData);
                }
        }
}

