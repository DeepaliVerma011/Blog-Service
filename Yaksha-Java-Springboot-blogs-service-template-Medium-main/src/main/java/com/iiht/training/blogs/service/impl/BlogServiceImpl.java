//package com.iiht.training.blogs.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.iiht.training.blogs.dto.BlogDto;
//import com.iiht.training.blogs.repository.BlogRepository;
//import com.iiht.training.blogs.service.BlogService;
//
//@Service
//public class BlogServiceImpl implements BlogService {
//
//	@Autowired
//	private BlogRepository blogRepository;
//
//	@Override
//	public BlogDto createBlog(BlogDto blogDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public BlogDto getBlogById(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public BlogDto updateBlog(Long id, BlogDto blogDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean deleteBlog(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	
//
//}
//
//
//package com.iiht.training.blogs.service.impl;
//
//import com.iiht.training.blogs.dto.BlogDto;
//import com.iiht.training.blogs.exception.BlogNotFoundException;
//import com.iiht.training.blogs.exception.DuplicateBlogException;
//import com.iiht.training.blogs.exception.InvalidBlogException;
//import com.iiht.training.blogs.model.Blog;
//import com.iiht.training.blogs.repository.BlogRepository;
//import com.iiht.training.blogs.service.BlogService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BlogServiceImpl implements BlogService {
//
//    private final BlogRepository blogRepository;
//
//    @Autowired
//    public BlogServiceImpl(BlogRepository blogRepository) {
//        this.blogRepository = blogRepository;
//    }
//
//    @Override
//    public BlogDto createBlog(BlogDto blogDto) {
//        validateBlogDto(blogDto);
//        if (blogRepository.existsByTitle(blogDto.getTitle())) {
//            throw new DuplicateBlogException("Blog with the same title already exists");
//        }
//        Blog blog = new Blog();
//        BeanUtils.copyProperties(blogDto, blog);
//        Blog savedBlog = blogRepository.save(blog);
//        BlogDto savedBlogDto = new BlogDto();
//        BeanUtils.copyProperties(savedBlog, savedBlogDto);
//        return savedBlogDto;
//    }
//
//    @Override
//    public BlogDto getBlogById(Long id) {
//        Optional<Blog> optionalBlog = blogRepository.findById(id);
//        Blog blog = optionalBlog.orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + id));
//        BlogDto blogDto = new BlogDto();
//        BeanUtils.copyProperties(blog, blogDto);
//        return blogDto;
//    }
//
//    @Override
//    public BlogDto updateBlog(Long id, BlogDto blogDto) {
//        validateBlogDto(blogDto);
//        Blog existingBlog = blogRepository.findById(id)
//                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + id));
//
//        if (!existingBlog.getTitle().equals(blogDto.getTitle()) && blogRepository.existsByTitle(blogDto.getTitle())) {
//            throw new DuplicateBlogException("Blog with the same title already exists");
//        }
//
//        existingBlog.setTitle(blogDto.getTitle());
//        existingBlog.setContent(blogDto.getContent());
//        Blog updatedBlog = blogRepository.save(existingBlog);
//        BlogDto updatedBlogDto = new BlogDto();
//        BeanUtils.copyProperties(updatedBlog, updatedBlogDto);
//        return updatedBlogDto;
//    }
//
//    @Override
//    public Boolean deleteBlog(Long id) {
//        if (!blogRepository.existsById(id)) {
//            throw new BlogNotFoundException("Blog not found with id: " + id);
//        }
//        blogRepository.deleteById(id);
//        return true;
//    }
//
//    private void validateBlogDto(BlogDto blogDto) {
//        if (blogDto.getTitle() == null || blogDto.getTitle().isEmpty()) {
//            throw new InvalidBlogException("Blog title is required");
//        }
//        if (blogDto.getContent() == null || blogDto.getContent().isEmpty()) {
//            throw new InvalidBlogException("Blog content is required");
//        }
//    }
//}

package com.iiht.training.blogs.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.blogs.dto.BlogDto;
import com.iiht.training.blogs.entity.BlogEntiry;
import com.iiht.training.blogs.exceptions.InvalidDataException;
import com.iiht.training.blogs.repository.BlogRepository;
import com.iiht.training.blogs.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        if (blogDto == null) {
            throw new InvalidDataException("Invalid blog data");
        }

        BlogEntiry blogEntity = new BlogEntiry();
        BeanUtils.copyProperties(blogDto, blogEntity);

        BlogEntiry savedBlog = blogRepository.save(blogEntity);

        BlogDto savedBlogDto = new BlogDto();
        BeanUtils.copyProperties(savedBlog, savedBlogDto);

        return savedBlogDto;
    }

    @Override
    public BlogDto getBlogById(Long id) {
        if (id == null) {
            throw new InvalidDataException("Invalid blog ID");
        }

        BlogEntiry blogEntity = blogRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("Blog not found"));

        BlogDto blogDto = new BlogDto();
        BeanUtils.copyProperties(blogEntity, blogDto);

        return blogDto;
    }

    @Override
    public BlogDto updateBlog(Long id, BlogDto blogDto) {
        if (id == null || blogDto == null) {
            throw new InvalidDataException("Invalid blog ID or data");
        }

        BlogEntiry existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("Blog not found"));

        BeanUtils.copyProperties(blogDto, existingBlog);
        BlogEntiry updatedBlog = blogRepository.save(existingBlog);

        BlogDto updatedBlogDto = new BlogDto();
        BeanUtils.copyProperties(updatedBlog, updatedBlogDto);

        return updatedBlogDto;
    }

    @Override
    public Boolean deleteBlog(Long id) {
        if (id == null) {
            throw new InvalidDataException("Invalid blog ID");
        }

        BlogEntiry existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("Blog not found"));

        blogRepository.delete(existingBlog);
        return true;

    }

}

