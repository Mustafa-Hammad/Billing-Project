/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schema;

/**
 *
 * @author ahmedmedhat
 */
public class OneTimeFee {
    int bucketID, zoneID, serviceID, cost, quota;

    public OneTimeFee(int bucketID, int zoneID, int serviceID, int cost, int quota) {
        this.bucketID = bucketID;
        this.zoneID = zoneID;
        this.serviceID = serviceID;
        this.cost = cost;
        this.quota = quota;
    }

    public OneTimeFee() {
    }

    public int getBucketID() {
        return bucketID;
    }

    public int getZoneID() {
        return zoneID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public int getCost() {
        return cost;
    }

    public int getQuota() {
        return quota;
    }

    public void setBucketID(int bucketID) {
        this.bucketID = bucketID;
    }

    public void setZoneID(int zoneID) {
        this.zoneID = zoneID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
    
    
}
