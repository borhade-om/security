package com.batch.BatchProcessing.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener implements JobExecutionListener {
    private Logger loggers= LoggerFactory.getLogger(JobListener.class);
    @Override
    public void beforeJob(JobExecution jobExecution) {
        loggers.info("excel import started");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
      loggers.info("excel import completed");
    }
}
