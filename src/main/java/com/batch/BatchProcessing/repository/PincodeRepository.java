package com.batch.BatchProcessing.repository;

import com.batch.BatchProcessing.entity.PinCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PincodeRepository extends JpaRepository<PinCode,Long> {

    PinCode findByPinCode(Long pincode);
}
