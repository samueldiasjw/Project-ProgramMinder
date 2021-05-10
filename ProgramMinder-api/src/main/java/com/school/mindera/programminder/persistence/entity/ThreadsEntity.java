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
@Table(name = "threads")
public class ThreadsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long threadId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String post;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private int categoryId;
}
