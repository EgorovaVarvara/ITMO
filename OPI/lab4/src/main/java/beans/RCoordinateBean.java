package beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;


@Data
@NoArgsConstructor
@ManagedBean(name = "rCoordinateBean")
@SessionScoped
public class RCoordinateBean implements Serializable {
    private Double r = 0.0;
}
