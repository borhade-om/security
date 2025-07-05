package com.batch.BatchProcessing.entity;

import com.batch.BatchProcessing.enums.CityStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "city")
@SQLDelete(sql="update city set city_status='INACTIVE' , deletedAt=now() where city_id=?")
public class City {
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long cityId;

    @Column(name = "city_name")
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnore
    private State state;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<PinCode> pinCode;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "city_status")
    @Enumerated(EnumType.STRING)
    private CityStatus cityStatus=CityStatus.ACTIVE;

    public City(Long cityId, String cityName, State state, LocalDateTime createdAt, LocalDateTime deletedAt, LocalDateTime updatedAt, CityStatus cityStatus) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.state = state;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.updatedAt = updatedAt;
        this.cityStatus = cityStatus;
    }

    public City() {
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public CityStatus getCityStatus() {
        return cityStatus;
    }

    public void setCityStatus(CityStatus cityStatus) {
        this.cityStatus = cityStatus;
    }
}
