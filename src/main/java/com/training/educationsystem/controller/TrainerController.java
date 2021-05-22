package com.training.educationsystem.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exception.AlreadyExistsException;
import com.training.educationsystem.exception.ErrorMessage;
import com.training.educationsystem.exception.InvalidTrainerException;
import com.training.educationsystem.exception.ListEmptyException;
import com.training.educationsystem.exception.TrainerNotFoundException;
import com.training.educationsystem.services.ITrainerService;

/**
 * This is the controller for Trainer module
 * 
 * @author Afeeda A.H
 *
 */
@RestController
@RequestMapping("/api/educationsystem/trainer")
public class TrainerController {

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	ITrainerService service;

	/**
	 * This method adds Trainer in the System
	 * 
	 * @param trainer
	 * @return Trainer 
	 * @throws InvalidTrainerException
	 */
	@PostMapping("/add-trainer")
	public Trainer addTrainer(@RequestBody Trainer trainer) throws InvalidTrainerException {
		logger.info("Add trainer  (Service) - START");
		String namePattern = "^[a-zA-Z]+$";
		if (trainer.getFirstName() == "" || trainer.getMiddleName() == "" || trainer.getLastName() == "") {
			logger.error("First name, middle name and last name cannot be Empty!");
			throw new InvalidTrainerException("First name, middle name and last name cannot be Empty!");
		} else if (!(Pattern.matches(namePattern, trainer.getFirstName()))
				|| (!Pattern.matches(namePattern, trainer.getMiddleName()))
				|| (!Pattern.matches(namePattern, trainer.getLastName()))) {
			logger.error("First name, middle name and last name must contain alphabets only!");
			throw new InvalidTrainerException("First name, middle name and last name must contain alphabets only!");
		} else {
			logger.info("Adding Trainer");
			Trainer addedTrainer = service.addTrainer(trainer);
			logger.info("Add trainer  (Service) - END");
			return addedTrainer;
		}
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidTrainerException.class)
	ErrorMessage exceptionHandler(InvalidTrainerException e) {
		return new ErrorMessage("400", e.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(TrainerNotFoundException.class)
	ErrorMessage exceptionHandler(TrainerNotFoundException e) {
		return new ErrorMessage("404", e.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ListEmptyException.class)
	ErrorMessage exceptionHandler(ListEmptyException e) {
		return new ErrorMessage("404", e.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AlreadyExistsException.class)
	ErrorMessage exceptionHandler(AlreadyExistsException e) {
		return new ErrorMessage("400", e.message);
	}

	/**
	 * This method deletes each Trainer from the system
	 * 
	 * @param trainerId
	 * @return String
	 * @throws TrainerNotFoundException 
	 */
	@DeleteMapping("/delete-trainer/{trainerId}")
	public String deleteTrainer(@PathVariable("trainerId") int trainerId) throws TrainerNotFoundException {
		logger.info("Delete trainer  (Service) - START");
		logger.info("Deleting Trainer");
		service.deleteTrainer(trainerId);
		logger.info("Delete trainer  (Service) - END");
		return "Trainer Deleted!";
	}

	/**
	 * This method displays the specified Trainer
	 * 
	 * @param trainerId
	 * @return Trainer 
	 * @throws TrainerNotFoundException
	 */
	@GetMapping("/view-trainer/{trainerId}")
	public Trainer viewTrainer(@PathVariable("trainerId") int trainerId ) throws TrainerNotFoundException {
		logger.info("View Trainer (Service) -START");
		logger.info("Fetching Trainer");
		Trainer trainer = service.viewTrainer(trainerId);
		logger.info("View Trainer (Service) -END");
		return trainer;
	}

	/**
	 * This method displays all the Trainers
	 * 
	 * @return List of Trainers
	 * @throws ListEmptyException
	 */
	@GetMapping("/view-all-trainers")
	public List<Trainer> viewAllTrainers() throws ListEmptyException {
		logger.info("View All Trainers  (Service) -START");
		logger.info("Fetching Trainers");
		List<Trainer> trainerList = service.viewAllTrainers();
		logger.info("View All Trainers  (Service) -END");
		return trainerList;
	}

	/**
	 * This method displays study material for each trainer specified
	 * 
	 * @param trainerId
	 * @return List of Study Materials
	 * @throws ListEmptyException
	 */
	@GetMapping("/view-study-material/{trainerId}")
	public List<StudyMaterial> viewStudyMaterial(@PathVariable("trainerId") int trainerId) throws ListEmptyException {
		logger.info("View Study Materials  (Service) -START!");
		logger.info("Fetching Study Materials");
		List<StudyMaterial> studyMaterial = service.viewStudyMaterial(trainerId);
		logger.info("View Study Materials  (Service) -END!");
		return studyMaterial;
	}

	/**
	 * This method adds Study Material for individual trainers
	 * 
	 * @param trainerId
	 * @param content
	 * @return Trainer 
	 * @throws TrainerNotFoundException
	 */
	@PatchMapping("/update-study-material")
	public Trainer updateTrainerForStudyMaterial(@RequestParam("trainerId") int trainerId,
			@RequestParam("content") String content) throws TrainerNotFoundException {
		logger.info("Update Trainer for Study Material  (Service) -START");
		logger.info("Adding Study Material");
		Trainer trainer = service.updateTrainerForStudyMaterial(trainerId, content);
		logger.info("Update Trainer for Study Material  (Service) -END");
		return trainer;
	}
}
