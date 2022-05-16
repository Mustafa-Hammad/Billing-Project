/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.billing;

import Schema.Contract;
import database.BillingHandeller;
import database.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import schema.BillingContract;
import schema.Customer;
import schema.RatePlan;

/**
 *
 * @author asmaaMohamed
 */
public class Billing {

    BillingHandeller bH = new BillingHandeller();
    Customer customer = new Customer();
    BillingContract billingContract = new BillingContract();

    public void billGeneration() {

        List<Customer> customerInfo = new ArrayList<>();
        customerInfo = bH.getAllCustommer();
        for (Customer cu : customerInfo) {

            customer.setCustomerId(cu.getCustomerId());
            customer.setName(cu.getName());
            customer.setEmail(cu.getEmail());
            billingContract.setCuid(cu.getCustomerId());
            billingContract.setRecurringCost(bH.getRecurringCost(cu.getCustomerId()));
            if (bH.getRemaingRecurringMonth(cu.getCustomerId()) == 0) {
                bH.resetrecurring(cu.getCustomerId());
                billingContract.setRecurringCost(0);
            } else {
                bH.updateRecurringMonths(cu.getCustomerId());
            }

            billForOneCustomer(cu.getCustomerId());
            bH.insertCustomer(customer);
        }
    }

    public void billForOneCustomer(int CustomerId) {

        List<Contract> customerContracts = new ArrayList<>();
        customerContracts = bH.getAllContracts(CustomerId);
        customer.setNumberOfContract(customerContracts.size());

        for (Contract c : customerContracts) {

            billingContract.setMsisdn(c.getMsisdn());
            //get all deatils of rate plan by contract 
            RatePlan detailsRP = bH.getRatePlan(c.getRpId());

            //set all deatils of rate plan to contract 
            billingContract.setRp(detailsRP.getName());
            billingContract.setCostRP(detailsRP.getMonthlyfee());

            billingContract.setFuVOnnet(detailsRP.getFuVOnNet());
            billingContract.setFuVcrossnet(detailsRP.getFuVOnCross());
            billingContract.setFuVinter(detailsRP.getFvVInter());

            billingContract.setFuSOnnet(detailsRP.getFuSOnNet());
            billingContract.setFuScrossnet(detailsRP.getFuSOnCross());
            billingContract.setFuSinter(detailsRP.getFvSInter());

            billingContract.setFuData(detailsRP.getFvData());

            //set used fu to all services and their zone
            billingContract.setUsedFuVOnnet(detailsRP.getFuVOnNet() - c.getFuVOnNet());
            billingContract.setUsedFuVcrossnet(detailsRP.getFuVOnCross() - c.getFuVOnCross());
            billingContract.setUsedFuVinter(detailsRP.getFvVInter() - c.getFvVInter());

            billingContract.setUsedFuSOnnet(detailsRP.getFuSOnNet() - c.getFuSOnNet());
            billingContract.setUsedFuScrossnet(detailsRP.getFuSOnCross() - c.getFuSOnCross());
            billingContract.setUsedFuSinter(detailsRP.getFvSInter() - c.getFvSInter());

            billingContract.setUsedFuData(detailsRP.getFvData() - c.getFvData());

            //get external cost from contract and set to billingcontract 
            float externalCost = bH.getExternalCost(c.getId());
            billingContract.setCostExternalCharge(externalCost);

            // get one time fee => total cost 
            billingContract.setCostOneTimeFee(bH.getOnTimeFeesCost(c.getId()));
            bH.resetOnTimefeeBucket(c.getId());

            // set one time fee details to table billing details one time fee
            // get recurring => total cost and decrease one from number of month 
            // set recurring details to table billing details recurring
            // rate plan cost tax after
//            billingContract.setPriceAfterTax((float) (detailsRP.getMonthlyfee() * 1.1));
            // set udr isbilling true 
            float TotalPrice = billingContract.getRecurringCost() + billingContract.getCostRP() + billingContract.getCostOneTimeFee() + billingContract.getCostExternalCharge();

            billingContract.setTax((float) (TotalPrice * 0.1));
            billingContract.setPriceAfterTax((float) (TotalPrice * 1.1));
            bH.ResetContract(c.getId(), detailsRP);
            bH.insertContract(billingContract);
            billingContract.setRecurringCost(0);

            bH.updateUdr(c.getId());

            // set free unit to contract
        }
    }

    public static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.getDatabaseInstance();
        db.connectToDatabase();
        Billing billing = new Billing();
        billing.billGeneration();

    }

}
