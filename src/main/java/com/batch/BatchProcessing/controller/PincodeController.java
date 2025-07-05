package com.batch.BatchProcessing.controller;

import com.batch.BatchProcessing.service.PincodeService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class PincodeController {

    private PincodeService pincodeService;

    private JobLauncher jobLauncher;

    private Job PincodeJob;

    public PincodeController(PincodeService pincodeService, JobLauncher jobLauncher, Job pincodeJob) {
        this.pincodeService = pincodeService;
        this.jobLauncher = jobLauncher;
        PincodeJob = pincodeJob;
    }

    @PostMapping(value = "pincode/import", consumes = "multipart/form-data")
    public String importData(@RequestParam("file") MultipartFile file) throws Exception {
        File tempfile = File.createTempFile("product", ".xlsx");
        file.transferTo(tempfile);
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", tempfile.getAbsolutePath())
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(PincodeJob, jobParameters);
        return null;
    }
}
