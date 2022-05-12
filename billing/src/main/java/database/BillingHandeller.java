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
import java.util.logging.Level;
import java.util.logging.Logger;
import schema.Customer;

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
            preStm = db.getConnection().prepareStatement("select cu_id, name from customer ");
            rs = preStm.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(rs.getInt(1), rs.getString(2)));
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

    public void getCustomerInfo(String msisdn) {

        try {
            preStm = db.getConnection().prepareStatement("select cu.name, cu.email,c.cu_id, c.rp_id,con_id from customer as cu ,contract as c where c.msisdn=? and cu.cu_id = c.cu_id ");
            preStm.setString(1, msisdn);
            rs = preStm.executeQuery();
            while (rs.next()) {

                customerInfo.put("CustomerName", rs.getString(1));
                customerInfo.put("CustomerEmail", rs.getString(2));
                customerInfo.put("CustomerId", Integer.toString(rs.getInt(3)));
                customerInfo.put("CustomerRatePlanId", Integer.toString(rs.getInt(4)));
                customerInfo.put("ContractId", Integer.toString(rs.getInt(5)));

            }

        } catch (SQLException ex) {
            System.out.println("com.mycompany.pdf.DatabaseConnection.getCustomerInfo()");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getRecurringCost(int custumerId) {
        try {
            preStm = db.getConnection().prepareStatement("select sum(rec.costpermonth) from recurring as rec ,customer_recurring as cRec where cRec.cu_id=? and rec.re_id=cRec.re_id ");
            preStm.setInt(1, custumerId);
            rs = preStm.executeQuery();
            while (rs.next()) {

                customerInfo.put("RecurringCost", Integer.toString(rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getMsisdnsForCustomer(int customerId) {
        try {
            preStm = db.getConnection().prepareStatement("select count(msisdn) from contract where cu_id=? ");
            preStm.setInt(1, customerId);
            rs = preStm.executeQuery();
            while (rs.next()) {

                customerInfo.put("NumberOfMsisdn", Integer.toString(rs.getInt(1)));

            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
