package com.batch.BatchProcessing.service;

import com.batch.BatchProcessing.dto.PinCodeDto;
import com.batch.BatchProcessing.entity.City;
import com.batch.BatchProcessing.entity.PinCode;
import com.batch.BatchProcessing.entity.State;
import com.batch.BatchProcessing.mapper.PinCodeMapper;
import com.batch.BatchProcessing.repository.CityRepository;
import com.batch.BatchProcessing.repository.PincodeRepository;
import com.batch.BatchProcessing.repository.StateRepository;
import org.springframework.stereotype.Service;

@Service
public class PincodeService {

    private PinCodeMapper   pinCodeMapper;

    private  PincodeRepository pincodeRepository;

    private StateRepository stateRepository;

    private CityRepository cityRepository;

    public PincodeService(PinCodeMapper pinCodeMapper, PincodeRepository pincodeRepository, StateRepository stateRepository, CityRepository cityRepository) {
        this.pinCodeMapper = pinCodeMapper;
        this.pincodeRepository = pincodeRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
    }

    public String savePinCodeData(PinCodeDto pinCodeDto) {
        State byState = stateRepository.findByStateNameIgnoreCase(pinCodeDto.getStateName());
           if (byState==null){
               throw new IllegalArgumentException("State Name Not found");
           }
        City byCity = cityRepository.findByCityNameIgnoreCase(pinCodeDto.getCityName());
           if (byCity==null){
               throw new IllegalArgumentException("City Name Not found");
           }

        PinCode pinCode = pinCodeMapper.toPinCode(pinCodeDto);
           pinCode.setStates(byState);
           pinCode.setCity(byCity);
           pincodeRepository.save(pinCode);

        return "data inserted successfully";

    }
}
