package com.training.educationsystem.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.Payment;
import com.training.educationsystem.exceptions.InvalidPaymentException;
import com.training.educationsystem.exceptions.PaymentException;
import com.training.educationsystem.repositories.PaymentRepository;



/**
 * 
 * @author Anisha
 *
 */
@Transactional
@Service
public class IPaymentService implements PaymentService{
	private static final Logger logger = LoggerFactory.getLogger(IPaymentService.class);
	@Autowired 
	PaymentRepository paymentRepository;
	

	/**
	 * This method adds  Payment for the course after enrollment
	 * @param payment
	 * @return Payment
	 * @throws InvalidPaymentException
	 */
	@Override
	public Payment addPayment(Payment payment){
		logger.info("service adding payment details - start");
		paymentRepository.save(payment);
		logger.info("service adding payment details - end");
		return payment;
	}
	
	/**
	 * This method views the payment details by respective Id
	 * @param id
	 * @return Payment
	 */

	@Override
	public Payment getPaymentById(int id) throws PaymentException{
		logger.info("service viewing payment by id - start");
		Payment payment = paymentRepository.findById(id).orElse(null);
		if(payment!=null)
		{
			logger.info("service viewing payment by id - end");
			return payment;
		}
		else
		{
			logger.error("Payment details not found");
			throw new PaymentException("Payment details not found");
		}
		
	}
	
	/**
	 * This method returns all payment details as list
	 * @return List
	 */


	@Override
	public List<Payment> viewPayment() throws PaymentException{
		logger.info("service viewing payment list - start");
		List<Payment> paymentList = paymentRepository.findAll();
		if(paymentList.size()>0)
		{
			logger.info("service viewing payment list - end");
			return paymentList;
		}
		else
		{
			logger.error("Payment details not found");
			throw new PaymentException("Payment details not found");
		}
		
	}
}