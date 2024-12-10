package com.aurionpro.entity;

import java.sql.Timestamp;

public class Transactions {
		private int transactionId;
		private String accountNumber;
		private String accountId;
		private String type;
		private double amount;
		private String toAccount;
		private Timestamp date;
	

		
		public Transactions(int transactionId, String accountId, String type, double amount, String toAccount,
				Timestamp date ) {
			super();
			this.transactionId = transactionId;
			this.accountId = accountId;
			this.type = type;
			this.amount = amount;
			this.toAccount = toAccount;
			this.date = date;
		}
		public Transactions(int transactionId, String accountId, String type, double amount, String toAccount,
				Timestamp date, String accountNumber ) {
			super();
			this.transactionId = transactionId;
			this.accountId = accountId;
			this.type = type;
			this.amount = amount;
			this.toAccount = toAccount;
			this.date = date;
//			this.customerId = customerId;
			this.accountNumber =  accountNumber;
		}

		public int getTransactionId() {
			return transactionId;
		}

		public String getType() {
			return type;
		}

		public double getAmount() {
			return amount;
		}

		public String getToAccount() {
			return toAccount;
		}

		public Timestamp getDate() {
			return date;
		}


		public String getAccountNumber() {
			return accountNumber;
		}
		

		public String getAccountId() {
			return accountId;
		}

		@Override
		public String toString() {
			return "Transactions [transactionId=" + transactionId +", accountNumber="
					+ accountNumber + ", accountId=" + accountId + ", type=" + type + ", amount=" + amount
					+ ", toAccount=" + toAccount + ", date=" + date + "]";
		}

		
		
		
		
	}