package com.batch.BatchProcessing.mapper;

import com.batch.BatchProcessing.dto.PinCodeBulkDto;
import com.batch.BatchProcessing.dto.PinCodeDto;
import com.batch.BatchProcessing.entity.PinCode;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-17T12:58:40+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class PinCodeMapperImpl implements PinCodeMapper {

    @Override
    public PinCode toEntity(PinCodeBulkDto pinCodeBulkDto) {
        if ( pinCodeBulkDto == null ) {
            return null;
        }

        PinCode pinCode = new PinCode();

        return pinCode;
    }

    @Override
    public PinCodeBulkDto toDto(PinCode pinCode) {
        if ( pinCode == null ) {
            return null;
        }

        PinCodeBulkDto pinCodeBulkDto = new PinCodeBulkDto();

        return pinCodeBulkDto;
    }

    @Override
    public PinCode toPinCode(PinCodeDto pinCodeDto) {
        if ( pinCodeDto == null ) {
            return null;
        }

        PinCode pinCode = new PinCode();

        pinCode.setPinCode( pinCodeDto.getPinCode() );

        return pinCode;
    }

    @Override
    public PinCodeDto toPinDto(PinCode pinCode) {
        if ( pinCode == null ) {
            return null;
        }

        PinCodeDto pinCodeDto = new PinCodeDto();

        pinCodeDto.setPinCode( pinCode.getPinCode() );

        return pinCodeDto;
    }
}
