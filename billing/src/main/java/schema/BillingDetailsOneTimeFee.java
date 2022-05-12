/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schema;

/**
 *
 * @author asmaaMohamed
 */
public class BillingDetailsOneTimeFee {
    
    private  String name ;
    private int used ;
    private  int quota ;

    public void setName(String name) {
        this.name = name;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getName() {
        return name;
    }

    public int getUsed() {
        return used;
    }

    public int getQuota() {
        return quota;
    }
    
}
