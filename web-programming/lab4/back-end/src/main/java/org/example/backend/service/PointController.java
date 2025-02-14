package org.example.backend.service;

import org.example.backend.db.point.PointService;
import org.example.backend.db.user.UserService;
import org.example.backend.resources.Point;
import org.example.backend.resources.User;
import org.example.backend.resources.UserEntity;
import org.example.backend.response.PointResponse;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

@Path("/points")
public class PointController {
    @EJB
    private PointService pointService;

    @EJB
    private UserService userService;
    long startTime = System.nanoTime();
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDate = now.format(formatter);

    @POST
    @Path("/check-point")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PointResponse check(Point point) {
        System.out.println(point.getX() + point.getY() + point.getR());
        boolean isHit = HitChecker.hit(point.getX(), point.getY(), point.getR());
        UserEntity userEntity = userService.findByLogin(point.getLogin());
        System.out.println(point.getLogin());
        try {
            return pointService.save(point, isHit, System.nanoTime() - startTime, formattedDate, userEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @POST
    @Path("/getpoint")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<PointResponse> getpoint(User user) {
        try {
            UserEntity userEntity = userService.findByLogin(user.getLogin());
            return pointService.findAll(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
