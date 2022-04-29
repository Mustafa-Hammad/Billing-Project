/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.schema;

import java.util.Date;

/**
 *
 * @author nora
 */
public class CDR {

    private int cdrId;
    private String timeStamp;
    private Date startDate;
    private String dialA;
    private String dialB;
    private int serviceId;
    private int ratePlanId;
    private float externelCharge;
    private boolean isRating;
    private float consumption;

    public CDR(int cdrId, String dialA, String dialB, Date startDate, String timeStamp, int ratePlanId, int serviceId, float consumption, float externelCharge, boolean isRating) {
        this.cdrId = cdrId;
        this.timeStamp = timeStamp;
        this.startDate = startDate;
        this.dialA = dialA;
        this.dialB = dialB;
        this.serviceId = serviceId;
        this.ratePlanId = ratePlanId;
        this.externelCharge = externelCharge;
        this.isRating = isRating;
        this.consumption = consumption;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public int getCdrId() {
        return cdrId;
    }

    public void setCdrId(int cdrId) {
        this.cdrId = cdrId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getDialA() {
        return dialA;
    }

    public void setDialA(String dialA) {
        this.dialA = dialA;
    }

    public String getDialB() {
        return dialB;
    }

    public void setDialB(String dialB) {
        this.dialB = dialB;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(int ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public float getExternelCharge() {
        return externelCharge;
    }

    public void setExternelCharge(float externelCharge) {
        this.externelCharge = externelCharge;
    }

    public boolean isIsRating() {
        return isRating;
    }

    public void setIsRating(boolean isRating) {
        this.isRating = isRating;
    }

}
