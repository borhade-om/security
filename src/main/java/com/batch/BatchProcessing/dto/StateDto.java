package com.batch.BatchProcessing.dto;

public class StateDto {

    private String stateName;

    public StateDto(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public StateDto() {
    }
}
