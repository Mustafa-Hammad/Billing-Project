/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

/**
 *
 * @author ahmedmedhat
 */
public class User {

    private int id, credit;
    private String name, email, password, rpName, contrStatus;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRpName(String rpName) {
        this.rpName = rpName;
    }

    public void setContrStatus(String contrStatus) {
        this.contrStatus = contrStatus;
    }

    public String getPassword() {
        return password;
    }

    public String getRpName() {
        return rpName;
    }

    public String getContrStatus() {
        return contrStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public int getCredit() {
        return credit;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, int credit) {
        this.id = id;
        this.credit = credit;
    }

    

    

}
