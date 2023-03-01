package com.ryanpodell.sbblogrestapi.service.impl;
import com.ryanpodell.sbblogrestapi.entity.Post;
import com.ryanpodell.sbblogrestapi.exception.ResourceNotFoundException;
import com.ryanpodell.sbblogrestapi.payload.PostDto;
import com.ryanpodell.sbblogrestapi.repository.PostRepository;
import com.ryanpodell.sbblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);  //this saves to database and returns Post

        // Then we need to send to API
        //convert entity to DTO
        PostDto postResponse = mapToDto(newPost);  //using helper method here
        return postResponse;
        //Now we will inject this class into controller which will call the method
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        //Now we need to convert list of 'posts' into list of 'PostDto'
        List<PostDto> toReturn = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        return toReturn;  //redundant but used for easy reading
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = checkIfPostExistsById(id);
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        //Need to get post by id, update fields, and then save to database
        //If post doesn't exist with ID, we'll throw the exception
        Post post = checkIfPostExistsById(id);

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = checkIfPostExistsById(id);
        postRepository.delete(post);
    }


    //Helper methods
    private Post checkIfPostExistsById(long id){
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    private PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
    //End helper methods
}
