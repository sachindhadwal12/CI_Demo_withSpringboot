package com.stackroute.controller;

import com.stackroute.domain.Blog;
import com.stackroute.service.BlogServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BlogController {

  @Autowired private BlogServiceImpl blogService;

  @Autowired
  public BlogController(BlogServiceImpl blogService) {
    this.blogService = blogService;
  }

  @PostMapping("/blog")
  public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) {
    Blog savedBlog = this.blogService.saveBlog(blog);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedBlog);
  }

// CI CD
  @GetMapping("/blogs")
  public ResponseEntity<List<Blog>> getAllBlogs() {
    List<Blog> blogs = this.blogService.getAllBlogs();
    return ResponseEntity.status(HttpStatus.OK).body(blogs);
  }

  @GetMapping("blog/{blogId}")
  public ResponseEntity<Blog> getBlogById(@PathVariable("blogId") int blogId) {
    Blog blog = this.blogService.getBlogById(blogId);
    return ResponseEntity.status(HttpStatus.OK).body(blog);
  }

  @DeleteMapping("blog/{blogId}")
  public ResponseEntity<Blog> getBlogAfterDeleting(@PathVariable("blogId") int blogId) {
    this.blogService.deleteBlog(blogId);
    Blog blog = null;
    return ResponseEntity.status(HttpStatus.OK).body(blog);
  }

  @PutMapping("blog")
  public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
    Blog updatedBlog = this.blogService.updateBlog(blog);
    return ResponseEntity.status(HttpStatus.OK).body(updatedBlog);
  }
}
