package com.school.mindera.programminder.service;

import com.school.mindera.programminder.command.PostDetailsDto;
import com.school.mindera.programminder.converter.PostConverter;
import com.school.mindera.programminder.error.ErrorMessages;
import com.school.mindera.programminder.exception.DataBaseCommunicationException;
import com.school.mindera.programminder.exception.ThreadIdFoundException;
import com.school.mindera.programminder.persistence.entity.PostsEntity;
import com.school.mindera.programminder.persistence.repository.PostRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private static final Logger LOGGER = LogManager.getLogger(PostService.class);
    private final PostRepository postRepository;

    /**
     * Constructor
     * @param postRepository
     */
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Try to create a post with specific thread.
     * @param postDetailsDto
     * @return
     */
    public PostDetailsDto createPostInThread(PostDetailsDto postDetailsDto) {
        PostsEntity postsEntity = PostConverter.fromPostDetailsDtoToPostsEntity(postDetailsDto);

        LOGGER.debug("Persisting Post into database");
        try {
            LOGGER.info("Saving Post on database");
            postRepository.save(postsEntity);
        } catch (Exception e) {
            LOGGER.error("Failed while saving Post into database {}", postDetailsDto, e);
            throw new DataBaseCommunicationException(ErrorMessages.DATABASE_COMMUNICATION_ERROR, e);
        }

        return PostConverter.fromPostsEntityToPostDetailsDto(postsEntity);
    }

    /**
     * Get all posts from database with specific thread. FrontEnd will put thread title with that posts.
     * @param threadId
     * @return
     * @throws ThreadIdFoundException
     */
    public List<PostDetailsDto> getAllPostsWithThreadId(long threadId) throws ThreadIdFoundException {
        if (postRepository.checkIfExistsPostsWithThatThread(threadId)){
            Iterable<PostsEntity> postsListSpecificThreadId = postRepository.findPostsByThreadId(threadId);

            List<PostDetailsDto> postingDetailsDtoList = new ArrayList<>();

            for (PostsEntity post : postsListSpecificThreadId) {
                postingDetailsDtoList.add(PostConverter.fromPostsEntityToPostDetailsDto(post));
            }

            LOGGER.info("Posts with specific ThreadId finded!");

            return postingDetailsDtoList;
        }
        LOGGER.error("Posts Not Found or ThreadId don't exist!");
        throw new ThreadIdFoundException(ErrorMessages.THREAD_ID_NOT_FOUND);
    }
}
