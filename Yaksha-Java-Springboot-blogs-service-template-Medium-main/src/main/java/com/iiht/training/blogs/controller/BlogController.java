//package com.iiht.training.blogs.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.iiht.training.blogs.service.BlogService;
//import com.iiht.training.blogs.service.CommentService;
//
//@RestController
//@RequestMapping("/api/blogs")
//public class BlogController {
//
//	@Autowired
//	private BlogService blogService;
//
//	@Autowired
//	private CommentService commentService;
//
//	
//}

package com.iiht.training.blogs.controller;

import com.iiht.training.blogs.dto.BlogDto;
import com.iiht.training.blogs.dto.CommentDto;
import com.iiht.training.blogs.exceptions.BlogNotFoundException;
import com.iiht.training.blogs.exceptions.InvalidDataException;
import com.iiht.training.blogs.service.BlogService;
import com.iiht.training.blogs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto blogDto) {
        // Implement the createBlog method
    
        if (blogDto.getTitle() == null || blogDto.getTitle().length() < 3 || blogDto.getTitle().length() > 100) {
            throw new InvalidDataException("Invalid blog title. It should have 3 to 100 characters.");
        }
        if (blogDto.getContent() == null || blogDto.getContent().length() < 3 || blogDto.getContent().length() > 200) {
            throw new InvalidDataException("Invalid blog content. It should have 3 to 200 characters.");
        }

        BlogDto createdBlog = blogService.createBlog(blogDto);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable("id") Long id) {
        // Implement the getBlogById method
        BlogDto blog = blogService.getBlogById(id);
        if (blog != null) {
            return new ResponseEntity<>(blog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable("id") Long id, @RequestBody BlogDto blogDto) {
       
        if (blogDto.getTitle() == null || blogDto.getTitle().length() < 3 || blogDto.getTitle().length() > 100) {
            throw new InvalidDataException("Invalid blog title. It should have 3 to 100 characters.");
        }
        if (blogDto.getContent() == null || blogDto.getContent().length() < 3 || blogDto.getContent().length() > 200) {
            throw new InvalidDataException("Invalid blog content. It should have 3 to 200 characters.");
        }
        BlogDto updatedBlog = blogService.updateBlog(id, blogDto);
        return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
    }


    
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBlog(@PathVariable Long id) {
		Boolean isDeleted = blogService.deleteBlog(id);
		return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}

    
    @PostMapping("/comment")
    public ResponseEntity<CommentDto> postComment(@Valid @RequestBody CommentDto commentDto) {

        if(getBlogById(commentDto.getBlogId())==null){
            throw new BlogNotFoundException();
        }
        if (commentDto.getId() == null) {
            throw new InvalidDataException("Invalid blog title. It should have 3 to 100 characters.");
        }
        if (commentDto.getComment() == null || commentDto.getComment().length() < 3 || commentDto.getComment().length() > 200) {
            throw new InvalidDataException("Invalid blog content. It should have 3 to 200 characters.");
        }

        CommentDto createdComment = commentService.postComment(commentDto);
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }
    // Add other rest-endpoints for Blog and Comment related activities here

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Implement the handleException method
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidDataException(InvalidDataException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    

    
    
    // Add other local exception handler methods here

}