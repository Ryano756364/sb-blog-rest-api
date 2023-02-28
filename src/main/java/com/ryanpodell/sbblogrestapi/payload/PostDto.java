package com.ryanpodell.sbblogrestapi.payload;
import lombok.Data;

@Data  //generates getter, setters, toString
public class PostDto {
    //First look and see what the client requires in the Post entity
    //Then assign fields
    private long id;
    private String title;
    private String description;
    private String content;
}
