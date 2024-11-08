package com.aurionpro.model;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.exceptionHandling.DuplicateProductException;
import com.aurionpro.exceptionHandling.DuplicateSupplierException;
import com.aurionpro.exceptionHandling.InsufficientStockException;
import com.aurionpro.exceptionHandling.InvalidProductIDException;

public class InventoryController {
    private InventoryFacade inventoryFacade;
    private Scanner scanner;

    public InventoryController() {
        inventoryFacade = new InventoryFacade();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("welcome to inventory management system");
            System.out.println("1] product management");
            System.out.println("2] supplier management");
            System.out.println("3] transaction management");
            System.out.println("4] save data");
            System.out.println("5] load data");
            System.out.println("6] exit");
            System.out.print("enter choice: ");

            int choice = scanner.nextInt();
            handleMenuChoice(choice);
        }
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                productManagement();
                break;
            case 2:
                supplierManagement();
                break;
            case 3:
                transactionManagement();
                break;
            case 4:
                inventoryFacade.saveData();
                System.out.println("data saved");
                break;
            case 5:
                inventoryFacade.loadData();
                System.out.println("data loaded");
                break;
            case 6:
                System.out.println("exiting..");
                System.exit(0);
                break;
            default:
                System.out.println("invalid choice,try again");
        }
    }

    private void productManagement() {
        System.out.println("product management:");
        System.out.println("1] add product");
        System.out.println("2] update product");
        System.out.println("3] delete product");
        System.out.println("4] view product details");
        System.out.println("5] view all products");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addProduct();
                break;
            case 2:
                updateProduct();
                break;
            case 3:
                deleteProduct();
                break;
            case 4:
                viewProductDetails();
                break;
            case 5:
                viewAllProducts();
                break;
            default:
                System.out.println("invalid choice");
        }
    }

    private void addProduct() {
        System.out.print("enter product Id: ");
        String id = scanner.next();
        System.out.print("producr name: ");
        String name = scanner.next();
        System.out.print("give description: ");
        String description = scanner.next();
        System.out.print("enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("enter price: ");
        double price = scanner.nextDouble();

        try {
            Product product = new Product(id, name, description, quantity, price);
            inventoryFacade.addProduct(product);
            System.out.println("product added successfully");
        } catch (DuplicateProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateProduct() {
        System.out.print("enter product Id to update: ");
        String id = scanner.next();
        System.out.print("enter new product name: ");
        String name = scanner.next();
        System.out.print("enter new description: ");
        String description = scanner.next();
        System.out.print("enter new quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("enter new price: ");
        double price = scanner.nextDouble();

        try {
            Product updatedProduct = new Product(id, name, description, quantity, price);
            inventoryFacade.updateProduct(id, updatedProduct);
            System.out.println("product updated successfully");
        } catch (InvalidProductIDException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProduct() {
        System.out.print("enter product Id to delete: ");
        String id = scanner.next();
        inventoryFacade.deleteProduct(id);
        System.out.println("product deleted successfully");
    }

    private void viewProductDetails() {
        System.out.print("enter product Id to view details: ");
        String id = scanner.next();
        try {
            Product product = inventoryFacade.viewProductDetails(id);
            System.out.println(product);
        } catch (InvalidProductIDException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllProducts() {
        List<Product> products = inventoryFacade.viewAllProducts();
        if (products.isEmpty()) {
            System.out.println("no products ");
        } else {
            products.forEach(System.out::println);
        }
    }

    private void supplierManagement() {
        System.out.println("supplier management:");
        System.out.println("1] add supplier");
        System.out.println("2] update supplier");
        System.out.println("3] delete supplier");
        System.out.println("4] view supplier details");
        System.out.println("5] view all suppliers");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addSupplier();
                break;
            case 2:
                updateSupplier();
                break;
            case 3:
                deleteSupplier();
                break;
            case 4:
                viewSupplierDetails();
                break;
            case 5:
                viewAllSuppliers();
                break;
            default:
                System.out.println("nvalid choice");
        }
    }

    private void addSupplier() {
        System.out.print("enter supplier Id: ");
        String id = scanner.next();
        System.out.print("enter supplier name: ");
        String name = scanner.next();
        System.out.print("enter contact info: ");
        String contactInfo = scanner.next();

        try {
            Supplier supplier = new Supplier(id, name, contactInfo);
            inventoryFacade.addSupplier(supplier);
            System.out.println("supplier added successfully");
        } catch (DuplicateSupplierException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateSupplier() {
        System.out.print("enter supplier Id to update: ");
        String id = scanner.next();
        System.out.print("enter new supplier name: ");
        String name = scanner.next();
        System.out.print("enter new contact info: ");
        String contactInfo = scanner.next();

        try {
            Supplier updatedSupplier = new Supplier(id, name, contactInfo);
            inventoryFacade.updateSupplier(id, updatedSupplier);
            System.out.println("supplier updated successfully");
        } catch (InvalidProductIDException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteSupplier() {
        System.out.print("enter supplier Id to delete: ");
        String id = scanner.next();
        inventoryFacade.deleteSupplier(id);
        System.out.println("supplier deleted successfully");
    }

    private void viewSupplierDetails() {
        System.out.print("enter supplier Id to view details: ");
        String id = scanner.next();
        try {
            Supplier supplier = inventoryFacade.viewSupplierDetails(id);
            System.out.println(supplier);
        } catch (InvalidProductIDException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllSuppliers() {
        List<Supplier> suppliers = inventoryFacade.viewAllSuppliers();
        if (suppliers.isEmpty()) {
            System.out.println("no suppliers ");
        } else {
            suppliers.forEach(System.out::println);
        }
    }

    private void transactionManagement() {
        System.out.println("Transaction management:");
        System.out.println("1] add stock");
        System.out.println("2] remove stock");
        System.out.println("3] view transaction history");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addStock();
                break;
            case 2:
                removeStock();
                break;
            case 3:
                viewTransactionHistory();
                break;
            default:
                System.out.println("invalid choice");
        }
    }

    private void addStock() {
        System.out.print("enter product Id to add stock: ");
        String productId = scanner.next();
        System.out.print("enter quantity to add: ");
        int quantity = scanner.nextInt();

        try {
            inventoryFacade.addStock(productId, quantity);
            System.out.println("stock added successfully");
        } catch (InvalidProductIDException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeStock() {
        System.out.print("enter product Id to remove stock: ");
        String productId = scanner.next();
        System.out.print("enter quantity to remove: ");
        int quantity = scanner.nextInt();

        try {
            inventoryFacade.removeStock(productId, quantity);
            System.out.println("stock removed ");
        } catch (InvalidProductIDException | InsufficientStockException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewTransactionHistory() {
        System.out.print("enter product Id to view transaction history: ");
        String productId = scanner.next();
        List<Transaction> history = inventoryFacade.viewTransactionHistory(productId);
        if (history.isEmpty()) {
            System.out.println("no transactions found for this product");
        } else {
            history.forEach(System.out::println);
        }
    }
}

