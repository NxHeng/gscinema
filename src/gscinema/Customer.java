/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gscinema;

/**
 *
 * @author e-hen
 */
public class Customer {
 
    // ic -> primary key
    private String ic;
    private String name;
    private String email;
    private String password;
    private String phone;
    
    public Customer(String ic, String name, String email, String password, String phone) {
        this.ic = ic;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getIc() {
        return ic;
    }
    
    public void setIc(String ic) {
        this.ic = ic;
    }   
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}