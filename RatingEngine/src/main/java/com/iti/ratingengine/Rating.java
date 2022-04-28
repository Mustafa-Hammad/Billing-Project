/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.iti.ratingengine;

import com.iti.database.DatabaseConnection;
import com.iti.schema.CDR;
import com.iti.schema.ServiceType;
import com.iti.schema.UDR;
import com.iti.schema.Zone;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nora
 */
public class Rating implements ServiceType, Zone {

    private DatabaseConnection db = DatabaseConnection.getDatabaseInstance();
    private CDR cdr;
    private UDR udr;

    private void setUDRTODB() {
    }

    private float getExternalCostFromDB(int RatePlan, int ServiceId, int zoneId) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select externalCost from ratingPkg where RatePlan=? and ServiceId = ? and zoneId = ? ");
            ps.setInt(1, RatePlan);
            ps.setInt(2, ServiceId);
            ps.setInt(3, zoneId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (Exception e) {
            System.out.println("error - at getting ExternalCost from db : " + e);
        }
        return 0;

    }

    private int getFreeUnitFromRatePlanDB(int RatePlan, int ServiceId, int zoneId) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select FU from ratingPkg where RatePlan=? and ServiceId = ? and zoneId = ? ");
            ps.setInt(1, RatePlan);
            ps.setInt(2, ServiceId);
            ps.setInt(3, zoneId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("error - at getting FreeUnitFromRatePlan from db : " + e);
        }
        return 0;
    }

    private int getFreeUnitFromContractDB(String MSISDN, int zoneId) {
        try {
            String[] fuVoice = {"FUVoiceOnNet", "FUVoiceCrossNet", "FUVoiceInternational"};
            PreparedStatement ps = db.getConnection().prepareStatement("select ? from contract where MSISDN=? ");
            ps.setString(1, fuVoice[zoneId]);
            ps.setString(2, MSISDN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("error - at getting FreeUnitFromContract from db : " + e);
        }
        return 0;
    }

    private int getFreeUnitFromBucketDB(String MSISDN, int ServiceId, int zoneId) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select co.consumtion from contract c, contract_oneTimeFee cot, oneTimeFeeBucket ot where c.MSISDN=? and  c.contractId = cot.contractId and ot.ServiceId = ? and ot.zoneId = ? and cot.bucketId = ot.bucketId  ");
            ps.setString(1, MSISDN);
            ps.setInt(2, ServiceId);
            ps.setInt(3, zoneId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("error - at getting FreeUnitFromBucket from db : " + e);
        }
        return 0;
    }

    private void callRating() {
        int zone = whichOperator();
        float externalCost = getExternalCostFromDB(cdr.getRatePlanId(), CALL, zone);
        float consumption = cdr.getConsumption() / 60 * externalCost;
        cdr.setConsumption(consumption);
        int fuFromRatePlan = getFreeUnitFromRatePlanDB(cdr.getRatePlanId(), CALL, zone);
        int fuFromContract = getFreeUnitFromContractDB(cdr.getDialA(), zone);
        if (fuFromContract == 0) {
            int fuFromBucket = getFreeUnitFromBucketDB(cdr.getDialA(), CALL, zone);
            if (fuFromBucket == 0) {
// يبقي هنسيب الاسهتلاك عادي زي ما هو 
            } else {
// نخصم من الباقه الاضافيه  وكمان نتاكد اننا نخصم لو مش مكافيه نزود الفلوس
            }
        } else {
// نخصم من الباقه وكمان نتاكد اننا نخصم لو مش مكافيه نزود الفلوس
        }
    }

    private void smsRating() {
    }

    private void dataRating() {
    }

    private int whichOperator() {
        String dialB = cdr.getDialB();
        if (dialB.equalsIgnoreCase("002")) {
            char num = dialB.charAt(5);

            if (num == '0' || num == '6') {
                return ONNET;
            } else {
                return CROSSNET;
            }
        } else {
            return INTERNATIONAL;
        }
    }

    public void applyRating() {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select * from cdr where isRating=false");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                System.out.println("data");

                cdr = new CDR(res.getInt(1), res.getString(2), res.getString(3), res.getDate(4), res.getString(5),
                        res.getInt(6), res.getInt(7), res.getFloat(8), res.getFloat(9), res.getBoolean(10));

                switch (cdr.getServiceId()) {
                    case CALL:
                        callRating();
                        setUDRTODB();

                        break;
                    case SMS:
                        smsRating();
                        setUDRTODB();
                        break;
                    case DATA:
                        dataRating();
                        setUDRTODB();
                        break;
                    default:
                        System.out.println("error - not exsit service id ");
                }

            }

        } catch (Exception e) {
            System.out.println("error - at getting cdr from db : " + e);
        }
    }

    public static void main(String[] args) {
        try {
            DatabaseConnection.getDatabaseInstance().connectToDatabase();
            System.out.println("Start rating");
            Rating rating = new Rating();
            rating.applyRating();

        } catch (Exception e) {
            System.out.println("error - rating connection to db : " + e);
        }
    }

}
