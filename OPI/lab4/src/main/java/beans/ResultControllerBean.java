package beans;

import MBeans.Area;
import MBeans.PointsStat;
import dataBaseUtils.ResultDAO;
import lombok.Data;
import dataBaseUtils.ResultEntity;
import org.primefaces.PrimeFaces;
import utils.HitChecker;
import utils.MBeanRegistry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Data
@ManagedBean(name = "ControllerBean")
@ApplicationScoped
public class ResultControllerBean implements Serializable {
    Logger logger = Logger.getLogger(this.getClass().getName());
    private ResultDAO resultDAO = new ResultDAO();
    private List<ResultEntity> results = resultDAO.getAll();
    boolean isHit = false;

    private final PointsStat pointsStat = new PointsStat();
    private final Area area = new Area();

    @PostConstruct
    public void init() {
        logger.info("meow1 " + results.size());
        final String[] info = {""};
        results.forEach(r -> info[0] += (r.getX() + " " + r.getY() + " " + r.getR() + " " + r.isResult() + "\n"));
        logger.info(info[0]);
        logger.info("initializing...");
        MBeanRegistry.registerBean(pointsStat, "stats");
        MBeanRegistry.registerBean(area, "area");
        pointsStat.updateLocal(results);
        area.updateLocal(results);
    }


    public static double truncate(double value, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return (Math.floor(value * factor) / factor);
    }

    public void addResult(Double x, Double y, Double r) {
        x = truncate(x, 3);
        y = truncate(y, 3);
        logger.info("meow2");
        ResultEntity entity = ResultEntity.builder().x(x).y(y).r(r).result(HitChecker.hit(x, y, r)).build();
        results.add(entity);
        pointsStat.updateLocal(results);
        area.updateLocal(results);
        pointsStat.addPoint(HitChecker.hit(x, y, r));
        PrimeFaces.current().executeScript("drawDot(" + entity.getX() + "," + entity.getY() + "," + entity.isResult() + ")");
        resultDAO.save(entity);


        System.out.printf("Added new result to the db: X=%f, Y=%f, R=%f", x, y, r);
    }
}
