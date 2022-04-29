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
public class UDR {

    private int udrId;
    private int cdrId;
    private int contractId;
    private String timeStamp;
    private Date startDate;
    private int dialA;
    private int dialB;
    private int serviceId;
    private int ratePlanId;
    private float cost;
    private float consumption;

    public int getUdrId() {
        return udrId;
    }

    public void setUdrId(int udrId) {
        this.udrId = udrId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
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

    public int getCdrId() {
        return cdrId;
    }

    public void setCdrId(int cdrId) {
        this.cdrId = cdrId;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

}
