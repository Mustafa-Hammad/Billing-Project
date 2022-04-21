/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billing.schema;

import java.util.Date;

/**
 *
 * @author nora
 */
public class CDR {

    private int cdrId;
    private String timeStamp;
    private Date startDate;
    private int dialA;
    private int dialB;
    private int serviceId;
    private int ratePlanId;
    private int eventType; 
    private float amount;

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

    public int getDialA() {
        return dialA;
    }

    public void setDialA(int dialA) {
        this.dialA = dialA;
    }

    public int getDialB() {
        return dialB;
    }

    public void setDialB(int dialB) {
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

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
