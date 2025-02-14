package org.example.backend.resources;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@Table(name = "user_points")
public class PointEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double x;
    private Double y;
    private Double r;
    private boolean isHit;
    private Long executionTime;
    private String serverTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public PointEntity(Double x, Double y, Double r, boolean isHit, Long executionTime, String serverTime, UserEntity user) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.executionTime = executionTime;
        this.serverTime = serverTime;
        this.user = user;
    }

    public PointEntity() {
    }
}
