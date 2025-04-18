package com.mphasis.parent.api;

import java.io.FileNotFoundException;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.exception.FileLoadException;
import com.mphasis.parent.model.dto.FileLoadRequestDto;
import com.mphasis.parent.model.dto.SearchCriteriaDto;
import com.mphasis.parent.service.FileLoadServiceImpl;
 
 
@RestController
@RequestMapping("/api/file-loads")
public class FileLoadController {

	private final FileLoadServiceImpl service;
	@Autowired
	public FileLoadController(FileLoadServiceImpl service) {
		this.service=service;
		}
 
 
	@PostMapping("/create")
	public ResponseEntity<FileLoad> createFileLoad(@RequestBody FileLoadRequestDto request){
		FileLoad load=service.createFileLoad(request);
		return ResponseEntity.ok(load);
	}
	@GetMapping("/file/{id}")
	public ResponseEntity<FileLoad> getFileLoad(@PathVariable Long id) throws FileNotFoundException {
	    try {
	        FileLoad load = service.getFileLoadById(id);
	        return ResponseEntity.ok(load);
	    } catch (RuntimeException e) {
	        throw new FileNotFoundException("File not found with ID: " + id);
	    }
	}
	@GetMapping("/files")
	public ResponseEntity<List<FileLoad>> searchFileLoad(@RequestBody SearchCriteriaDto search){
		List<FileLoad> load=service.searchFileLoads(search);
		return ResponseEntity.ok(load);
	}
	  @PutMapping("/{id}/update")
	    public ResponseEntity<FileLoad> updateFileLoadStatus(@PathVariable Long id, @RequestParam String status) {
	        FileLoad fileLoad = service.updateFileLoadStatus(id, status);
	        return ResponseEntity.ok(fileLoad);
	    }
	  @PostMapping("/{id}/archive")
	    public ResponseEntity<Void> archiveFileLoad(@PathVariable Long id) {
	        service.archiveFileLoad(id);
	        return ResponseEntity.noContent().build();
	  }
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<Void> deleteFileLoad(@PathVariable Long id) {
	      try {
	          service.deleteFileLoad(id);
	          return ResponseEntity.noContent().build();
	      } catch (FileLoadException e) {
	          throw new FileLoadException(e.getMessage());
	      }
	  }
	  @GetMapping("/hello")
	  public String greet() {				
		  return "Helloooo";
	  }
}