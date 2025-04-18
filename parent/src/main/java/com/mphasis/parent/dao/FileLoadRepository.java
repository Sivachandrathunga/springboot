package com.mphasis.parent.dao;

import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.model.dto.SearchCriteriaDto;
 
 
@Repository
public interface FileLoadRepository extends JpaRepository<FileLoad, Long>{
	
		public List<FileLoad> findByCriteria(SearchCriteriaDto searchCriteria);
}