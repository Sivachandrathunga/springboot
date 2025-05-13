package com.mphasis.parent.configuration;


import org.springframework.batch.core.Job;

import org.springframework.batch.core.Step;

import org.springframework.batch.core.job.builder.JobBuilder;

import org.springframework.batch.core.repository.JobRepository;

import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.batch.item.ItemProcessor;

import org.springframework.batch.item.ItemReader;

import org.springframework.batch.item.ItemWriter;

import org.springframework.batch.item.file.FlatFileItemReader;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

import org.springframework.batch.item.file.mapping.DefaultLineMapper;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ClassPathResource;

import org.springframework.transaction.PlatformTransactionManager;

//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;

import com.mphasis.parent.dao.FileLoadRepository;
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.processor.FileLoadItemProcessor;
 


@Configuration

public class BatchJobConfig {

    private final JobRepository jobRepository;

    private final PlatformTransactionManager transactionManager;

    private final FileLoadRepository fileLoadRepository;
    
    //private final FileLoadItemProcessor fileloadcontroller;

    public BatchJobConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager, FileLoadRepository fileLoadRepository) {

        this.jobRepository = jobRepository;

        this.transactionManager = transactionManager;

        this.fileLoadRepository = fileLoadRepository;

    }

    @Bean

    public Job fileLoadJob(Step fileLoadStep) {

        return new JobBuilder("fileLoadJob", jobRepository)

                .start(fileLoadStep)

                .build();

    }

	/*
	 * @Bean public Job importUserJob(JobBuilderFactory jobBuilderFactory, Step
	 * step1) { return jobBuilderFactory.get("importUserJob") .start(step1)
	 * .build(); }
	 */
    
    @Bean

    public Step fileLoadStep(ItemReader<FileLoad> reader, ItemWriter<FileLoad> writer,ItemProcessor<FileLoad,FileLoad> processor) {

        return new StepBuilder("fileLoadStep", jobRepository)

                .<FileLoad, FileLoad>chunk(10, transactionManager)

                .reader(reader)

                .processor(processor)

                .writer(writer)

                .build();

    }

    @Bean

    public FlatFileItemReader<FileLoad> reader() {

        FlatFileItemReader<FileLoad> reader = new FlatFileItemReader<>();

        ClassPathResource resource = new ClassPathResource("fileload_data.csv");

        if (!resource.exists()) {

            throw new RuntimeException("File not found: " + resource.getFilename());

        }

        reader.setResource(resource);

        reader.setLinesToSkip(1); 

        reader.setLineMapper(new DefaultLineMapper<FileLoad>() {{

            setLineTokenizer(new DelimitedLineTokenizer() {{

                setNames("fileName", "status", "recordCount", "errors");

            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{

                setTargetType(FileLoad.class);

            }});

        }});

        return reader;

    }

    @Bean

   	public ItemProcessor<FileLoad, FileLoad> processor() {

   		return new FileLoadItemProcessor();

   	}

    @Bean

    public ItemWriter<FileLoad> writer() {

        return fileLoads -> fileLoadRepository.saveAll(fileLoads);

    }


}

 