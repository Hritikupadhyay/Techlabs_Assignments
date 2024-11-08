package com.aurionpro.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.aurionpro.exceptionHandling.InsufficientStockException;

public class Transaction implements Serializable{

	private String transactionId;
	private String productId;
	private String type;
	private int quantity;
	private LocalDateTime date;

	public Transaction(String transactionId, String productId, String type, int quantity, LocalDateTime date) {
		super();
		this.transactionId = transactionId;
		this.productId = productId;
		this.type = type;
		this.quantity = quantity;
		this.date = LocalDateTime.now();
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", productId=" + productId + ", type=" + type
				+ ", quantity=" + quantity + ", date=" + date + "]";
	}

	

}
