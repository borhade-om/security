package com.batch.BatchProcessing.mapper;

import com.batch.BatchProcessing.dto.PinCodeBulkDto;
import com.batch.BatchProcessing.entity.PinCode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PinCodeMapper {

    PinCode toEntity(PinCodeBulkDto pinCodeBulkDto);

    PinCodeBulkDto toDto(PinCode pinCode);

}
