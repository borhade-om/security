package com.batch.BatchProcessing.mapper;

import com.batch.BatchProcessing.dto.StateDto;
import com.batch.BatchProcessing.entity.State;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StateMapper {

    State toEntity(StateDto stateDto);

    StateDto toDto(State state);


    List<StateDto> toList_Dto(List<State> state);

}
