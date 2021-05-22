package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exception.ListEmptyException;
import com.training.educationsystem.exception.TrainerNotFoundException;

public interface ITrainerService {
		public Trainer addTrainer(Trainer trainer);
		public void deleteTrainer(int id) throws TrainerNotFoundException ;
		public Trainer viewTrainer(int id) throws TrainerNotFoundException;
		public List<Trainer> viewAllTrainers()throws ListEmptyException;
		public Trainer updateTrainerForStudyMaterial(int tid,String content) throws TrainerNotFoundException;
		public List<StudyMaterial> viewStudyMaterial(int tid)throws ListEmptyException;
}
