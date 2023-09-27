package com.hengxing.common.Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author hengxing
 * @version 1.0
 * @project EurekaTest
 * @date 9/26/2023 10:25:55
 */
public class User implements Serializable {

    String username;
    Integer age;
    String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(age, user.age)) return false;
        return Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User() {
    }

    public User(String username, Integer age, String address) {
        this.username = username;
        this.age = age;
        this.address = address;
    }
}
