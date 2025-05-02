package br.com.pi.atostech.aplication.domain;

import java.time.LocalDate;

public class UserDomain {

    private final String email;
    private final String name;
    private final String surname;
    private final LocalDate birthday;
    private final String role;
    private String password;

    public UserDomain(
            String email,
            String name,
            String surname,
            LocalDate birthday,
            String role,
            String password) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.role = role;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
