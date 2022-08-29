package com.li.frame.spring.enums;

public enum DeviceBizCodeEnum {

    IOT_METER("物联网表","iotmeter","物联网表"),
    METER("普通表","meter","普表，IC卡表"),
    ALARM("报警器","alarm","报警器"),
    GATEWAY("网关设备","gateway","集中器，管理机，采集器"),
    VALVE("智能阀门","valve","智能阀门"),
    ;

    private String name;
    private String code;
    private String description;

    DeviceBizCodeEnum(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
