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
import schema.Customer;

/**
 *
 * @author asmaaMohamed
 */
public class Billing {

    BillingHandeller bH = new BillingHandeller();
    Customer customer = new Customer();

    public void billGeneration() {

        List<Customer> customerInfo = new ArrayList<>();
        customerInfo = bH.getAllCustommer();
        for (Customer cu : customerInfo) {
            billForOneCustomer(cu.getCustomerId());
            customer.setCustomerId(cu.getCustomerId());
            customer.setName(cu.getName());
        }

    }

    public void billForOneCustomer(int CustomerId) {

        List<Contract> customerContracts = new ArrayList<>();
        customerContracts = bH.getAllContracts(CustomerId);
        customer.setNumberOfContract(customerContracts.size());

        for (Contract c : customerContracts) {
            
        }

    }

    public static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.getDatabaseInstance();
        db.connectToDatabase();
    }

}
