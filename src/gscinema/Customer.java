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
    
    public Customer(String ic, String name) {
        this.ic = ic;
        this.name = name;
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
