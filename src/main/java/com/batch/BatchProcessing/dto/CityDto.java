package com.batch.BatchProcessing.dto;

public class CityDto {

    private String cityName;

    private String stateName;

    public CityDto(String cityName, String stateName) {
        this.cityName = cityName;
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public CityDto() {
    }
}
