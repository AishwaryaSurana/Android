/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expensespackage;

/**
 *
 * @author Aishwarya
 */
public class User
{
    int id,type;
    String name,email,mobile,password,manEmail;

    public User() {
    }

    public User(int id, int type, String name, String email, String mobile, String password, String manEmail) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.manEmail = manEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getManEmail() {
        return manEmail;
    }

    public void setManEmail(String manEmail) {
        this.manEmail = manEmail;
    }
    
    
}
