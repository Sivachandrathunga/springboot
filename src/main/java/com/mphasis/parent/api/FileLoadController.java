package com.mphasis.parent.api;

import java.io.FileNotFoundException;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.mphasis.parent.model.dto.FileLoadRequestDto;
import com.mphasis.parent.model.dto.SearchCriteriaDto;
import com.mphasis.parent.service.FileLoadServiceImpl;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/file-loads")
public class FileLoadController {

    private static final Logger logger = LoggerFactory.getLogger(FileLoadController.class);
    private final FileLoadServiceImpl service;

    @Autowired
    public FileLoadController(FileLoadServiceImpl service) {
        this.service = service;
    }

    
    @PostMapping("/create")
    public ResponseEntity<FileLoad> createFileLoad(@RequestBody FileLoadRequestDto request) {
        logger.info("Creating new file load");
        FileLoad load = service.createFileLoad(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(load);
    }

    
    @GetMapping("/files")
    public ResponseEntity<List<FileLoad>> getFiles() {
        List<FileLoad> files = service.getAllFiles(); 
        return ResponseEntity.ok(files);
    }

    
    @GetMapping("/{id}")  
    public ResponseEntity<FileLoad> getFileById(@PathVariable Long id) {
        FileLoad file = service.getFileLoadById(id);
        return file != null ? ResponseEntity.ok(file) :
                              ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }



    
    @PostMapping("/search")
    public ResponseEntity<List<FileLoad>> searchFileLoad(@RequestBody SearchCriteriaDto search) {
        logger.info("Searching file loads with criteria: {}", search);
        List<FileLoad> loads = service.searchFileLoads(search);
        return ResponseEntity.ok(loads);
    }

   
    @PutMapping("/update/{id}")
    public ResponseEntity<FileLoad> updateFileLoadStatus(@PathVariable Long id, @RequestParam String status) {
        FileLoad fileLoad = service.updateFileLoadStatus(id, status);
        if (fileLoad != null) {
            return ResponseEntity.ok(fileLoad);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
    @PostMapping("/{id}/archive")
    public ResponseEntity<Void> archiveFileLoad(@PathVariable Long id) {
        logger.info("Archiving file load with ID: {}", id);
        service.archiveFileLoad(id);
        return ResponseEntity.noContent().build();
    }

    
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteFileLoad(@PathVariable Long id) {
        logger.info("Deleting file load with ID: {}", id);
        service.deleteFileLoad(id);
        return ResponseEntity.noContent().build();
    }

   
    @GetMapping("/hello")
    public String greet() {
        return "Hello from FileLoadController!";
    }

    
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

