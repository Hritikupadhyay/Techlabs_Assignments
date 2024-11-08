package com.aurionpro.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.aurionpro.exceptionHandling.DuplicateProductException;
import com.aurionpro.exceptionHandling.DuplicateSupplierException;
import com.aurionpro.exceptionHandling.InsufficientStockException;
import com.aurionpro.exceptionHandling.InvalidProductIDException;

import java.io.*;
import java.util.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Inventory implements Serializable {
	private List<Product> products;
	private List<Supplier> suppliers;
	private List<Transaction> transactions;

	public Inventory() {
		products = new ArrayList<>();
		suppliers = new ArrayList<>();
		transactions = new ArrayList<>();
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void addProduct(Product product) throws DuplicateProductException {
		for (Product item : products) {
			if (item.getProductId().equals(product.getProductId())) {
				throw new DuplicateProductException("product with Id " + product.getProductId() + " already exists");
			}
		}
		products.add(product);
	}

	public void updateProduct(String productId, Product updatedProduct) throws InvalidProductIDException {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProductId().equals(productId)) {
				products.set(i, updatedProduct);
				return;
			}
		}
		throw new InvalidProductIDException("product Id" + productId + " not found");
	}

	public void deleteProduct(String productId) {
		products.removeIf(product -> product.getProductId().equals(productId));
	}

	public Product viewProductDetails(String productId) throws InvalidProductIDException {
		for (Product product : products) {
			if (product.getProductId().equals(productId)) {
				return product;
			}
		}
		throw new InvalidProductIDException("product ID " + productId + " not found");
	}

	public List<Product> viewAllProducts() {
		return products;
	}

	public void addSupplier(Supplier supplier) throws DuplicateSupplierException {
		for (Supplier dealer : suppliers) {
			if (dealer.getSupplierId().equals(supplier.getSupplierId())) {
				throw new DuplicateSupplierException(
						"supplier with Id " + supplier.getSupplierId() + " already exists");
			}
		}
		suppliers.add(supplier);
	}

	public void updateSupplier(String supplierId, Supplier updatedSupplier) throws InvalidProductIDException {
		for (int i = 0; i < suppliers.size(); i++) {
			if (suppliers.get(i).getSupplierId().equals(supplierId)) {
				suppliers.set(i, updatedSupplier);
				return;
			}
		}
		throw new InvalidProductIDException("supplier Is " + supplierId + " not found");
	}

	public void deleteSupplier(String supplierId) {
		suppliers.removeIf(supplier -> supplier.getSupplierId().equals(supplierId));
	}

	public Supplier viewSupplierDetails(String supplierId) throws InvalidProductIDException {
		for (Supplier supplier : suppliers) {
			if (supplier.getSupplierId().equals(supplierId)) {
				return supplier;
			}
		}
		throw new InvalidProductIDException("supplier Id " + supplierId + " not found");
	}

	public List<Supplier> viewAllSuppliers() {
		return suppliers;
	}

	public void addStock(String productId, int quantity) throws InvalidProductIDException {
		for (Product product : products) {
			if (product.getProductId().equals(productId)) {
				product.setQuantity(product.getQuantity() + quantity);
				transactions.add(new Transaction(UUID.randomUUID().toString(), productId, "add", quantity, null));
				return;
			}
		}
		throw new InvalidProductIDException("product Id " + productId + " not found");
	}

	public void removeStock(String productId, int quantity)
			throws InvalidProductIDException, InsufficientStockException {
		for (Product product : products) {
			if (product.getProductId().equals(productId)) {
				if (product.getQuantity() < quantity) {
					throw new InsufficientStockException("insufficient stock for product ID " + productId);
				}
				product.setQuantity(product.getQuantity() - quantity);
				transactions.add(new Transaction(UUID.randomUUID().toString(), productId, "remove", quantity, null));
				return;
			}
		}
		throw new InvalidProductIDException("product Id " + productId + " not found");
	}

	public List<Transaction> viewTransactionHistory(String productId) {
		List<Transaction> history = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if (transaction.getProductId().equals(productId)) {
				history.add(transaction);
			}
		}
		return history;
	}
}
