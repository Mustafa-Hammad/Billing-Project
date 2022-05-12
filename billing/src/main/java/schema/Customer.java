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
    private  int numberOfContract; 
    private  int customerId;
    private  String  name;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }
    public Customer(){}
    
    
    
    
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
