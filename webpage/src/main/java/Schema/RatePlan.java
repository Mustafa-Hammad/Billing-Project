/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schema;

/**
 *
 * @author nora
 */
public class RatePlan {

    int id, fuVOnNet, fuVOnCross, fvVInter, fuSOnNet, fuSOnCross, fvSInter, fvData, monthlyfee;
    int extraVOnNet, extraVOnCross, extraVInter, extraSOnNet, extraSOnCross, extraSInter, extraData;

    String name;

    public RatePlan(int id, int monthlyfee, String name) {
        this.id = id;
        this.monthlyfee = monthlyfee;
        this.name = name;
    }

    public RatePlan() {
    }

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

    public int getExtraVOnNet() {
        return extraVOnNet;
    }

    public void setExtraVOnNet(int extraVOnNet) {
        this.extraVOnNet = extraVOnNet;
    }

    public int getExtraVOnCross() {
        return extraVOnCross;
    }

    public void setExtraVOnCross(int extraVOnCross) {
        this.extraVOnCross = extraVOnCross;
    }

    public int getExtraVInter() {
        return extraVInter;
    }

    public void setExtraVInter(int extraVInter) {
        this.extraVInter = extraVInter;
    }

    public int getExtraSOnNet() {
        return extraSOnNet;
    }

    public void setExtraSOnNet(int extraSOnNet) {
        this.extraSOnNet = extraSOnNet;
    }

    public int getExtraSOnCross() {
        return extraSOnCross;
    }

    public void setExtraSOnCross(int extraSOnCross) {
        this.extraSOnCross = extraSOnCross;
    }

    public int getExtraSInter() {
        return extraSInter;
    }

    public void setExtraSInter(int extraSInter) {
        this.extraSInter = extraSInter;
    }

    public int getExtraData() {
        return extraData;
    }

    public void setExtraData(int extraData) {
        this.extraData = extraData;
    }

}
