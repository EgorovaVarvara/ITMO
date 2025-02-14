package org.example.backend.db.user;

import org.example.backend.resources.User;
import org.example.backend.resources.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserService {
    @EJB
    private UserDAO userDAO;

    public void save(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userDAO.save(userEntity);
    }

    public UserEntity findByLogin(String login) {
        return userDAO.findByLogin(login);
    }
}
