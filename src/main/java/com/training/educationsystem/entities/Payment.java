package com.training.educationsystem.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_table")
public class Payment {
	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;

	@Column(name = "card_number", nullable = false)
	private int cardNumber;

	@Column(name = "card_type", nullable = false)
	private String cardType;

	@Column(name = "bank_name", nullable = false)
	private String bankName;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "payment_date", nullable = false)
	private String paymentDate;

	public Payment() {
		super();
	}

	public Payment(int transactionId, int cardNumber, String cardType, String bankName, double amount,
			String description, String paymentDate) {
		super();
		this.transactionId = transactionId;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.bankName = bankName;
		this.amount = amount;
		this.description = description;
		this.paymentDate = paymentDate;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Payment [transactionId=" + transactionId + ", cardNumber=" + cardNumber + ", cardType=" + cardType
				+ ", bankName=" + bankName + ", amount=" + amount + ", description=" + description + ", paymentDate="
				+ paymentDate + "]";
	}

}
