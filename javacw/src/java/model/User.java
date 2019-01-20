/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HaShaN
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String country;
    private String contactNo;
    private String email;
    private String userName;
    private String password;
    private Role role;
    private Short status;

    public User() {
    }

    public User(int id, String firstName, String lastName, String country, String contactNo, String email, String userName, String password, Role role, Short status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.contactNo = contactNo;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = status;
    }
    
    public User(String firstName, String lastName, String country, String contactNo, String email, String userName, String password, Role role, Short status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.contactNo = contactNo;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + ", contactNo=" + contactNo + ", email=" + email + ", userName=" + userName + ", password=" + password + ", role=" + role + ", status=" + status + '}';
    }
}
