package com.school.mindera.programminder.persistence.repository;

import com.school.mindera.programminder.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    @Query(
            value = "SELECT * FROM users\n" +
                    "INNER JOIN posts ON users.`user_id` = posts.`user_id`\n" +
                    "WHERE posts.`thread_id` = :threadId",
            nativeQuery = true
    )
    Iterable<UserEntity> findPostsByThreadId (
            @Param("threadId") long threadId
    );

    Optional<UserEntity> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE role = :role",
            countQuery = "SELECT * FROM users WHERE role = :role",
            nativeQuery = true)
    Page<UserEntity> findAllByRole(@Param("role") String role, Pageable pageable);





    @Query(
            value = "SELECT * FROM users\n" +
                    "INNER JOIN threads ON users.`user_id` = threads.`user_id`\n" +
                    "WHERE threads.`category_id` = :categoryId ORDER BY threads.`thread_id`",
            nativeQuery = true
    )
    Iterable<UserEntity> findPostsByCategoryId (
            @Param("categoryId") long categoryId
    );
}
