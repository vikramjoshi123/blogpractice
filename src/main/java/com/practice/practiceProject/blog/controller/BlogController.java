package com.practice.practiceProject.blog.controller;

import com.practice.practiceProject.blog.entity.BlogEntity;
import com.practice.practiceProject.blog.repository.BlogRepository;
import com.practice.practiceProject.utils.handler.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class BlogController {
    @Autowired
    BlogRepository blogRepository;
    Logger logger = LoggerFactory.getLogger(BlogController.class);

    @GetMapping("/blog")
    public ResponseEntity<Object> getAllBlogs() {
        try {
            List<BlogEntity> blogEntityList = new ArrayList<>();
            blogEntityList = blogRepository.findAll();

            logger.info("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")) + " - BlogController - getAllBlogs - Response - " + blogEntityList.toString());

            return ResponseHandler.response("Success", blogEntityList, HttpStatus.OK);
        } catch (Error e) {
            return ResponseHandler.response("Error", "", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/blog")
    public ResponseEntity<Object> saveBlog(@RequestBody BlogEntity blog) {
        try {
            BlogEntity blogEntity = blogRepository.save(blog);

            logger.info("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")) + " - BlogController - saveBlog - Response - " + blogEntity);

            return ResponseHandler.response("Success", blogEntity, HttpStatus.OK);
        } catch (Error e) {

            return ResponseHandler.response("Error", "", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/blog/{blogId}")
    public ResponseEntity<Object> deleteBlog(@PathVariable Long blogId) {
        try {
            if (blogRepository.findById(blogId).isPresent()) {
                blogRepository.deleteById(blogId);
                logger.info("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")) + " - BlogController - deleteBlog - Response - " + "Deleted Successfully!");

                return ResponseHandler.response("Success", "Deleted Successfully!", HttpStatus.OK);
            } else {
                logger.info("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")) + " - BlogController - deleteBlog - Response - " + "Already Deleted Or No data found!");

                return ResponseHandler.response("Already Deleted", "Already Deleted Or No data found!", HttpStatus.BAD_REQUEST);
            }

        } catch (Error e) {

            return ResponseHandler.response("Error", "", HttpStatus.NO_CONTENT);
        }
    }


}
