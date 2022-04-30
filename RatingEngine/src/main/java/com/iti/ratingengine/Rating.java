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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nora
 */
public class Rating implements ServiceType, Zone {

    private final DatabaseConnection db = DatabaseConnection.getDatabaseInstance();
    private CDR cdr;
    private UDR udr = new UDR();

//    public void Rating() {
//        udr = new UDR();
//    }
    private void setUDRTODB(CDR uploadCDR, int conID) {
        try {

            PreparedStatement ps = db.getConnection().prepareStatement("insert into udr (diala,dialb,startdate,timestamp,rp_id,service_id,con_id,consumption,cost)"
                    + "                                                  values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, uploadCDR.getDialA());
            ps.setString(2, uploadCDR.getDialB());
            ps.setDate(3, (Date) uploadCDR.getStartDate());
            ps.setString(4, uploadCDR.getTimeStamp());
            ps.setInt(5, uploadCDR.getRatePlanId());
            ps.setInt(6, uploadCDR.getServiceId());
            ps.setInt(7, conID);
            ps.setFloat(8, uploadCDR.getConsumption());
            ps.setFloat(9, uploadCDR.getExternelCharge());
            int done = ps.executeUpdate();
            System.out.println("udr "+done);
        } catch (SQLException e) {
            System.out.println("error - at setting udr to db : " + e);
        }
    }

    private void changeCDRIsRationg(int cdrId) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("update cdr set israting=true where cdr_id=? ");
            ps.setInt(1, cdrId);
            int done = ps.executeUpdate();
            System.out.println("cdr State " + done);
        } catch (SQLException ex) {
            Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private float getExternalCostFromDB(int RatePlan, int ServiceId, int zoneId) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select externalcost from ratingPkg where rp_id=? and service_id = ? and zone_id = ? ");
            ps.setInt(1, RatePlan);
            ps.setInt(2, ServiceId);
            ps.setInt(3, zoneId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException e) {
            System.out.println("error - at getting ExternalCost from db : " + e);
        }
        return 0;

    }

//    private int getFreeUnitFromRatePlanDB(int RatePlan, int ServiceId, int zoneId) {
//        try {
//            PreparedStatement ps = db.getConnection().prepareStatement("select FU from ratingPkg where RatePlan=? and ServiceId = ? and zoneId = ? ");
//            ps.setInt(1, RatePlan);
//            ps.setInt(2, ServiceId);
//            ps.setInt(3, zoneId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                return rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            System.out.println("error - at getting FreeUnitFromRatePlan from db : " + e);
//        }
//        return 0;
//    }
    private int getFreeUnitFromContractDB(int con_id, int serviceId, int zoneId) {
        try {
            String[][] fu = {{"fuvoiceonnet", "fuvoicecrossnet", "fuvoiceinternational"},
            {"fusmsonnet", "fusmscrossnet", "fusmsinternational"},
            {"fudata", "fudata", "fudata"}};

            PreparedStatement ps = db.getConnection().prepareStatement("select " + fu[serviceId - 1][zoneId - 1] + " from contract where con_id=?");
            ps.setInt(1, con_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error - at getting FreeUnitFromContract from db : " + e);
        }
        return 0;
    }

    private int getFreeUnitFromBucketDB(int conID, int ServiceId, int zoneId) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select cot.consumtion from  contract_onetimefee cot, onetimefeebucket ot where  cot.con_id=? and ot.service_id = ? and ot.zone_id = ? and cot.bucket_id = ot.bucket_id  ");
            ps.setInt(1, conID);
            ps.setInt(2, ServiceId);
            ps.setInt(3, zoneId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error - at getting FreeUnitFromBucket from db : " + e);
        }
        return 0;
    }

