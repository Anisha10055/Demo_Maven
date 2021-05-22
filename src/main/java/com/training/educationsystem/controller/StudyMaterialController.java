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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.exception.ErrorMessage;
import com.training.educationsystem.exceptions.InvalidStudyMaterialException;
import com.training.educationsystem.exceptions.StudyMaterialException;
import com.training.educationsystem.services.IStudyMaterialService;



/**
 * 
 * @author Anisha
 *
 */
@RestController
@RequestMapping("/api/educationsystem")
public class StudyMaterialController {
	private static final Logger logger = LoggerFactory.getLogger(StudyMaterialController.class);
	@Autowired
	public IStudyMaterialService studymaterialService;

	/**
	 * This method adds the study material details after enrollment
	 * 
	 * @param studymaterial
	 * @return StudyMaterial
	 */
	@PostMapping(value = "/add-Studymaterial")
	public StudyMaterial addStudyMaterial(@RequestBody StudyMaterial studymaterial)
			throws InvalidStudyMaterialException {
		logger.info("adding study material details - start");

		String materialPattern = "[a-zA-Z0-9\s]+";
		if (!(Pattern.matches(materialPattern, studymaterial.getContent()))) {
			logger.error("Checking for content type");
			throw new InvalidStudyMaterialException(
					"Content should not be null and should contain alphanumeric values only");
		} else {
			StudyMaterial addStud = studymaterialService.addStudyMaterial(studymaterial);
			logger.info("adding study material details - end");
			return addStud;

		}

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidStudyMaterialException.class)
	ErrorMessage exceptionHandler(InvalidStudyMaterialException e) {
		return new ErrorMessage("400", e.str);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(StudyMaterialException.class)
	ErrorMessage exceptionHandler(StudyMaterialException e) {
		return new ErrorMessage("404", e.str);
	}

	/**
	 * This method views study material details by respective Id
	 * 
	 * @param id
	 * @return StudyMaterial
	 */
	@GetMapping(value = "/get-Material/{materialId}")
	public StudyMaterial getStudyMaterialById(@PathVariable("materialId") int id) throws StudyMaterialException {
		logger.info("viewing study material details by id - start");
		StudyMaterial getStudyMaterial = studymaterialService.getStudyMaterialById(id);
		logger.info("viewing study material details by id - end");
		return getStudyMaterial;
	}

	/**
	 * This method views all study material detals as a list
	 * @return List
	 * @throws StudyMaterialException
	 */
	@GetMapping(value = "/view-Studymaterial")
	public List<StudyMaterial> viewStudyMaterial() throws StudyMaterialException {
		logger.info("viewing study material list - start");
		List<StudyMaterial> getStudyMaterialList = studymaterialService.viewStudyMaterial();
		logger.info("viewing study material list - end");
		return getStudyMaterialList;
	}

	/**
	 * This method deletes a study material object by the respective Id
	 * 
	 * @param id
	 * @return String Message
	 * @throws StudyMaterialException 
	 */
	@DeleteMapping(value = "/remove-Studymaterial/{materialId}")
	public String deleteStudyMaterial(@PathVariable("materialId") int id) throws StudyMaterialException {
		logger.info("deleting study material details by id - start");
		studymaterialService.deleteStudyMaterial(id);
		logger.info("deleting study material details by id - end");
		return "Study Material removes!";
	}


}
