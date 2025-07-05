package com.batch.BatchProcessing.dto;

public class PinCodeBulkDto {

    private Long pincode;

    private String StateName;

    private String CityName;

    public PinCodeBulkDto(Long pincode, String stateName, String cityName) {
        this.pincode = pincode;
        StateName = stateName;
        CityName = cityName;
    }

    public PinCodeBulkDto() {
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }
}
