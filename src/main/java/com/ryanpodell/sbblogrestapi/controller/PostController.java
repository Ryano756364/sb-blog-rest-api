//Some developers also use naming convention such as PostResource because is restful webservices, it becomes a resource
package com.ryanpodell.sbblogrestapi.controller;
import com.ryanpodell.sbblogrestapi.payload.PostDto;
import com.ryanpodell.sbblogrestapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //Developing REST API's so that is why we are using RestController, don't forget this is @Controller and @ResponseBody annotation
@RequestMapping("/api/posts")
public class PostController {
    //Now we will inject service class into this controller
    private PostService postService; //Notice we are injecting an interface; not a class  --> known as loose coupling

    //Will leave out AutoWired because of only one constructor
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Rest endpoint!

    //create blog post API

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){  //converts JSON into Java object
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
}
