/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schema;

/**
 *
 * @author nora
 */
public class RatePlan {

    int fuVOnNet, fuVOnCross, fvVInter, fuSOnNet, fuSOnCross, fvSInter, fvData, monthlyfee,id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthlyfee() {
        return monthlyfee;
    }

    public void setMonthlyfee(int monthlyfee) {
        this.monthlyfee = monthlyfee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
