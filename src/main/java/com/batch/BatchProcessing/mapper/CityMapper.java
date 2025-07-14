package com.batch.BatchProcessing.mapper;

import com.batch.BatchProcessing.dto.CityDto;
import com.batch.BatchProcessing.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    City toEntity(CityDto cityDto);

    CityDto toDto(City city);
}
