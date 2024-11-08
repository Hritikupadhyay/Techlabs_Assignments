package com.aurionpro.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.aurionpro.exceptionHandling.DuplicateProductException;
import com.aurionpro.exceptionHandling.DuplicateSupplierException;



import java.io.*;
import java.util.List;

public class FileManagement {
    public static void saveData(Inventory inventory) {
        try (ObjectOutputStream instream = new ObjectOutputStream(new FileOutputStream("inventory.dat"))) {
            instream.writeObject(inventory.getProducts());
            instream.writeObject(inventory.getSuppliers());
            instream.writeObject(inventory.getTransactions());
        } catch (IOException e) {
            System.out.println("error in saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadData(Inventory inventory) {
        try (ObjectInputStream  oustream= new ObjectInputStream(new FileInputStream("inventory.dat"))) {
            List<Product> products = (List<Product>) oustream.readObject();
            List<Supplier> suppliers = (List<Supplier>) oustream.readObject();
            List<Transaction> transactions = (List<Transaction>) oustream.readObject();
            inventory.setProducts(products);
            inventory.setSuppliers(suppliers);
            inventory.setTransactions(transactions);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error in loading data: " + e.getMessage());
        }
    }
}

