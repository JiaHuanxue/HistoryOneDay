package com.result.homepage.bean;

/**
 * Created by 贾焕雪 on 2016-12-19.
 */
public class FirstEvent {

    private String eYear;
    private String eMonth;
    private String eDay;

    public FirstEvent(String eYear, String eMonth, String eDay) {
        this.eYear = eYear;
        this.eMonth = eMonth;
        this.eDay = eDay;
    }

    public String geteYear() {
        return eYear;
    }

    public String geteMonth() {
        return eMonth;
    }

    public String geteDay() {
        return eDay;
    }
}
