package com.training.educationsystem.services;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.exceptions.StudyMaterialException;
import com.training.educationsystem.repositories.StudyMaterialRepository;

/**
 * 
 * @author Anisha
 *
 */
@Transactional
@Service
public class IStudyMaterialService implements StudyMaterialService{
	private static final Logger logger = LoggerFactory.getLogger(IStudyMaterialService.class);
	
	@Autowired
	StudyMaterialRepository studyRepo;

	/**
	 * This method adds the study material details after enrollment
	 * @param studymaterial
	 * @return StudyMaterial
	 */
	@Override
	public StudyMaterial addStudyMaterial(StudyMaterial studymaterial) {
		logger.info("service adding study material details-start");
		studyRepo.save(studymaterial);
		logger.info("service adding study material details-start");
		return studymaterial;
	}
	
	/**
	 * This method deletes a study material object by the respective Id
	 * @param id
	 * @return void
	 */
	@Override
	public void deleteStudyMaterial(int id) throws StudyMaterialException{
		logger.info("service deleting study material details by id-start");
		if(studyRepo.existsById(id))
		{
			studyRepo.deleteById(id);
			logger.info("Study Material deleted successfully!");
			logger.info("service deleted study material - end");
		}
		else
		{
			logger.error("No such study material with the mentioned Id!");
			throw new StudyMaterialException("No such study material with the mentioned Id!");
		}
	}

	/**
	 * This method views all study material details in a list
	 * @throws StudyMaterialException
	 * @return List
	 */
	@Override
	public List<StudyMaterial> viewStudyMaterial() throws StudyMaterialException{
		logger.info("service viewing study material list - start");
		List<StudyMaterial> materialList = studyRepo.findAll();
		if(materialList.size() > 0 )
		{
			logger.info("service viewing study material list - end");
			return materialList;
		}
		else
		{
			logger.error("Study Material not found");
			throw new StudyMaterialException("Study Material not found");
		}
	}

	/**
	 * This method views study material details by respective Id
	 * @param id
	 * @throws StudyMaterialException
	 * @return StudyMaterial
	 */
	@Override
	public StudyMaterial getStudyMaterialById(int id) throws StudyMaterialException{
		logger.info("service viewing study material details by id - start");
		StudyMaterial studyMaterial = studyRepo.findById(id).orElse(null);
		if(studyMaterial!=null)
		{
			logger.info("service viewing study material details by id - end");
			return studyMaterial;
		}
		else
		{
			logger.error("Study Material not found");
			throw new StudyMaterialException("Study Material not found");
		}
		
		
	}
}
