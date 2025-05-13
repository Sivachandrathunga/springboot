package com.mphasis.parent.dao;

import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.model.dto.SearchCriteriaDto;
 
 
@Repository
public interface FileLoadRepository extends JpaRepository<FileLoad, Long>{
	
	@Query("SELECT f FROM FileLoad f WHERE f.Filename LIKE %:filename% AND f.status = :status")
    List<FileLoad> findByCriteria(@Param("filename") String filename, @Param("status") String status);
		
}