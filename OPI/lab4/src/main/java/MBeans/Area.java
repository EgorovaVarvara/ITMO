package MBeans;

import dataBaseUtils.ResultDAO;
import dataBaseUtils.ResultEntity;
import utils.MBeanRegistry;

import javax.enterprise.context.Destroyed;
import javax.enterprise.event.Observes;
import javax.faces.bean.ApplicationScoped;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class Area implements AreaMBean {
    private Logger logger = Logger.getLogger(Area.class.getName());
    private List<ResultEntity> results;
    private List<ResultEntity> lastPoints;

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object unused) {
        MBeanRegistry.unregisterBean(this);
    }

    public void updateLocal(List<ResultEntity> results) {
        this.results = results;
        int size = results.size();
        int fromIndex = Math.max(0, size - 4);

        this.lastPoints = new ArrayList<>(results.subList(fromIndex, size));
        if (lastPoints.size() == 4) {
            double centerX = (lastPoints.get(2).getX() + lastPoints.get(3).getX() + lastPoints.get(0).getX() + lastPoints.get(1).getX()) / 4;
            double centerY = (lastPoints.get(0).getY() + lastPoints.get(1).getY() + lastPoints.get(2).getY() + lastPoints.get(3).getY()) / 4;

            lastPoints.sort(Comparator.comparingDouble(p -> Math.atan2(p.getY() - centerY, p.getX() - centerX)));
        }
        logger.info("Updated lastPoints with " + lastPoints.size() + " points");
    }

    @Override
    public double getArea() {
        if (lastPoints == null || lastPoints.size() < 3) {
            return 0.0;
        }

        double x1 = lastPoints.get(0).getX();
        double y1 = lastPoints.get(0).getY();
        double x2 = lastPoints.get(1).getX();
        double y2 = lastPoints.get(1).getY();
        double x3 = lastPoints.get(2).getX();
        double y3 = lastPoints.get(2).getY();
        if (lastPoints.size() == 3) {
            double a = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            double b = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
            double c = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
            double p = (a + b + c) / 2;
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        } else {
            double x4 = lastPoints.get(3).getX();
            double y4 = lastPoints.get(3).getY();
            return 0.5 * Math.abs(
                    (x1 - x2) * (y1 + y2) +
                            (x2 - x3) * (y2 + y3) +
                            (x3 - x4) * (y3 + y4) +
                            (x4 - x1) * (y4 + y1)
            );
        }
    }
}
