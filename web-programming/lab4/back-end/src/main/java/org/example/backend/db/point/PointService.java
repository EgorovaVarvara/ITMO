package org.example.backend.db.point;

import org.example.backend.resources.Point;
import org.example.backend.resources.PointEntity;
import org.example.backend.resources.UserEntity;
import org.example.backend.response.PointResponse;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Set;

@Stateless
public class PointService {
    @EJB
    private PointDAO pointDAO;

    public PointResponse save(Point point, boolean isHit, long executionTime, String serverTime, UserEntity user) {
        PointEntity pointEntity = new PointEntity(point.getX(), point.getY(), point.getR(), isHit, executionTime, serverTime, user);
        pointDAO.save(pointEntity);
        return new PointResponse(pointEntity.getX(), pointEntity.getY(), pointEntity.getR(),pointEntity.isHit(), pointEntity.getExecutionTime(), pointEntity.getServerTime());
    }

    public Set<PointResponse> findAll(UserEntity user){
        return pointDAO.findAll(user);
    }
}
