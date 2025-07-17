package com.batch.BatchProcessing.mapper;

import com.batch.BatchProcessing.dto.StateDto;
import com.batch.BatchProcessing.entity.State;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-17T12:58:41+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class StateMapperImpl implements StateMapper {

    @Override
    public State toEntity(StateDto stateDto) {
        if ( stateDto == null ) {
            return null;
        }

        State state = new State();

        state.setStateName( stateDto.getStateName() );

        return state;
    }

    @Override
    public StateDto toDto(State state) {
        if ( state == null ) {
            return null;
        }

        StateDto stateDto = new StateDto();

        stateDto.setStateName( state.getStateName() );

        return stateDto;
    }

    @Override
    public List<StateDto> toList_Dto(List<State> state) {
        if ( state == null ) {
            return null;
        }

        List<StateDto> list = new ArrayList<StateDto>( state.size() );
        for ( State state1 : state ) {
            list.add( toDto( state1 ) );
        }

        return list;
    }
}
