package com.mphasis.parent.service;
 
import java.time.LocalDate;
import java.util.List;
 
import org.springframework.stereotype.Service;
 
import com.mphasis.parent.dao.FileLoadRepository;
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.model.dto.FileLoadRequestDto;
import com.mphasis.parent.model.dto.SearchCriteriaDto;
 
 
 
 
 
 
@Service
public class FileLoadServiceImpl implements FileLoadService{
	
 
	
	private FileLoadRepository Filerepo;
	
	public FileLoadServiceImpl(FileLoadRepository Filerepo) {
		this.Filerepo=Filerepo;
	}
	
	@Override
	public FileLoad createFileLoad(FileLoadRequestDto load) {
		FileLoad fileload = new FileLoad();
		fileload.setFilename(load.getFilename());
		fileload.setStatus(load.getStatus());
		fileload.setRecordCount(load.getRecordCount());
		fileload.setLocaldate(LocalDate.now());
		return Filerepo.save(fileload);	
	}
	
	@Override
	public FileLoad getFileLoadById(Long id) {
		return Filerepo.findById(id).orElseThrow(() -> new RuntimeException("FileLoad not found with ID: " + id));
	}
	
	@Override
	public List<FileLoad> searchFileLoads(SearchCriteriaDto search) {
	    return Filerepo.findByCriteria(search.getFilename(), search.getStatus());  // ✅ Extract individual fields
	}


	
	@Override
	public FileLoad updateFileLoadStatus(Long id, String status) {
		FileLoad load = Filerepo.findById(id).orElseThrow(null);
		load.setStatus(status);
		return Filerepo.save(load);
	}
	
	@Override
	public void archiveFileLoad(Long id) {
		FileLoad load = Filerepo.findById(id).orElseThrow(null);
		load.setStatus("Archived");
		Filerepo.save(load);
	}
	
	@Override
	public void deleteFileLoad(Long id) {
		Filerepo.deleteById(id);
	}

	@Override
	public List<FileLoad> getAllFiles() {
		
		return null;
	}
	
}