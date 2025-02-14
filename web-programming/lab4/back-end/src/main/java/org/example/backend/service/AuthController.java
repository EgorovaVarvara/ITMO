package org.example.backend.service;

import org.example.backend.db.HashManager;
import org.example.backend.db.user.UserService;
import org.example.backend.resources.User;
import org.example.backend.resources.UserEntity;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/main")
public class AuthController {
    @EJB
    private UserService userService;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(User user){
        System.out.println(user.getLogin());
        user.setPassword(HashManager.getSHA256Hash(user.getPassword()));
        UserEntity userEntity = userService.findByLogin(user.getLogin());
        if (userEntity == null){
            user.setRegistered(false);
        }else{
            user.setRegistered(userEntity.getPassword().equals(user.getPassword()));
        }
        return user;
    }

    @POST
    @Path("/registration")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User register(User user){
        user.setPassword(HashManager.getSHA256Hash(user.getPassword()));
        if(userService.findByLogin(user.getLogin()) == null){
            userService.save(user);
            user.setRegistered(true);
        } else{
            user.setRegistered(false);
        }
        System.out.println(user.getLogin() + user.isRegistered());
        return user;
    }
}
