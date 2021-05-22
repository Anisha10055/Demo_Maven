package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.educationsystem.entities.Progress;

public interface ProgressRepository extends JpaRepository<Progress,Integer>{

}
