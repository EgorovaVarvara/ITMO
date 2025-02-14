package org.example.backend.db.user;

import org.example.backend.db.HibernateUtil;
import org.example.backend.resources.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class UserDAO {
    private HibernateUtil hibernateUtil;
    public UserDAO() {
        this.hibernateUtil = new HibernateUtil();
    }

    public UserEntity findByLogin(String login){
        Set<UserEntity> users = findAll();
        for (UserEntity UserEntity: users){
            System.out.println(UserEntity.getId());
            if (UserEntity.getLogin().equals(login)){
                return UserEntity;
            }
        }
        return null;
    }

    public void save(UserEntity user){
        Session session = hibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
    }


    public Set<UserEntity> findAll(){
        Session session = hibernateUtil.getSession();
        List<UserEntity> userList = session.createQuery("FROM UserEntity", UserEntity.class).list();
        Set<UserEntity> users = new HashSet<>(userList);
        return users;
    }
}
