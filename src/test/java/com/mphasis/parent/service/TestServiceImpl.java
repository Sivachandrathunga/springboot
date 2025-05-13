package com.mphasis.parent.service;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import com.mphasis.parent.dao.FileLoadRepository;
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.model.dto.FileLoadRequestDto;
import com.mphasis.parent.model.dto.SearchCriteriaDto;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.io.FileNotFoundException;
 
class TestServiceImpl {
 
    @Mock
    private FileLoadRepository fileLoadRepository;
 
    @InjectMocks
    private FileLoadServiceImpl fileLoadService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }
 
    @Test
    void testCreateFileLoad() {
        FileLoadRequestDto requestDto = new FileLoadRequestDto("testFile.csv", "IN_PROGRESS", 50);
        FileLoad expectedFileLoad = new FileLoad();
        expectedFileLoad.setFilename(requestDto.getFilename());
        expectedFileLoad.setStatus(requestDto.getStatus());
        expectedFileLoad.setRecordCount(requestDto.getRecordCount());
        expectedFileLoad.setLocaldate(LocalDate.now());
 
        when(fileLoadRepository.save(any(FileLoad.class))).thenReturn(expectedFileLoad);
 
        FileLoad createdFile = fileLoadService.createFileLoad(requestDto);
 
        assertNotNull(createdFile);
        assertEquals("testFile.csv", createdFile.getFilename());
        assertEquals("IN_PROGRESS", createdFile.getStatus());
        assertEquals(50, createdFile.getRecordCount());
    }
 
    @Test
    void testGetFileLoadById_Success() {
        FileLoad mockFile = new FileLoad(1L, "testFile.csv", "COMPLETED", 100, List.of("No Errors"), LocalDate.now());
        when(fileLoadRepository.findById(1L)).thenReturn(Optional.of(mockFile));
 
        FileLoad result = fileLoadService.getFileLoadById(1L);
 
        assertNotNull(result);
        assertEquals("testFile.csv", result.getFilename());
        assertEquals("COMPLETED", result.getStatus());
        assertEquals(100, result.getRecordCount());
    }
 
    @Test
    void testGetFileLoadById_NotFound() {
        when(fileLoadRepository.findById(2L)).thenReturn(Optional.empty());
 
        Exception exception = assertThrows(RuntimeException.class, () -> fileLoadService.getFileLoadById(2L));
        assertEquals("FileLoad not found with ID: 2", exception.getMessage());
    }
 
	/*
	 * @Test void testSearchFileLoads() { SearchCriteriaDto searchCriteria = new
	 * SearchCriteriaDto("testFile.csv", "COMPLETED", 25-11-2024);
	 *
	 * List<FileLoad> mockFiles = Arrays.asList( new FileLoad(1L, "file1.csv",
	 * "COMPLETED", 100, List.of("Error1"), LocalDate.now()), new FileLoad(2L,
	 * "file2.csv", "PENDING", 50, List.of("Error2", "Error3"), LocalDate.now()) );
	 *
	 * when(fileLoadRepository.findByCriteria(searchCriteria)).thenReturn(mockFiles)
	 * ;
	 *
	 * List<FileLoad> result = fileLoadService.searchFileLoads(searchCriteria);
	 *
	 * assertEquals(2, result.size()); assertEquals("file1.csv",
	 * result.get(0).getFileName()); assertEquals("file2.csv",
	 * result.get(1).getFileName()); }
	 */
 
    @Test
    void testUpdateFileLoadStatus() {
        FileLoad mockFile = new FileLoad(3L, "updateFile.csv", "PENDING", 75, List.of("Some Issues"), LocalDate.now());
        when(fileLoadRepository.findById(3L)).thenReturn(Optional.of(mockFile));
        when(fileLoadRepository.save(mockFile)).thenReturn(mockFile);
 
        FileLoad updatedFile = fileLoadService.updateFileLoadStatus(3L, "COMPLETED");
 
        assertEquals("COMPLETED", updatedFile.getStatus());
    }
 
    @Test
    void testArchiveFileLoad() {
        FileLoad mockFile = new FileLoad(4L, "archiveFile.csv", "PENDING", 200, List.of("Minor Issues"), LocalDate.now());
        when(fileLoadRepository.findById(4L)).thenReturn(Optional.of(mockFile));
        when(fileLoadRepository.save(mockFile)).thenReturn(mockFile);
 
        fileLoadService.archiveFileLoad(4L);
 
        assertEquals("Archived", mockFile.getStatus());
    }
 
    @Test
    void testDeleteFileLoad_Success() throws FileNotFoundException {
        when(fileLoadRepository.existsById(5L)).thenReturn(true);
        doNothing().when(fileLoadRepository).deleteById(5L);
 
        assertDoesNotThrow(() -> fileLoadService.deleteFileLoad(5L));
        verify(fileLoadRepository, times(1)).deleteById(5L);
    }
 
	/*
	 * @Test void testDeleteFileLoad_NotFound() {
	 * when(fileLoadRepository.existsById(6L)).thenReturn(false);
	 * 
	 * assertThrows(FileNotFoundException.class, () ->
	 * fileLoadService.deleteFileLoad(6L)); }
	 */

}
 
 