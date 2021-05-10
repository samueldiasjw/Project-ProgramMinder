package com.school.mindera.programminder.service;

import com.school.mindera.programminder.command.CreateThreadDto;
import com.school.mindera.programminder.command.ThreadsDetailsDto;
import com.school.mindera.programminder.converter.ThreadConverter;
import com.school.mindera.programminder.error.ErrorMessages;
import com.school.mindera.programminder.exception.DataBaseCommunicationException;
import com.school.mindera.programminder.exception.ThreadIdFoundException;
import com.school.mindera.programminder.exception.ThreadWithThisCategoyIdNotFoundException;
import com.school.mindera.programminder.exception.ThreadWithThisUserIdNotFoundException;
import com.school.mindera.programminder.persistence.entity.ThreadsEntity;
import com.school.mindera.programminder.persistence.repository.ThreadRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThreadService {
    private static final Logger LOGGER = LogManager.getLogger(ThreadService.class);
    private final ThreadRepository threadRepository;

    /**
     * Constructor
     * @param threadRepository
     */
    public ThreadService(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    /**
     * Create Thread in specific category
     * @param createThreadDto
     * @return
     */
    public ThreadsDetailsDto createThreadInCategory(CreateThreadDto createThreadDto) {
        ThreadsEntity threadsEntity = ThreadConverter.fromCreateThreadDtoToThreadEntity(createThreadDto);

        LOGGER.debug("Persisting thread into database");
        try {
            LOGGER.info("Saving Thread on database");
            threadRepository.save(threadsEntity);
        } catch (Exception e) {
            LOGGER.error("Failed while saving Thread into database {}", createThreadDto, e);
            throw new DataBaseCommunicationException(ErrorMessages.DATABASE_COMMUNICATION_ERROR, e);
        }

        return ThreadConverter.fromThreadsEntityToThreadDetailsDto(threadsEntity);
    }

    /**
     * Get all threads from Database with specific CategoryId.
     * @param categoryId
     * @return
     */
    public List<ThreadsDetailsDto> getAllThreadsWithCategoryId(long categoryId) {
        Iterable<ThreadsEntity> threadsListWithSpecificCategoryId = threadRepository.findPostsByCategoryId(categoryId);

        if(threadsListWithSpecificCategoryId.toString().equals("[]")){
            LOGGER.info("This CategoryId don't exist or Threads are not createad in this Category!");
            throw new ThreadWithThisCategoyIdNotFoundException(ErrorMessages.DONT_EXIST_THREADS);
        }

        List<ThreadsDetailsDto> threadDetailsDtoList = new ArrayList<>();

        for (ThreadsEntity thread : threadsListWithSpecificCategoryId) {
            threadDetailsDtoList.add(ThreadConverter.fromThreadsEntityToThreadDetailsDto(thread));
        }

        LOGGER.info("Threads Finded, returning!");
        return threadDetailsDtoList;
    }

    /**
     * Get detailed information from thread with specific THREAD ID.
     * @param threadId
     * @return
     */
    public ThreadsDetailsDto getThreadById(long threadId) {
        ThreadsEntity threadsEntity = threadRepository.findById(threadId)
                .orElseThrow(() -> {
                    LOGGER.error("Try to get a Thread don't exist, Thread ID - {}", threadId);
                    return new ThreadIdFoundException(ErrorMessages.THREAD_ID_NOT_FOUND);
                });

        return ThreadConverter.fromThreadsEntityToThreadDetailsDto(threadsEntity);
    }

    /**
     * Get all threads posted by User. Needed User Id
     * @param userId
     * @return
     */
    public List<ThreadsDetailsDto> getThreadsByUserId(long userId) {
        Iterable<ThreadsEntity> threadListSpecificUserId = threadRepository.findThreadsByUserId(userId);

        if(threadListSpecificUserId.toString().equals("[]")){
            LOGGER.info("This UserId don't exist or Threads are not createad in this Category!");
            throw new ThreadWithThisUserIdNotFoundException(ErrorMessages.DONT_EXIST_THREADS_WITH_THIS_USER_ID);
        }

        List<ThreadsDetailsDto> threadDetailsDtoList = new ArrayList<>();

        for (ThreadsEntity thread : threadListSpecificUserId) {
            threadDetailsDtoList.add(ThreadConverter.fromThreadsEntityToThreadDetailsDto(thread));
        }

        LOGGER.info("Threads Finded, returning!");
        return threadDetailsDtoList;
    }
}
