package MBeans;


public interface PointsStatMBean {
    int getTotalPoints();

    int getPointsInArea();

    void addPoint(boolean isInArea);

    int getCurrentMisses();
}
