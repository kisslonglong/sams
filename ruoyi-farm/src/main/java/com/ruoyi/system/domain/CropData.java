package com.ruoyi.system.domain;

import java.util.Date;

public class CropData {


    private Long year;//年份


    private Long wheat;//小麦

    private Long cotton;//棉花

    private Long corn; //玉米

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getWheat() {
        return wheat;
    }

    public void setWheat(Long wheat) {
        this.wheat = wheat;
    }

    public Long getCotton() {
        return cotton;
    }

    public void setCotton(Long cotton) {
        this.cotton = cotton;
    }

    public Long getCorn() {
        return corn;
    }

    public void setCorn(Long corn) {
        this.corn = corn;
    }
}
