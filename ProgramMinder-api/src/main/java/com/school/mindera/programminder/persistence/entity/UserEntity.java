package com.school.mindera.programminder.persistence.entity;

import com.school.mindera.programminder.enumerators.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String encryptedPassword;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String avatarName;

    private String photo;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
}
