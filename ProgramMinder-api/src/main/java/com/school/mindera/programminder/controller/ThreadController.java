package com.school.mindera.programminder.controller;

import com.school.mindera.programminder.command.CreateThreadDto;
import com.school.mindera.programminder.command.ThreadsDetailsDto;
import com.school.mindera.programminder.service.ThreadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/threads")
public class ThreadController {
    private static final Logger LOGGER = LogManager.getLogger(ThreadService.class);

    private final ThreadService threadService;

    /**
     * Constructor
     * @param threadService
     */
    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    /**
     * User is trying to create a Thread in Category
     * @param createThreadDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ThreadsDetailsDto> createThread(@Valid @RequestBody CreateThreadDto createThreadDto) {
        LOGGER.info("Request to create thread - {}.", createThreadDto);

        ThreadsDetailsDto threadsDetailsDto =  threadService.createThreadInCategory(createThreadDto);

        LOGGER.debug("Service retrieved created thread {}", threadsDetailsDto);
        return new ResponseEntity<>(threadsDetailsDto, HttpStatus.CREATED);
    }

    /**
     * User is accessing a specific category and wanna know what threads exist
     * @param categoryId
     * @return
     */
    @GetMapping("/categoryid/{categoryId}")
    public ResponseEntity<List<ThreadsDetailsDto>> getAllThreadsWithCategoryId(@PathVariable long categoryId) {
        LOGGER.info("Request Threads with CategoryId - {}", categoryId);

        List<ThreadsDetailsDto> listOfThreadsDetailsDto = threadService.getAllThreadsWithCategoryId(categoryId);

        return new ResponseEntity<>(listOfThreadsDetailsDto, HttpStatus.OK);
    }

    /**
     * User enter in a specific thread and wanna read content
     * @param threadId
     * @return
     */
    @GetMapping("/{threadId}")
    public ResponseEntity<ThreadsDetailsDto> getThreadById(@PathVariable long threadId) {
        LOGGER.info("Request thread with Id - {}", threadId);

        ThreadsDetailsDto threadsDetailsDto = threadService.getThreadById(threadId);

        return new ResponseEntity<>(threadsDetailsDto, HttpStatus.OK);
    }

    /**
     * Get data from all threads user create. You can see that in Account.
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ThreadsDetailsDto>> getThreadsByUserId(@PathVariable long userId) {
        LOGGER.info("Request User with Id - {}", userId);

        List<ThreadsDetailsDto> threadsEntityList = threadService.getThreadsByUserId(userId);

        return new ResponseEntity<>(threadsEntityList, HttpStatus.OK);
    }
}