    private void setFreeUnitToBucket(float bucketConsumption, int conID, int serviceId, int zoneId) {
        System.out.println(bucketConsumption);
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("update contract_onetimefee set consumtion=? where con_id=? and bucket_id=(select bucket_id from onetimefeebucket where zone_id=? and service_id=?)");
            ps.setInt(1, (int) bucketConsumption);
            ps.setInt(2, conID);
            ps.setInt(3, zoneId);
            ps.setInt(4, serviceId);
            int done = ps.executeUpdate();
            System.out.println("setFUB "+done);
        } catch (SQLException ex) {
            Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setFreeUnitToContract(float consumption, int conID, int serviceId, int zoneId) {
        try {
            String[][] fu = {{"fuvoiceonnet", "fuvoicecrossnet", "fuvoiceinternational"},
            {"fusmsonnet", "fusmscrossnet", "fusmsinternational"},
            {"fudata", "fudata", "fudata"}};

            PreparedStatement ps = db.getConnection().prepareStatement("update contract set " + fu[serviceId - 1][zoneId - 1] + "= ? where con_id=?");
            ps.setInt(1, (int) consumption);
            ps.setInt(2, conID);
            int done = ps.executeUpdate();
            System.out.println("set FU Con "+done);

        } catch (SQLException e) {
            System.out.println("error - at setFreeUnitFromContract from db : " + e);
        }
    }

    private int getContractId(String msisdn) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select con_id from contract where msisdn=? ");
            ps.setString(1, msisdn);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rating.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private void checkBucketFU(int serviceId, int zone, float consumption, float externalCost) {
        System.out.println("consumption "+consumption);
        int fuFromBucket = getFreeUnitFromBucketDB(udr.getContractId(), serviceId, zone);
        //System.out.println("fuFromBucket"+fuFromBucket);
        if (fuFromBucket == 0) {
            // يبقي هنسيب الاسهتلاك عادي زي ما هو 
            float consumptionLE = (consumption * externalCost) / 100;
            cdr.setExternelCharge(consumptionLE);

        } else {
            // نخصم من الباقه الاضافيه  وكمان نتاكد اننا نخصم لو مش مكافيه نزود الفلوس
            float remaingBFU = (float) (fuFromBucket - consumption);
            System.out.println("remaingBFU "+remaingBFU);
            // System.out.println("-Math.ceil(cdr.getConsumption()/60)"+Math.ceil(cdr.getConsumption()/60));
            if (remaingBFU >= 0) {
                setFreeUnitToBucket(remaingBFU, udr.getContractId(), serviceId, zone);
                cdr.setExternelCharge(0);
            } else {
                setFreeUnitToBucket(0, udr.getContractId(), serviceId, zone);
                cdr.setExternelCharge((-1 * remaingBFU * externalCost) / 100);
                System.out.println("ExternelCharge "+cdr.getExternelCharge());
                
            }

        }

    }

    private void rating(int ServiceId) {
        int zone = whichOperator();
        System.out.println("zone" + zone);
        udr.setContractId(getContractId(cdr.getDialA()));

        float externalCost = getExternalCostFromDB(cdr.getRatePlanId(), ServiceId, zone);

        int fuFromContract = getFreeUnitFromContractDB(udr.getContractId(), ServiceId, zone);
        if (fuFromContract == 0) {
            checkBucketFU(ServiceId, zone, cdr.getConsumption(), externalCost);
        } else {
            // نخصم من الباقه وكمان نتاكد اننا نخصم لو مش مكافيه نزود الفلوس
            float remaingFU = (float) (fuFromContract - cdr.getConsumption());
            if (remaingFU >= 0) {
                setFreeUnitToContract(remaingFU, udr.getContractId(), ServiceId, zone);
                cdr.setExternelCharge(0);
                //هنا محتاجه نعرف  الزون هل كدا ينفع اعمل كذا فانكشن ابديت
            } else {
                setFreeUnitToContract(0, udr.getContractId(), ServiceId, zone);
                checkBucketFU(ServiceId, zone, (-1 * remaingFU), externalCost);
            }
        }
    }


    private int whichOperator() {
        String dialB = cdr.getDialB();
        if (dialB.matches("\\d+")) {
            if (dialB.startsWith("002")) {
                char num = dialB.charAt(5);

                if (num == '0') {
                    return ONNET;
                } else {
                    return CROSSNET;
                }
            } else {
                return INTERNATIONAL;
            }
        }

        return DATAZONE;
    }

    public void applyRating() {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select * from cdr where israting=false");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                System.out.println("data");

                cdr = new CDR(res.getInt(1), res.getString(2), res.getString(3), res.getDate(4), res.getString(5),
                        res.getInt(6), res.getInt(7), res.getFloat(8), res.getInt(9), res.getBoolean(10));
//                System.out.println(cdr.getServiceId()+"+++"+cdr.getCdrId());
                switch (cdr.getServiceId()) {
                    case CALL:
                        cdr.setConsumption((float) Math.ceil(cdr.getConsumption() / 60));
                        rating(CALL);
                        setUDRTODB(cdr, udr.getContractId());
                        changeCDRIsRationg(cdr.getCdrId());

                        break;
                    case SMS:
                        rating(SMS);
                        setUDRTODB(cdr, udr.getContractId());
                        changeCDRIsRationg(cdr.getCdrId());
                        break;
                    case DATA:
                        cdr.setConsumption((float) Math.ceil(cdr.getConsumption() / 1024));
                        rating(DATA);
                        setUDRTODB(cdr, udr.getContractId());
                        changeCDRIsRationg(cdr.getCdrId());
                        break;
                    default:
                        System.out.println("error - not exsit service id ");
                }

            }

        } catch (SQLException e) {
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
            Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("error - rating connection to db : " + e);
        }
    }

}
