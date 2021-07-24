package ucf.assignments;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InventoryAppController {

        @FXML
        private TableView<?> tableView;

        @FXML
        private TableColumn<?, ?> itemValueColumn;

        @FXML
        private TableColumn<?, ?> itemSerialNumberColumn;

        @FXML
        private TableColumn<?, ?> itemNameColumn;

        @FXML
        private Button addItemButton;

        @FXML
        private Button itemDeleteButton;

        @FXML
        private TextField newItemValueTextField;

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

}

