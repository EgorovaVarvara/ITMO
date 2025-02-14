package org.example.backend.resources;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class Point implements Serializable {
    private Double x;
    private Double y;
    private Double r;
    private String login;

    public Point(Double x, Double y, Double r, String login) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.login = login;
    }

    public Point() {}
}
