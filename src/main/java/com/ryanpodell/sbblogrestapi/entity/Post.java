package com.ryanpodell.sbblogrestapi.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //adds all getters and setters
@AllArgsConstructor  //name describes itself
@NoArgsConstructor  //same with this one
@Entity
@Table(  //if no name is provided here, then name from class will be used automatically
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "title", nullable = false) //if column name isn't provided here, then it will be name of field used instead
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;


}
