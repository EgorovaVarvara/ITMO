package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ManagedBean(name = "yCoordinateBean")
@SessionScoped
public class YCoordinateBean implements Serializable {
    private Double y;
}
