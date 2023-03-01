package com.ryanpodell.sbblogrestapi.service;

import com.ryanpodell.sbblogrestapi.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
