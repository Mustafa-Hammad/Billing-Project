/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schema;

/**
 *
 * @author nora
 */
public class Product {

    private int id;
    private String product;
    private int cost;
    private int costPerMonth;
    private int numberOfMonths;
    private int remaining;

    public Product(String product,int id, int costPerMonth, int numberOfMonths, int remaining) {
        this.id = id;
        this.product = product;
        this.costPerMonth = costPerMonth;
        this.numberOfMonths = numberOfMonths;
        this.remaining = remaining;
    }

    public Product(int id, String product, int cost, int costPerMonth, int numberOfMonths) {
        this.id = id;
        this.product = product;
        this.cost = cost;
        this.costPerMonth = costPerMonth;
        this.numberOfMonths = numberOfMonths;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(int costPerMonth) {
        this.costPerMonth = costPerMonth;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }
}
