package org.example.backend.db.point;

import org.example.backend.db.HibernateUtil;
import org.example.backend.resources.PointEntity;
import org.example.backend.resources.UserEntity;
import org.example.backend.response.PointResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class PointDAO {
    private HibernateUtil hibernateUtil;
    public PointDAO() {
        this.hibernateUtil = new HibernateUtil();
    }

    public void save(PointEntity point) {
        try{
            Session session = hibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(point);
            tx.commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Set<PointResponse> findAll(UserEntity userModel){
        Session session = hibernateUtil.getSession();;
        List<PointEntity> pointList = session.createQuery("FROM PointEntity p WHERE p.user = :user", PointEntity.class)
                .setParameter("user", userModel)
                .list();
        Set<PointEntity> points = new HashSet<>(pointList);
        Set<PointResponse> pointResponse = new HashSet<>();
        for (PointEntity point : points) {
            pointResponse.add(new PointResponse(point.getX(), point.getY(), point.getR(), point.isHit(), point.getExecutionTime(), point.getServerTime()));
        }
        return pointResponse;
    }
}
