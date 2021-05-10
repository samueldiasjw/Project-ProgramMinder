package com.school.mindera.programminder.converter;

import com.school.mindera.programminder.command.PostDetailsDto;
import com.school.mindera.programminder.persistence.entity.PostsEntity;

public class PostConverter {

    public static PostsEntity fromPostDetailsDtoToPostsEntity(PostDetailsDto postDetailsDto) {
        return PostsEntity.builder()
                .post(postDetailsDto.getPost())
                .userId(postDetailsDto.getUserId())
                .threadId(postDetailsDto.getThreadId())
                .build();
    }


    public static PostDetailsDto fromPostsEntityToPostDetailsDto(PostsEntity postsEntity) {
        return PostDetailsDto.builder()
                .postId(postsEntity.getPostId())
                .post(postsEntity.getPost())
                .userId(postsEntity.getUserId())
                .threadId(postsEntity.getThreadId())
                .build();
    }

}
