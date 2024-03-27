package com.practice.practiceProject.blog.repository;

import com.practice.practiceProject.blog.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

}
