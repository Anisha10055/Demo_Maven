package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.Payment;
import com.training.educationsystem.exceptions.PaymentException;

public interface PaymentService {
	public Payment addPayment(Payment payment);
	public Payment getPaymentById(int id) throws PaymentException;
	public List<Payment> viewPayment() throws PaymentException;

}
