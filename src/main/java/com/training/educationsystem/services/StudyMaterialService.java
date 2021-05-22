package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.exceptions.StudyMaterialException;

public interface StudyMaterialService {
	public StudyMaterial addStudyMaterial(StudyMaterial studymaterial);
	public StudyMaterial getStudyMaterialById(int id) throws StudyMaterialException;
	public List<StudyMaterial> viewStudyMaterial() throws StudyMaterialException;
	public void deleteStudyMaterial(int id) throws StudyMaterialException;

}
