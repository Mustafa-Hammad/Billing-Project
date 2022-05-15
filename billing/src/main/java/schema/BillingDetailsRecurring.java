/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schema;

/**
 *
 * @author asmaaMohamed
 */
public class BillingDetailsRecurring {
    
    private  String product ;
    private  int remainingMonths ;
    private  int numberOfMonths ;

    public void setProduct(String product) {
        this.product = product;
    }

    public void setRemainingMonths(int remainingMonths) {
        this.remainingMonths = remainingMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public String getProduct() {
        return product;
    }

    public int getRemainingMonths() {
        return remainingMonths;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }
    
    
}
