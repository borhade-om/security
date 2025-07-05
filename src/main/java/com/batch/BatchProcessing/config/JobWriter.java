package com.batch.BatchProcessing.config;

import com.batch.BatchProcessing.entity.PinCode;
import com.batch.BatchProcessing.repository.PincodeRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class JobWriter implements ItemWriter<PinCode> {
    private PincodeRepository pincodeRepository;
    @Override
    public void write(Chunk<? extends PinCode> chunk) throws Exception {
        pincodeRepository.saveAll(chunk);
    }
}
