package org.example.backend.resources;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name="users")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<PointEntity> points;

    public UserEntity(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UserEntity() {}

}
