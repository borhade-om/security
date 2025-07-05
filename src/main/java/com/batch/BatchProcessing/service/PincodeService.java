package com.batch.BatchProcessing.service;

import com.batch.BatchProcessing.repository.CityRepository;
import com.batch.BatchProcessing.repository.PincodeRepository;
import com.batch.BatchProcessing.repository.StateRepository;
import org.springframework.stereotype.Service;

@Service
public class PincodeService {

    private  PincodeRepository pincodeRepository;

    private StateRepository stateRepository;

    private CityRepository cityRepository;

    public PincodeService(PincodeRepository pincodeRepository, StateRepository stateRepository, CityRepository cityRepository) {
        this.pincodeRepository = pincodeRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
    }


}
