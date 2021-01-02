package org.mapapp.base;

public class Customer {

    private final int id;
    private String first;
    private String last;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private String stable;
    private boolean active;

    public Customer(int id, String first, String last, String address, String city,
        String state, String zip, String phone, String email, String stable, boolean active) {
        
        this.id = id;
        this.first = first;
        this.last = last;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.stable = stable;
        this.active = active;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.first;
    }

    public String getLastName() {
        return this.last;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getZip() {
        return this.zip;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getStable() {
        return this.stable;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setFirstName(String first) {
        this.first = first;
    }

    public void setLastName(String last) {
        this.last = last;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStable(String stable) {
        this.stable = stable;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}