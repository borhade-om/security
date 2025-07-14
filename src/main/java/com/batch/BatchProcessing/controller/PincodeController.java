package com.batch.BatchProcessing.controller;

import com.batch.BatchProcessing.dto.PinCodeDto;
import com.batch.BatchProcessing.service.PincodeService;
import org.springframework.batch.core.*;

import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class PincodeController {

    private final PincodeService pincodeService;



    public PincodeController(PincodeService pincodeService) {
        this.pincodeService = pincodeService;

    }



    @GetMapping("/pincode/insert")
    public ResponseEntity<String> insertData(@RequestParam PinCodeDto pinCodeDto){
        return new ResponseEntity<String>(pincodeService.savePinCodeData(pinCodeDto), HttpStatus.CREATED);

    }
}
