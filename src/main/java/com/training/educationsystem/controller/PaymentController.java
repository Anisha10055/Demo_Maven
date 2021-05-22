package com.training.educationsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Payment;
import com.training.educationsystem.exception.ErrorMessage;
import com.training.educationsystem.exceptions.DateException;
import com.training.educationsystem.exceptions.InvalidPaymentException;
import com.training.educationsystem.exceptions.PaymentException;
import com.training.educationsystem.services.IPaymentService;



/**
 * 
 * @author Anisha
 *
 */
@RestController
@RequestMapping("/api/educationsystem")
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	@Autowired
	IPaymentService paymentService;

	/**
	 * This method adds Payment for the course after enrollment
	 * 
	 * @param payment
	 * @return Payment
	 * @throws InvalidPaymentException
	 */
	@PostMapping(value = "/add-Payment")
	public Payment addPayment(@RequestBody Payment payment) throws InvalidPaymentException,DateException {
		logger.info("adding payment details - start");

		String alphaPattern = "[a-zA-Z]+";
		String cardNoPattern = "[0-9]{9}";
		String datePattern = "^(1[0-2]|0[1-9])/(3[01]"
                + "|[12][0-9]|0[1-9])/[0-9]{4}$";
		List<String> bankNameList = new ArrayList();
		bankNameList.add("Axis");
		bankNameList.add("HDFC");
		bankNameList.add("ICICI");
		bankNameList.add("Citi");
		bankNameList.add("Saraswat");
		

		if (!(Pattern.matches(alphaPattern, payment.getCardType()))) {
			logger.error("Card type or bank name cannot contain integer values or should not be null");
			throw new InvalidPaymentException(
					"Card type or bank name cannot contain integer values or should not be null");
		}
		else if(!(bankNameList.contains(payment.getBankName())))
		{
			logger.error("Please enter from the following list: Axis, HDFC, ICICI, Citi, Saraswat");
			throw new InvalidPaymentException("Please enter from the following list: Axis, HDFC, ICICI, Citi, Saraswat");
		}

		else if (!(Pattern.matches(cardNoPattern, String.valueOf(payment.getCardNumber())))) {
			logger.error("Card Number should be of length 9");
			throw new InvalidPaymentException("Card Number should be of length 9");
		} else if (payment.getAmount() == 0 || payment.getDescription() == null) {
			logger.error("Amount and Description cannot contain null values");
			throw new InvalidPaymentException("Amount and Description cannot contain null values");
		} else if (!(Pattern.matches(datePattern, payment.getPaymentDate()))) {
			logger.error("Please enter in the format dd/mm/yyyy");
			throw new DateException("Please enter in the format dd/mm/yyyy");
		}

		else {
			Payment addPayment = paymentService.addPayment(payment);
			logger.info("adding payment details - end");
			return addPayment;

		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidPaymentException.class)
	ErrorMessage exceptionHandler(InvalidPaymentException e) {
		return new ErrorMessage("400", e.str);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PaymentException.class)
	ErrorMessage exceptionHandler(PaymentException e) {
		return new ErrorMessage("404", e.str);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DateException.class)
	ErrorMessage exceptionHandler(DateException e) {
		return new ErrorMessage("400", e.str);
	}

	/**
	 * This method views the payment details by respective Id
	 * 
	 * @param id
	 * @return Payment
	 * @throws PaymentException
	 */

	@GetMapping(value = "/get-Payment/{transactionId}")
	public Payment getPaymentById(@PathVariable("transactionId") int id) throws PaymentException {
		logger.info("viewing payment by id - start");
		Payment getPayment = paymentService.getPaymentById(id);
		logger.info("viewing payment by id - end");
		return getPayment;
	}

	/**
	 * This method returns all payment details as list
	 * @return List
	 * @throws PaymentException
	 */

	@GetMapping(value = "/view-Payment")
	public List<Payment> viewPayment() throws PaymentException {
		logger.info("viewing payment list - start");
		List<Payment> getPaymentList = paymentService.viewPayment();
		logger.info("viewing payment list - end");
		return getPaymentList;
	}



}
