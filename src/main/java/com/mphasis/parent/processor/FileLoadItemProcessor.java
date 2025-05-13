package com.mphasis.parent.processor;
 
import org.springframework.batch.item.ItemProcessor;

import com.mphasis.parent.entity.FileLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
	
	@Component
	public class FileLoadItemProcessor implements ItemProcessor<FileLoad, FileLoad> {
	 
	        @Autowired
	        private JdbcTemplate jdbcTemplate;
	        
	        @Override
	        public FileLoad process(FileLoad item) throws Exception {
	            String sql = "SELECT COUNT(*) FROM file_load WHERE fileName = ?";
	            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, item.getFilename());
	 
	            // Log the processing logic for debugging
	            System.out.println("Processing record with fileName: " + item.getFilename() + " | Count result: " + count);
	 
	            if (count != null && count > 0) {
	                // Skip already processed records
	                System.out.println("Skipping record with fileName: " + item.getFilename());
	                return null;
	            }
	 
	            // Process the new record
	            System.out.println("Processing new record with fileName: " + item.getFilename());
	            return item;
	        }
	    }

 

 