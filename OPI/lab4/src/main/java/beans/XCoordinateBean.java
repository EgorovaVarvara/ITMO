package beans;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "xCoordinateBean")
@SessionScoped
public class XCoordinateBean implements Serializable {
    private Double x;

    public Double getX() {
        return x;
    }

    public void setX(Double xCoordinate) {
        this.x = xCoordinate;
    }
}
