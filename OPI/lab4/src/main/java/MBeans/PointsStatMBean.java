package MBeans;

import javax.management.MXBean;

@MXBean
public interface PointsStatMBean {
    int getTotalPoints();
    int getPointsInArea();
    void addPoint(boolean isInArea);
    int getCurrentMisses();
}
