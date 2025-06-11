package utils;

import lombok.experimental.UtilityClass;

import javax.management.*;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@UtilityClass
public class MBeanRegistry implements ServletContextListener {
    Logger logger = Logger.getLogger("MBeanRegistry");
    private final Map<Class<?>, ObjectName> beans = new HashMap<>();

    public void registerBean(Object bean, String name) {
        logger.info("Try to register " + bean.getClass().getName() + " with name " + name);
        try {
            var domain = bean.getClass().getPackageName();
            var type = bean.getClass().getSimpleName();
            var objectName = new ObjectName(String.format("%s:type=%s,name=%s", domain, type, name));

            ManagementFactory.getPlatformMBeanServer().registerMBean(bean, objectName);
            beans.put(bean.getClass(), objectName);
            logger.info("MBean registered:" + name);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException |
                 MalformedObjectNameException ex) {
            logger.severe("Failed to register MBean: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void unregisterBean(Object bean) {
        if (!beans.containsKey(bean.getClass())) {
            throw new IllegalArgumentException("Specified bean is not registered.");
        }

        try {
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(beans.get(bean.getClass()));
        } catch (InstanceNotFoundException | MBeanRegistrationException ex) {
            ex.printStackTrace();
        }
    }
}
