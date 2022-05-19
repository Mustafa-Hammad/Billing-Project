/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import Schema.Contract;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import schema.BillingContract;
import schema.Customer;
import schema.RatePlan;

/**
 *
 * @author asmaaMohamed
 */
public class BillingHandeller {

    private PreparedStatement preStm = null;
    ResultSet rs = null;
    DatabaseConnection db = DatabaseConnection.getDatabaseInstance();
    HashMap<String, String> customerInfo = new HashMap<String, String>();

    public List<Customer> getAllCustommer() {
        List<Customer> customers = new ArrayList<>();
        try {
            preStm = db.getConnection().prepareStatement("select cu_id, name,email from customer ");
            rs = preStm.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return customers;
    }

    public List<Contract> getAllContracts(int cuid) {
        List<Contract> contract = new ArrayList<>();

        try {
            preStm = db.getConnection().prepareStatement("select con_id, fuvoiceonnet,fuvoicecrossnet, fuvoiceinternational,fusmsonnet, fusmscrossnet, fusmsinternational,fudata,rp_id,msisdn from contract where cu_id = ? ");
            preStm.setInt(1, cuid);
            rs = preStm.executeQuery();
            while (rs.next()) {
                contract.add(new Contract(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10)));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return contract;
    }

    public float getExternalCost(int contractId) {
        try {
            preStm = db.getConnection().prepareStatement("select sum(cost) from udr where con_id=? and isbilling = false ");
            preStm.setInt(1, contractId);
            rs = preStm.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public void updateUdr(int contractId) {
        try {
            preStm = db.getConnection().prepareStatement("update udr set isbilling =true where con_id=? ");
            preStm.setInt(1, contractId);
            preStm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getOnTimeFeesCost(int conId) {
        try {
            preStm = db.getConnection().prepareStatement("select sum(otf.cost) from onetimefeebucket as otf,contract_onetimefee as con_otf where con_otf.con_id =? and con_otf.bucket_id=otf.bucket_id ");
            preStm.setInt(1, conId);
            rs=preStm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public void resetOnTimefeeBucket(int conId) {

        try {
            preStm = db.getConnection().prepareStatement("DELETE FROM contract_onetimefee WHERE con_id=?");
            preStm.setInt(1, conId);
            preStm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getRecurringCost(int custumerId) {
        try {
            preStm = db.getConnection().prepareStatement("select sum(rec.costpermonth) from recurring as rec ,customer_recurring as cRec where cRec.cu_id=? and rec.re_id=cRec.re_id ");
            preStm.setInt(1, custumerId);
            rs = preStm.executeQuery();
            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateRecurringMonths(int customerId) {
        try {
            preStm = db.getConnection().prepareStatement("update customer_recurring  set remaing = remaing - 1  where cu_id=? ");
            preStm.setInt(1, customerId);
            preStm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getRemaingRecurringMonth(int cID) {

        try {
            preStm = db.getConnection().prepareStatement("select cRec.remaing from recurring as rec ,customer_recurring as cRec where cRec.cu_id=? and rec.re_id=cRec.re_id ");
            preStm.setInt(1, cID);
            rs = preStm.executeQuery();
            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public void resetrecurring(int cId) {

        try {
            preStm = db.getConnection().prepareStatement("DELETE FROM customer_recurring WHERE cu_id=?");
            preStm.setInt(1, cId);
            preStm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertCustomer(Customer cu) {

        try {
            preStm = db.getConnection().prepareStatement("insert into billingcustomer (cu_id,name,numberofcontract,email) values(?,?,?,?) ");
            preStm.setInt(1, cu.getCustomerId());
            preStm.setString(2, cu.getName());
            preStm.setInt(3, cu.getNumberOfContract());
            preStm.setString(4, cu.getEmail());
            preStm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertContract(BillingContract c) {

        try {
            preStm = db.getConnection().prepareStatement("insert into billingcontract (cu_id , msisdn , rp, costrp , costonetimefee , costExternalCharge , usedFuVOnnet, usedFuVcrossnet , usedFuVinter , usedFuSOnnet , usedFuScrossnet , usedFuSinter , usedFuData ,FuVOnnet , FuVcrossnet , FuVinter, FuSOnnet, FuScrossnet , FuSinter , FuData,costRecurring,tax,priceaftertax)"
                    + "                                    values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
            preStm.setInt(1, c.getCuid());
            preStm.setString(2, c.getMsisdn());
            preStm.setString(3, c.getRp());
            preStm.setInt(4, c.getCostRP());
            preStm.setInt(5, c.getCostOneTimeFee());
            preStm.setDouble(6, c.getCostExternalCharge());
            preStm.setInt(7, c.getUsedFuVOnnet());
            preStm.setInt(8, c.getUsedFuVcrossnet());
            preStm.setInt(9, c.getUsedFuVinter());
            preStm.setInt(10, c.getUsedFuSOnnet());
            preStm.setInt(11, c.getUsedFuScrossnet());
            preStm.setInt(12, c.getUsedFuSinter());
            preStm.setInt(13, c.getUsedFuData());
            preStm.setInt(14, c.getFuVOnnet());
            preStm.setInt(15, c.getFuVcrossnet());
            preStm.setInt(16, c.getFuVinter());
            preStm.setInt(17, c.getFuSOnnet());
            preStm.setInt(18, c.getFuScrossnet());
            preStm.setInt(19, c.getFuSinter());
            preStm.setInt(20, c.getFuData());
            preStm.setInt(21, c.getRecurringCost());
            preStm.setFloat(22, c.getTax());
            preStm.setFloat(23, c.getPriceAfterTax());

            preStm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int ResetContract(int coid, RatePlan ratePlans) {
        try {
            //Cu_Id serial, name string, address string, cridet int
            preStm = db.getConnection().prepareStatement("update contract set fuvoiceonnet=?, fuvoicecrossnet=?,fuvoiceinternational=?,fusmsonnet=?,fusmscrossnet=?,fusmsinternational=?,fudata=? where con_id =? ");
            preStm.setInt(1, ratePlans.getFuVOnNet());
            preStm.setInt(2, ratePlans.getFuVOnCross());
            preStm.setInt(3, ratePlans.getFvVInter());
            preStm.setInt(4, ratePlans.getFuSOnNet());
            preStm.setInt(5, ratePlans.getFuSOnCross());
            preStm.setInt(6, ratePlans.getFvSInter());
            preStm.setInt(7, ratePlans.getFvData());
            preStm.setInt(8, coid);

            int i = preStm.executeUpdate();

            return 0;
        } catch (SQLException e) {
            Logger.getLogger(BillingHandeller.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

//    public void getCustomerInfo(String msisdn) {
//
//        try {
//            preStm = db.getConnection().prepareStatement("select cu.name, cu.email,c.cu_id, c.rp_id,con_id from customer as cu ,contract as c where c.msisdn=? and cu.cu_id = c.cu_id ");
//            preStm.setString(1, msisdn);
//            rs = preStm.executeQuery();
//            while (rs.next()) {
//
//                customerInfo.put("CustomerName", rs.getString(1));
//                customerInfo.put("CustomerEmail", rs.getString(2));
//                customerInfo.put("CustomerId", Integer.toString(rs.getInt(3)));
//                customerInfo.put("CustomerRatePlanId", Integer.toString(rs.getInt(4)));
//                customerInfo.put("ContractId", Integer.toString(rs.getInt(5)));
//
//            }
//
//        } catch (SQLException ex) {
//            System.out.println("com.mycompany.pdf.DatabaseConnection.getCustomerInfo()");
//            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//    public void getMsisdnsForCustomer(int customerId) {
//        try {
//            preStm = db.getConnection().prepareStatement("select count(msisdn) from contract where cu_id=? ");
//            preStm.setInt(1, customerId);
//            rs = preStm.executeQuery();
//            while (rs.next()) {
//
//                customerInfo.put("NumberOfMsisdn", Integer.toString(rs.getInt(1)));
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    // getAllRatePlan
    public RatePlan getRatePlan(int rp_id) {
        RatePlan ratePlan = new RatePlan();
        ratePlan.setId(rp_id);
        try {
            preStm = db.getConnection().prepareStatement("select name,monthlyfee from rateplan where rp_id = ?");
            preStm.setInt(1, rp_id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                ratePlan.setName(rs.getString(1));
                ratePlan.setMonthlyfee(rs.getInt(2));
                ratePlan = getDetailsRatePlanByID(ratePlan);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return ratePlan;
    }

    // getAllRatePlan
    public RatePlan getDetailsRatePlanByID(RatePlan ratePlan) {

        try {
            preStm = db.getConnection().prepareStatement("select service_id,zone_id,fu from ratingpkg where rp_id = ?");
            preStm.setInt(1, ratePlan.getId());
            rs = preStm.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1 && rs.getInt(2) == 1) {
                    ratePlan.setFuVOnNet(rs.getInt(3));
                } else if (rs.getInt(1) == 1 && rs.getInt(2) == 2) {
                    ratePlan.setFuVOnCross(rs.getInt(3));
                } else if (rs.getInt(1) == 1 && rs.getInt(2) == 3) {
                    ratePlan.setFvVInter(rs.getInt(3));
                } else if (rs.getInt(1) == 2 && rs.getInt(2) == 1) {
                    ratePlan.setFuSOnNet(rs.getInt(3));
                } else if (rs.getInt(1) == 2 && rs.getInt(2) == 2) {
                    ratePlan.setFuSOnCross(rs.getInt(3));
                } else if (rs.getInt(1) == 2 && rs.getInt(2) == 3) {
                    ratePlan.setFvSInter(rs.getInt(3));
                } else {
                    ratePlan.setFvData(rs.getInt(3));
                }

            }
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return ratePlan;
    }

}
