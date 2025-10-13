package com.stackroute.service;

import com.stackroute.domain.Blog;
import com.stackroute.repository.BlogRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
  @Autowired private BlogRepository blogRepository;

  @Autowired
  public BlogServiceImpl(BlogRepository blogRepository) {
    this.blogRepository = blogRepository;
  }

  public Blog saveBlog(Blog blog) {
    Blog savedBlog = this.blogRepository.save(blog);
    return savedBlog;
  }

  public List<Blog> getAllBlogs() {
    List<Blog> list = this.blogRepository.findAll();
    if (list.size() == 0) {
      System.out.println("No blogs found");
    }
    return list;
  }

  public Blog getBlogById(int id) {
    Optional<Blog> blog = this.blogRepository.findById(id);
    if (blog.isPresent()) {
      return blog.get();
    } else {
      System.out.println("No blog found");
      return null;
    }
  }

  /** delete blog by id */
  public Blog deleteBlog(int id) {
    Optional<Blog> blog = this.blogRepository.findById(id);
    if (blog.isPresent()) {
      this.blogRepository.deleteById(id);
      return blog.get();
    } else {
      System.out.println("No blog found");
      return null;
    }
  }

  /** update blog */
  public Blog updateBlog(Blog blog) {
    Optional<Blog> blog1 = this.blogRepository.findById(blog.getBlogId());
    if (blog1.isPresent()) {
      Blog blog2 = blog1.get();
      blog2.setBlogContent(blog.getBlogContent());
      blog2.setBlogTitle(blog.getBlogTitle());
      blog2.setAuthorName(blog.getAuthorName());
      this.blogRepository.save(blog2);
      return blog2;
    } else {
      System.out.println("No blog found");
      return null;
    }
  }
}
