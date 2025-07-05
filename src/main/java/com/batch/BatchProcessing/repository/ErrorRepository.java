package com.batch.BatchProcessing.repository;

import com.batch.BatchProcessing.entity.ErrorTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<ErrorTable,Long> {

}
