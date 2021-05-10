package com.school.mindera.programminder.persistence.repository;

import com.school.mindera.programminder.persistence.entity.ThreadsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends CrudRepository<ThreadsEntity, Long> {
    @Query(
            value = "SELECT * FROM threads\n" +
                    "WHERE category_id = :categoryId",
            nativeQuery = true
    )
    Iterable<ThreadsEntity> findPostsByCategoryId (
            @Param("categoryId") long categoryId
    );


    @Query(
            value = "SELECT * FROM threads\n" +
                    "WHERE user_id = :userId",
            nativeQuery = true
    )
    Iterable<ThreadsEntity> findThreadsByUserId (
            @Param("userId") long userId
    );
}
