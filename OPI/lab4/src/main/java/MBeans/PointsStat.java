package MBeans;

import dataBaseUtils.ResultEntity;
import lombok.Getter;
import lombok.Setter;
import utils.MBeanRegistry;

import javax.enterprise.context.Destroyed;
import javax.enterprise.event.Observes;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Getter
@Setter
@Named("stats")
@ApplicationScoped
public class PointsStat extends NotificationBroadcasterSupport implements PointsStatMBean, Serializable {
    Logger logger = Logger.getLogger(PointsStat.class.getName());
    private int missStreak = 0;
    private long sequenceNumber = 1;
    private List<ResultEntity> results;
    private int total;
    private int inArea;

    @Override
    public synchronized void addPoint(boolean isInArea) {
        total++;
        if (isInArea) {
            inArea++;
            missStreak = 0;
        } else {
            missStreak++;
            if (missStreak >= 4) {
                logger.info("Sending notification about 4 misses");
                Notification n = new Notification(
                        "miss.streak.notification", this, sequenceNumber++,
                        "User made 4 misses in a row"
                );
                sendNotification(n);
                missStreak = 0;
            }
        }
    }

    @Override
    public int getCurrentMisses() {
        return missStreak;
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object unused) {
        MBeanRegistry.unregisterBean(this);
    }

    public void updateLocal(List<ResultEntity> results) {
        this.results = results;
        total = results.size();
        inArea = results.stream().filter(ResultEntity::isResult).toList().size();
    }

    @Override
    public int getTotalPoints() {
        return total;
    }

    @Override
    public int getPointsInArea() {
        return inArea;
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{AttributeChangeNotification.ATTRIBUTE_CHANGE};
        String name = AttributeChangeNotification.class.getName();
        String description = "Miss notification";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }

}
