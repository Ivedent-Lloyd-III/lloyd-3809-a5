package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Ivedent Lloyd III
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryAppControllerTest {

    @Test
    void addButton() {
        int testAdd = 1;

        ArrayList<InventoryItems> tableDataAddTest = new ArrayList<>();
        tableDataAddTest.add(new InventoryItems("$499.99", "0123456789", "PS5"));
        tableDataAddTest.add(new InventoryItems("$399.99", "Asd1234gg1", "Xbox One"));
        tableDataAddTest.add(new InventoryItems("$599.99", "zBBi3345aa", "Samsung TV"));
        tableDataAddTest.add(new InventoryItems("$199.99", "5129387jwe", "Nitendo Switch Lite"));
        tableDataAddTest.add(new InventoryItems("$899.99", "3fju4HHjdA", "Macbook Air"));

        while( testAdd != 0 ){

            tableDataAddTest.add(new InventoryItems("$1,099.99", "k55ghSSwe", "Macbook Pro"));

        }

        ArrayList<InventoryItems> testAddActual = new ArrayList<>();
        testAddActual.add(new InventoryItems("$499.99", "0123456789", "PS5"));
        testAddActual.add(new InventoryItems("$399.99", "Asd1234gg1", "Xbox One"));
        testAddActual.add(new InventoryItems("$599.99", "zBBi3345aa", "Samsung TV"));
        testAddActual.add(new InventoryItems("$199.99", "5129387jwe", "Nitendo Switch Lite"));
        testAddActual.add(new InventoryItems("$899.99", "3fju4HHjdA", "Macbook Air"));
        testAddActual.add(new InventoryItems("$1,099.99", "k55ghSSwe", "Macbook Pro"));

        assertEquals(testAddActual, tableDataAddTest);
    }

    @Test
    void deleteButton() {
        int testDelete = 1;

        ArrayList<InventoryItems> tableDataDeleteTest = new ArrayList<>();
        tableDataDeleteTest.add(new InventoryItems("$499.99", "0123456789", "PS5"));
        tableDataDeleteTest.add(new InventoryItems("$399.99", "Asd1234gg1", "Xbox One"));
        tableDataDeleteTest.add(new InventoryItems("$599.99", "zBBi3345aa", "Samsung TV"));
        tableDataDeleteTest.add(new InventoryItems("$199.99", "5129387jwe", "Nitendo Switch Lite"));
        tableDataDeleteTest.add(new InventoryItems("$899.99", "3fju4HHjdA", "Macbook Air"));
        tableDataDeleteTest.add(new InventoryItems("$1,099.99", "k55ghSSwe", "Macbook Pro"));

        while( testDelete != 0 ){

            tableDataDeleteTest.remove(new InventoryItems("$1,099.99", "k55ghSSwe", "Macbook Pro"));

        }

        ObservableList<InventoryItems> testDeleteActual = FXCollections.observableArrayList();
        testDeleteActual.add(new InventoryItems("$499.99", "0123456789", "PS5"));
        testDeleteActual.add(new InventoryItems("$399.99", "Asd1234gg1", "Xbox One"));
        testDeleteActual.add(new InventoryItems("$599.99", "zBBi3345aa", "Samsung TV"));
        testDeleteActual.add(new InventoryItems("$199.99", "5129387jwe", "Nitendo Switch Lite"));
        testDeleteActual.add(new InventoryItems("$899.99", "3fju4HHjdA", "Macbook Air"));

        assertEquals(testDeleteActual, tableDataDeleteTest);

    }

    @Test
    void saveFile() {
    }

    @Test
    void openButton() {
    }

    @Test
    void saveButton() {
    }
}