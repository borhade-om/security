package com.batch.BatchProcessing.entity;

import com.batch.BatchProcessing.enums.PinCodeStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "pin_code")
@SQLDelete(sql="update pin_code set pin_code_status='INACTIVE' ,deleted_at=now() where pin_code_id=? ")
public class PinCode {

    @Id
    @Column(name = "pin_code_id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long pinCodeId;

    @Column(name = "pincode")
    private Long pinCode;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnore
    private State states;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name="pin_code_status")
    @Enumerated(EnumType.STRING)
    private PinCodeStatus pinCodeStatus=PinCodeStatus.ACTIVE;

    public PinCode(PinCodeStatus pinCodeStatus, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime createdAt, State states, City city, Long pinCode, Long pinCodeId) {
        this.pinCodeStatus = pinCodeStatus;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.states = states;
        this.city = city;
        this.pinCode = pinCode;
        this.pinCodeId = pinCodeId;
    }

    public PinCode() {
    }

    public Long getPinCodeId() {
        return pinCodeId;
    }

    public void setPinCodeId(Long pinCodeId) {
        this.pinCodeId = pinCodeId;
    }

    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getStates() {
        return states;
    }

    public void setStates(State states) {
        this.states = states;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PinCodeStatus getPinCodeStatus() {
        return pinCodeStatus;
    }

    public void setPinCodeStatus(PinCodeStatus pinCodeStatus) {
        this.pinCodeStatus = pinCodeStatus;
    }
}
