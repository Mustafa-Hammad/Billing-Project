/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schema;

/**
 *
 * @author nora
 */
public class ServiceT {

    private int zone;
    private int service;
    private String name;
    private String fu;
    private String externalCost;

    public ServiceT(String fu, String externalCost) {
        if (fu.isEmpty()) {
            this.fu = "0";
        }
        if (externalCost.isEmpty()) {
            this.externalCost = "0";
        }

        this.fu = fu;
        this.externalCost = externalCost;
    }

    public ServiceT(String name, int zone, int service, String fu, String externalCost) {
        if (fu.isEmpty() || fu.equalsIgnoreCase("")) {
            this.fu = "0";
        } else {
            this.fu = fu;
        }

        if (externalCost.isEmpty() || externalCost.equalsIgnoreCase("")) {
            this.externalCost = "0";
        } else {
            this.externalCost = externalCost;
        }
        this.zone = zone;
        this.service = service;
        this.name = name;

    }

    public ServiceT(String name, int service, int zone) {
        this.zone = zone;
        this.service = service;
        this.name = name;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFu() {
        return fu;
    }

    public void setFu(String fu) {
        this.fu = fu;
    }

    public String getExternalCost() {
        return externalCost;
    }

    public void setExternalCost(String externalCost) {
        this.externalCost = externalCost;
    }

}
