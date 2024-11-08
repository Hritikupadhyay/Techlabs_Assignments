package com.aurionpro.model;

import java.util.List;

import com.aurionpro.exceptionHandling.DuplicateProductException;
import com.aurionpro.exceptionHandling.DuplicateSupplierException;
import com.aurionpro.exceptionHandling.InsufficientStockException;
import com.aurionpro.exceptionHandling.InvalidProductIDException;

public class InventoryFacade {
	private Inventory inventory;

	public InventoryFacade() {
		this.inventory = new Inventory();
	}

	public void addProduct(Product product) throws DuplicateProductException {
		inventory.addProduct(product);
	}

	public void updateProduct(String productId, Product updatedProduct) throws InvalidProductIDException {
		inventory.updateProduct(productId, updatedProduct);
	}

	public void deleteProduct(String productId) {
		inventory.deleteProduct(productId);
	}

	public Product viewProductDetails(String productId) throws InvalidProductIDException {
		return inventory.viewProductDetails(productId);
	}

	public List<Product> viewAllProducts() {
		return inventory.viewAllProducts();
	}

	public void addSupplier(Supplier supplier) throws DuplicateSupplierException {
		inventory.addSupplier(supplier);
	}

	public void updateSupplier(String supplierId, Supplier updatedSupplier) throws InvalidProductIDException {
		inventory.updateSupplier(supplierId, updatedSupplier);
	}

	public void deleteSupplier(String supplierId) {
		inventory.deleteSupplier(supplierId);
	}

	public Supplier viewSupplierDetails(String supplierId) throws InvalidProductIDException {
		return inventory.viewSupplierDetails(supplierId);
	}

	public List<Supplier> viewAllSuppliers() {
		return inventory.viewAllSuppliers();
	}

	public void addStock(String productId, int quantity) throws InvalidProductIDException {
		inventory.addStock(productId, quantity);
	}

	public void removeStock(String productId, int quantity)
			throws InvalidProductIDException, InsufficientStockException {
		inventory.removeStock(productId, quantity);
	}

	public List<Transaction> viewTransactionHistory(String productId) {
		return inventory.viewTransactionHistory(productId);
	}

	public void saveData() {
		FileManagement.saveData(inventory);
	}

	public void loadData() {
		FileManagement.loadData(inventory);
	}
}
