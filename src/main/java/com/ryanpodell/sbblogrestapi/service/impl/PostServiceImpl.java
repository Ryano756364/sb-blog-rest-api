package com.ryanpodell.sbblogrestapi.service.impl;
import com.ryanpodell.sbblogrestapi.entity.Post;
import com.ryanpodell.sbblogrestapi.payload.PostDto;
import com.ryanpodell.sbblogrestapi.repository.PostRepository;
import com.ryanpodell.sbblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {  //the plan is to inject this class into other classes

    //Plan is to use constructor-based-dependency injection
    private PostRepository postRepository;

    @Autowired  //If class is configured as Spring Bean and only one constructor, AutoWired is not needed
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto){

        // convert DTO to entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);  //this saves to database and returns Post

        // Then we need to send to API
        //convert entity to DTO
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
        //Now we will inject this class into controller which will call the method
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }
}
