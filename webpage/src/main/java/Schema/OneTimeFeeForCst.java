/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schema;

/**
 *
 * @author ahmedmedhat
 */
public class OneTimeFeeForCst {
    int contractOTFID, contractID, bucketID, consumtion;

    public int getContractOTFID() {
        return contractOTFID;
    }

    public int getContractID() {
        return contractID;
    }

    public int getBucketID() {
        return bucketID;
    }

    public int getConsumtion() {
        return consumtion;
    }

    public void setContractOTFID(int contractOTFID) {
        this.contractOTFID = contractOTFID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public void setBucketID(int bucketID) {
        this.bucketID = bucketID;
    }

    public void setConsumtion(int consumtion) {
        this.consumtion = consumtion;
    }

    public OneTimeFeeForCst() {
    }

    public OneTimeFeeForCst(int contractOTFID, int contractID, int bucketID, int consumtion) {
        this.contractOTFID = contractOTFID;
        this.contractID = contractID;
        this.bucketID = bucketID;
        this.consumtion = consumtion;
    }
    
}
