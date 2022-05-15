/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schema;

/**
 *
 * @author nora
 */
public class Contract {
private int id,fuVOnNet, fuVOnCross, fvVInter,fuSOnNet, fuSOnCross, fvSInter,fvData,rpId;
private String msisdn;

    public Contract(int id, int fuVOnNet, int fuVOnCross, int fvVInter, int fuSOnNet, int fuSOnCross, int fvSInter, int fvData, int rpId, String msisdn) {
        this.id = id;
        this.fuVOnNet = fuVOnNet;
        this.fuVOnCross = fuVOnCross;
        this.fvVInter = fvVInter;
        this.fuSOnNet = fuSOnNet;
        this.fuSOnCross = fuSOnCross;
        this.fvSInter = fvSInter;
        this.fvData = fvData;
        this.rpId = rpId;
        this.msisdn = msisdn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFuVOnNet() {
        return fuVOnNet;
    }

    public void setFuVOnNet(int fuVOnNet) {
        this.fuVOnNet = fuVOnNet;
    }

    public int getFuVOnCross() {
        return fuVOnCross;
    }

    public void setFuVOnCross(int fuVOnCross) {
        this.fuVOnCross = fuVOnCross;
    }

    public int getFvVInter() {
        return fvVInter;
    }

    public void setFvVInter(int fvVInter) {
        this.fvVInter = fvVInter;
    }

    public int getFuSOnNet() {
        return fuSOnNet;
    }

    public void setFuSOnNet(int fuSOnNet) {
        this.fuSOnNet = fuSOnNet;
    }

    public int getFuSOnCross() {
        return fuSOnCross;
    }

    public void setFuSOnCross(int fuSOnCross) {
        this.fuSOnCross = fuSOnCross;
    }

    public int getFvSInter() {
        return fvSInter;
    }

    public void setFvSInter(int fvSInter) {
        this.fvSInter = fvSInter;
    }

    public int getFvData() {
        return fvData;
    }

    public void setFvData(int fvData) {
        this.fvData = fvData;
    }

    public int getRpId() {
        return rpId;
    }

    public void setRpId(int rpId) {
        this.rpId = rpId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    
}