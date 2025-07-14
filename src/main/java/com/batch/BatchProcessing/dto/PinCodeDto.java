package com.batch.BatchProcessing.dto;

public class PinCodeDto {

    private Long  pinCode;

    private String cityName;

    private String stateName;

    public PinCodeDto(Long pinCode, String cityName, String stateName) {
        this.pinCode = pinCode;
        this.cityName = cityName;
        this.stateName = stateName;
    }

    public PinCodeDto() {
    }

    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
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
}
