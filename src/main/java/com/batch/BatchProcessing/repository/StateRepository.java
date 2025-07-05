package com.batch.BatchProcessing.repository;

import com.batch.BatchProcessing.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {
    State findByStateNameIgnoreCase(String stateName);
}
