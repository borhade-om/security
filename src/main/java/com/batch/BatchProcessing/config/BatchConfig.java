package com.batch.BatchProcessing.config;

import com.batch.BatchProcessing.dto.PinCodeBulkDto;
import com.batch.BatchProcessing.entity.PinCode;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.yaml.snakeyaml.util.PlatformFeatureDetector;

@Component
public class BatchConfig {

    @Bean
    public Job jobConfig(JobRepository jobRepository, Step steps ,JobListener jobListener){
        return new JobBuilder("job",jobRepository)
                .listener(jobListener ).start(steps).build();
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     ItemReader<PinCodeBulkDto> reader,
                     ItemProcessor<PinCodeBulkDto, PinCode> processor,
                     ItemWriter<PinCode> writer){

        return new StepBuilder("jobSteps",jobRepository)
                .<PinCodeBulkDto, PinCode>  chunk(500,transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
