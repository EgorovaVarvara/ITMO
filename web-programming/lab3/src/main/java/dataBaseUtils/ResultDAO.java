package dataBaseUtils;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class ResultDAO {
    private Session session;
    Logger logger = Logger.getLogger(this.getClass().getName());

    public ResultDAO() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public void save(ResultEntity result) {
        Transaction tx = session.beginTransaction();
        session.save(result);
        tx.commit();
    }

    public List<ResultEntity> getAll() {
        logger.info("db meow " + session.isConnected());
        return session.createQuery("From ResultEntity ", ResultEntity.class).getResultList();
    }
}
