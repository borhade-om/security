package com.batch.BatchProcessing.entity;

import com.batch.BatchProcessing.enums.StateStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name ="state")
@SQLDelete(sql="update state set state_status='INACTIVE' ,deleted_at=now() where state_id=? ")
public class State {

    @Id
    @Column(name = "state_id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long stateId;

    @Column(name="state_name")
    private String stateName;

    @OneToMany(mappedBy = "states")
    @JsonIgnore
    private List<PinCode> pinCode;

    @OneToMany(mappedBy = "state")
    @JsonIgnore
    private List<City> city;

    @Column(name = "state_status")
    @Enumerated(EnumType.STRING)
    private StateStatus stateStatus =StateStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


    public State(Long stateId, String stateName, StateStatus stateStatus, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.stateId = stateId;
        this.stateName = stateName;
        this.stateStatus = stateStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public State() {
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public StateStatus getStateStatus() {
        return stateStatus;
    }

    public void setStateStatus(StateStatus stateStatus) {
        this.stateStatus = stateStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }


}
