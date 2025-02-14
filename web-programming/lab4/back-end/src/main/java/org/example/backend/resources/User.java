package org.example.backend.resources;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class User implements Serializable {
    private String login;
    private String password;
    private boolean isRegistered;

    public User(String login, String password, boolean isRegistered) {
        this.login = login;
        this.password = password;
        this.isRegistered = isRegistered;
    }

    public User(){}
}
