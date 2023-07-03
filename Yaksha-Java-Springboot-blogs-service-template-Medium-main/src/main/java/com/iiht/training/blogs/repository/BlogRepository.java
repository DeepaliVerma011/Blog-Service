package com.iiht.training.blogs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.blogs.entity.BlogEntiry;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntiry, Long> {

}
