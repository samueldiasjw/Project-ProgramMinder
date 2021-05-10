package com.school.mindera.programminder.converter;

import com.school.mindera.programminder.command.CreateThreadDto;
import com.school.mindera.programminder.command.CreateUserDto;
import com.school.mindera.programminder.command.ThreadsDetailsDto;
import com.school.mindera.programminder.command.UserDetailsDto;
import com.school.mindera.programminder.persistence.entity.ThreadsEntity;
import com.school.mindera.programminder.persistence.entity.UserEntity;

public class ThreadConverter {

    public static ThreadsEntity fromCreateThreadDtoToThreadEntity(CreateThreadDto createThreadDto) {
        return ThreadsEntity.builder()
                .title(createThreadDto.getTitle())
                .post(createThreadDto.getPost())
                .userId(createThreadDto.getUserId())
                .categoryId(createThreadDto.getCategoryId())
                .build();
    }


    public static ThreadsDetailsDto fromThreadsEntityToThreadDetailsDto(ThreadsEntity threadsEntity) {
        return ThreadsDetailsDto.builder()
                .threadId(threadsEntity.getThreadId())
                .title(threadsEntity.getTitle())
                .post(threadsEntity.getPost())
                .userId(threadsEntity.getUserId())
                .categoryId(threadsEntity.getCategoryId())
                .build();
    }

}
