package com.mphasis.parent.controller;

import com.mphasis.parent.api.FileLoadController;
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.model.dto.FileLoadRequestDto;
import com.mphasis.parent.model.dto.SearchCriteriaDto;
import com.mphasis.parent.service.FileLoadServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileLoadControllerTest {

    @InjectMocks
    private FileLoadController fileLoadController; // Controller under test

    @Mock
    private FileLoadServiceImpl fileLoadService; // Mocked service

    private FileLoad fileLoad;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        // Sample FileLoad object for testing
        fileLoad = new FileLoad();
        fileLoad.setId(1L);
        fileLoad.setFilename("testFile.csv");
        fileLoad.setStatus("PENDING");
        fileLoad.setRecordCount(10);
    }

    @Test
    void testCreateFileLoad() {
        // Arrange
        FileLoadRequestDto requestDto = new FileLoadRequestDto();
        requestDto.setFilename("newFile.csv");
        requestDto.setStatus("PENDING");
        requestDto.setRecordCount(5);

        when(fileLoadService.createFileLoad(requestDto)).thenReturn(fileLoad);

        // Act
        ResponseEntity<FileLoad> response = fileLoadController.createFileLoad(requestDto);

        // Assert
        assertNotNull(response);
		/* assertEquals(200, response.getStatusCodeValue()); */
        assertEquals("testFile.csv", response.getBody().getFilename());
        verify(fileLoadService, times(1)).createFileLoad(requestDto);
    }

    @Test
    void testGetFileLoad() throws Exception {
        // Arrange
        when(fileLoadService.getFileLoadById(1L)).thenReturn(fileLoad);

        // Act
        ResponseEntity<FileLoad> response = fileLoadController.getFileById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(fileLoadService, times(1)).getFileLoadById(1L);
    }

    @Test
    void testSearchFileLoad() {
        // Arrange
        SearchCriteriaDto searchCriteria = new SearchCriteriaDto(); // Mock search criteria
        when(fileLoadService.searchFileLoads(searchCriteria)).thenReturn(Arrays.asList(fileLoad));

        // Act
        ResponseEntity<List<FileLoad>> response = fileLoadController.searchFileLoad(searchCriteria);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(fileLoadService, times(1)).searchFileLoads(searchCriteria);
    }

    @Test
    void testUpdateFileLoadStatus() {
        // Arrange
        when(fileLoadService.updateFileLoadStatus(1L, "COMPLETED")).thenReturn(fileLoad);

        // Act
        ResponseEntity<FileLoad> response = fileLoadController.updateFileLoadStatus(1L, "COMPLETED");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(fileLoadService, times(1)).updateFileLoadStatus(1L, "COMPLETED");
    }

    @Test
    void testArchiveFileLoad() {
        // Arrange
        doNothing().when(fileLoadService).archiveFileLoad(1L);

        // Act
        ResponseEntity<Void> response = fileLoadController.archiveFileLoad(1L);

        // Assert
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(fileLoadService, times(1)).archiveFileLoad(1L);
    }

    @Test
    void testDeleteFileLoad() {
        // Arrange
        doNothing().when(fileLoadService).deleteFileLoad(1L);

        // Act
        ResponseEntity<Void> response = fileLoadController.deleteFileLoad(1L);

        // Assert
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(fileLoadService, times(1)).deleteFileLoad(1L);
    }

	/*
	 * @Test void testGreet() { // Act String greeting = fileLoadController.greet();
	 * 
	 * // Assert assertNotNull(greeting); assertEquals("Helloooo", greeting); }
	 */
}
