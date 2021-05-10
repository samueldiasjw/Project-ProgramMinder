package com.school.mindera.programminder.controller;

import com.school.mindera.programminder.command.PostDetailsDto;
import com.school.mindera.programminder.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private static final Logger LOGGER = LogManager.getLogger(PostService.class);

    private final PostService postService;

    /**
     * Constructor
     * @param postService
     */
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * User creating post in specific thread
     * @param postDetailsDto
     * @return
     */
    @PostMapping
    public ResponseEntity<PostDetailsDto> createPost(@Valid @RequestBody PostDetailsDto postDetailsDto) {
        LOGGER.info("Request to create post - {}.", postDetailsDto);

        PostDetailsDto postDto =  postService.createPostInThread(postDetailsDto);

        LOGGER.debug("Service retrieved created post {}", postDto);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    /**
     * Request all posts with specific thread Id. Thread - title --> Posts - Thread
     * @param threadId
     * @return
     */
    @CrossOrigin
    @GetMapping("/{threadId}")
    public ResponseEntity<List<PostDetailsDto>> getPostsWithThreadId(@PathVariable long threadId) {
        LOGGER.info("Request Posts with ThreadId - {}", threadId);

        List<PostDetailsDto> listOfPosts = postService.getAllPostsWithThreadId(threadId);

        return new ResponseEntity<>(listOfPosts, HttpStatus.OK);
    }
}
