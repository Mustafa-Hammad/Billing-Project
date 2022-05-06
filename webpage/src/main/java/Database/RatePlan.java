/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmedmedhat
 */
public class RatePlan {

    int fuSMSIn, fuSMSOut;
    int fuVoiceIn, fuVoiceOut, fuVoiceInternational;
    int fuData;

    public List<Integer> ratePlan(int x) {
        if (x == 1) {
            //voice
            fuVoiceIn = 1000;
            fuVoiceOut = 700;
            fuVoiceInternational = 20;
            //sms
            fuSMSIn = 100;
            fuSMSOut = 100;
            //Data
            fuData = 10000;
        } else if (x == 2) {
            //voice
            fuVoiceIn = 2000;
            fuVoiceOut = 1000;
            fuVoiceInternational = 50;
            //sms
            fuSMSIn = 200;
            fuSMSOut = 200;
            //Data
            fuData = 20000;
        } else {
            //voice
            fuVoiceIn = 4000;
            fuVoiceOut = 4000;
            fuVoiceInternational = 100;
            //sms
            fuSMSIn = 400;
            fuSMSOut = 400;
            //Data
            fuData = 50000;
        }
        
        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(0, fuVoiceIn);
        l1.add(1, fuVoiceOut);
        l1.add(2, fuVoiceInternational);
        l1.add(3, fuSMSIn);
        l1.add(4, fuSMSOut);
        l1.add(5, fuData);

        return l1;
    }

    public void setFuSMSIn(int fuSMSIn) {
        this.fuSMSIn = fuSMSIn;
    }

    public void setFuSMSOut(int fuSMSOut) {
        this.fuSMSOut = fuSMSOut;
    }

    public void setFuVoiceIn(int fuVoiceIn) {
        this.fuVoiceIn = fuVoiceIn;
    }

    public void setFuVoiceOut(int fuVoiceOut) {
        this.fuVoiceOut = fuVoiceOut;
    }

    public void setFuVoiceInternational(int fuVoiceInternational) {
        this.fuVoiceInternational = fuVoiceInternational;
    }

    public void setFuData(int fuData) {
        this.fuData = fuData;
    }

    public int getFuSMSIn() {
        return fuSMSIn;
    }

    public int getFuSMSOut() {
        return fuSMSOut;
    }

    public int getFuVoiceIn() {
        return fuVoiceIn;
    }

    public int getFuVoiceOut() {
        return fuVoiceOut;
    }

    public int getFuVoiceInternational() {
        return fuVoiceInternational;
    }

    public int getFuData() {
        return fuData;
    }

}
