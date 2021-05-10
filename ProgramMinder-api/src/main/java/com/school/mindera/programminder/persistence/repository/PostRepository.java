package com.school.mindera.programminder.persistence.repository;

import com.school.mindera.programminder.persistence.entity.PostsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostsEntity, Long> {
    @Query(
            value = "SELECT * FROM posts\n" +
                    "WHERE thread_id = :threadId",
            nativeQuery = true
    )
    Iterable<PostsEntity> findPostsByThreadId (
            @Param("threadId") long threadId
    );


    @Query(
            value = "SELECT CASE WHEN EXISTS (" +
                    "SELECT * FROM posts\n" +
                    "WHERE thread_id = :threadId" +
                    ") then 'true' else 'false' end",
            nativeQuery = true
    )

    boolean checkIfExistsPostsWithThatThread (
            @Param("threadId") long threadId
    );
}
