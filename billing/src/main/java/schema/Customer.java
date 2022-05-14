/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schema;

/**
 *
 * @author asmaaMohamed
 */
public class Customer {

    private int invoiceId;
    private int numberOfContract;
    private int customerId;
    private String name;
    private  String email;

    public Customer(int customerId, String name,String email) {
        this.customerId = customerId;
        this.name = name;
        this.email=email;
    }

    public Customer() {
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setNumberOfContract(int numberOfContract) {
        this.numberOfContract = numberOfContract;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    
    

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getNumberOfContract() {
        return numberOfContract;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

   

   
}
