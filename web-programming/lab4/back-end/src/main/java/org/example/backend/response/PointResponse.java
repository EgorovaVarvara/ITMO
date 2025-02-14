package org.example.backend.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class PointResponse implements Serializable {
    private Double x;

    private Double y;

    private Double r;

    private boolean isHit;

    private Long executionTime;

    private String serverTime;

    public PointResponse(Double x, Double y, Double r, boolean isHit, Long executionTime, String serverTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.executionTime = executionTime;
        this.serverTime = serverTime;
    }

}
