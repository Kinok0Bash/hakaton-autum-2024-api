package com.kinok0.fileexportservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "position")
    private String position;

    @Column(name = "avatar")
    private byte[] avatar;

    @OneToMany(mappedBy = "employee")
    private List<TaskEntity> tasks;

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "user")
    private List<RefreshTokenEntity> tokens;

    public UserEntity(String name, String login,
                      String password, Role role,
                      String position, byte[] avatar) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.position = position;
        this.avatar = avatar;
    }
}
