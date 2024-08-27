package vn.hoidanit.laptopshop.domain;

import org.springframework.context.annotation.Bean;

public class User {
    private long id;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String phone;

    @Bean
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("| %d | %s | %s | %s | %s | %s |", id, email, password, fullName, address, phone);
    }

}
