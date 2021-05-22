package com.training.educationsystem.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.controller.CourseController;
import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exception.ListEmptyException;
import com.training.educationsystem.exception.TrainerNotFoundException;
import com.training.educationsystem.repositories.StudyMaterialRepository;
import com.training.educationsystem.repositories.TrainerRepository;

/**
 * This is Service class for Trainer module
 * 
 * @author Afeeda A.H
 *
 */
@Transactional
@Service
public class TrainerService implements ITrainerService {

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	TrainerRepository trainerRepo;

	@Autowired
	StudyMaterialRepository studyRepo;

	/**
	 * This method adds Trainer in the System
	 * 
	 * @param trainer
	 * @return Trainer
	 */
	@Override
	public Trainer addTrainer(Trainer trainer) {
		logger.info("Add trainer  (Service) - START");
		trainerRepo.save(trainer);
		logger.info("Trainer Added Successfully!");
		logger.info("Add trainer  (Service) - END");
		return trainer;
	}

	/**
	 * This method deletes each Trainer from the system
	 * 
	 * @param id
	 * @return Nothing
	 * @throws TrainerNotFoundException
	 */
	@Override
	public void deleteTrainer(int id) throws TrainerNotFoundException {
		logger.info("Delete trainer  (Service) - START");
		if (trainerRepo.existsById(id)) {
			trainerRepo.deleteById(id);
			logger.info("Trainer Deleted Successfully!");
			logger.info("Delete trainer  (Service) - END");
		} else {
			logger.error("Trainer cannot be deletes as this trainer is not found!");
			throw new TrainerNotFoundException("Trainer cannot be deletes as this trainer is not found!");
		}
	}

	/**
	 * This method displays the specified Trainer
	 * 
	 * @param trainerId
	 * @return Trainer
	 * @throws TrainerNotFoundException
	 */
	@Override
	public Trainer viewTrainer(int trainerId) throws TrainerNotFoundException {
		logger.info("View Trainer (Service) -START");
		Trainer trainer = trainerRepo.findById(trainerId).orElse(null);
		if (trainer != null) {
			logger.info("Displaying Trainer!");
			logger.info("View Trainer (Service) -END");
			return trainer;
		} else {
			logger.error("Trainer cannot be found!");
			throw new TrainerNotFoundException("Trainer cannot be found!");
		}

	}

	/**
	 * This method displays all the Trainers
	 * 
	 * @return List of Trainers
	 * @throws ListEmptyException
	 */
	@Override
	public List<Trainer> viewAllTrainers() throws ListEmptyException {
		logger.info("View All Trainers  (Service) -START");
		List<Trainer> trainerList = trainerRepo.findAll();
		if (trainerList.size() > 0) {
			logger.info("Displaying Trainers!");
			logger.info("View All Trainers  (Service) -END");
			return trainerList;
		} else {
			logger.error("No Trainers to show!");
			throw new ListEmptyException("No Trainers to show!");
		}

	}

	/**
	 * This method adds Study Material for individual trainers
	 * 
	 * @param trainerId
	 * @param content
	 * @return Trainer
	 * @throws TrainerNotFoundException
	 */
	@Override
	public Trainer updateTrainerForStudyMaterial(int trainerId, String content) throws TrainerNotFoundException {
		logger.info("Update Trainer for Study Material  (Service) -START");
		Trainer trainer = trainerRepo.findById(trainerId).orElse(null);
		StudyMaterial studyMaterial = new StudyMaterial();
		List<StudyMaterial> studyMaterialList = new ArrayList<StudyMaterial>();
		if (trainer != null) {
			studyMaterialList.add(studyMaterial);
			studyMaterial.setContent(content);
			studyRepo.save(studyMaterial);
			trainer.setStudyMaterial(studyMaterialList);
			trainerRepo.save(trainer);
			logger.info("Study Material Added Successfully!");
			logger.info("Update Trainer for Study Material  (Service) -END");
			return trainer;
		} else {
			logger.error("Study Material cannot be added as Trainer cannot be found!");
			throw new TrainerNotFoundException("Study Material cannot be added as Trainer cannot be found!");
		}

	}

	/**
	 * This method displays study material for each trainer specified
	 * 
	 * @param trainerId
	 * @return List of Study Materials
	 * @throws ListEmptyException
	 */
	@Override
	public List<StudyMaterial> viewStudyMaterial(int trainerId) throws ListEmptyException {
		logger.info("View Study Materials  (Service) -START!");
		Trainer trainer = trainerRepo.getOne(trainerId);
		List<StudyMaterial> studyMaterialList = trainer.getStudyMaterial();
		if (studyMaterialList.size() > 0) {
			logger.info("Displaying Study Materials!");
			logger.info("View Study Materials  (Service) -END!");
			return studyMaterialList;
		} else {
			logger.error("No Study Materials to show!");
			throw new ListEmptyException("No Study Materials to show!");
		}

	}

}
