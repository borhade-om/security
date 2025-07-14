package com.batch.BatchProcessing.service;

import com.batch.BatchProcessing.dto.StateDto;
import com.batch.BatchProcessing.entity.State;
import com.batch.BatchProcessing.mapper.StateMapper;
import com.batch.BatchProcessing.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    private StateRepository stateRepository;


    private StateMapper stateMapper;

    public StateService(StateRepository stateRepository, StateMapper stateMapper) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
    }

    public String insertState(StateDto stateDto) {
        State entity = stateMapper.toEntity(stateDto);
        stateRepository.save(entity);
        return "data inserted successfully";
    }


    public List<StateDto> findAllState() {
        List<State> all = stateRepository.findAll();
        return stateMapper.toList_Dto(all);
    }
}
