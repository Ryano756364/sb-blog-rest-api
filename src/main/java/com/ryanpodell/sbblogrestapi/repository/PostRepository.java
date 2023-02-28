package com.ryanpodell.sbblogrestapi.repository;
import com.ryanpodell.sbblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository  -> do not need to add this because JpaRepository internally implements this
public interface PostRepository extends JpaRepository<Post, Long> {  //Since JpaRepo is a generic, need to instantiate parameters
    //JpaRepo has many internal methods like -> find, fina all, page pagination, sorting

}
