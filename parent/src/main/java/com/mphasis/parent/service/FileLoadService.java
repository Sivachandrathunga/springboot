package com.mphasis.parent.service;
 
import java.util.List;
 
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.model.dto.FileLoadRequestDto;
import com.mphasis.parent.model.dto.SearchCriteriaDto;
 
public interface FileLoadService {
	
	 FileLoad createFileLoad(FileLoadRequestDto req);
	 void deleteFileLoad(Long id);
	 List<FileLoad> searchFileLoads(SearchCriteriaDto search);
	 FileLoad updateFileLoadStatus(Long id, String status);
	 void archiveFileLoad(Long id);
	 FileLoad getFileLoadById(Long id);
}