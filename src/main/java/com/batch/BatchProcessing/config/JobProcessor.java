package com.batch.BatchProcessing.config;

import com.batch.BatchProcessing.dto.PinCodeBulkDto;
import com.batch.BatchProcessing.entity.City;
import com.batch.BatchProcessing.entity.PinCode;
import com.batch.BatchProcessing.entity.State;
import com.batch.BatchProcessing.repository.CityRepository;
import com.batch.BatchProcessing.repository.PincodeRepository;
import com.batch.BatchProcessing.repository.StateRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class JobProcessor implements ItemProcessor<PinCodeBulkDto, PinCode> {
    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private PincodeRepository pincodeRepository;

    public JobProcessor(StateRepository stateRepository, CityRepository cityRepository, PincodeRepository pincodeRepository) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.pincodeRepository = pincodeRepository;
    }

    @Override
    public PinCode process(PinCodeBulkDto  dto) throws Exception {

        State state=stateRepository.findByStateNameIgnoreCase(dto.getStateName());
        if(state ==null){
            State states=new State();
            states.setStateName(dto.getStateName());
            stateRepository.save(states);
        }
        City city =cityRepository.findByCityNameIgnoreCase(dto.getCityName());
        if (city==null){
            City cities = new City();
            cities.setCityName(dto.getCityName());
            cities.setState(state);
            cityRepository.save(cities);
        }

       PinCode pinCode= pincodeRepository.findByPinCode(dto.getPincode());
        PinCode pinCodes=new PinCode();
        if(pinCode==null){
            pinCodes.setPinCode(dto.getPincode());
            pinCodes.setCity(city);
            pinCodes.setStates(state);
//            pincodeRepository.save(pinCodes);
        }
        return pinCodes;

    }
}
