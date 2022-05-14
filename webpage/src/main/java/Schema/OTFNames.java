/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schema;

/**
 *
 * @author ahmedmedhat
 */
public class OTFNames {
    
    String zone, service;

    public OTFNames() {
    }

    public OTFNames(String zone, String service) {
        this.zone = zone;
        this.service = service;
    }

    public String getZone() {
        return zone;
    }

    public String getService() {
        return service;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setService(String service) {
        this.service = service;
    }
}
