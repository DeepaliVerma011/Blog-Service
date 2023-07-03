package com.iiht.training.blogs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iiht.training.blogs.dto.CommentDto;
import com.iiht.training.blogs.entity.BlogEntiry;
import com.iiht.training.blogs.entity.CommentEntity;
import com.iiht.training.blogs.exceptions.InvalidDataException;
import com.iiht.training.blogs.repository.BlogRepository;
import com.iiht.training.blogs.repository.CommentRepository;
import org.springframework.web.server.ResponseStatusException;

import com.iiht.training.blogs.service.CommentService;

//
//@Service
//public class CommentServiceImpl implements CommentService {
//
//	@Autowired
//	private CommentRepository commentRepository;
//
//	@Override
//	public CommentDto postComment(CommentDto commentDto) {
//		return null;
//	}
//
//}
//

//}
//
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public CommentDto postComment(CommentDto commentDto) {
        validateCommentDto(commentDto); // Validate the CommentDto

        Long blogId = commentDto.getBlogId();
        BlogEntiry blog = blogRepository.findById(blogId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found with id: " + blogId));

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDto.getComment());

        CommentEntity createdCommentEntity = commentRepository.save(commentEntity);

        CommentDto createdCommentDto = new CommentDto();
        createdCommentDto.setId(createdCommentEntity.getId());
        createdCommentDto.setComment(createdCommentEntity.getContent());
        createdCommentDto.setBlogId(blogId);

        return createdCommentDto;
    }

    private void validateCommentDto(CommentDto commentDto) {
        // Validate blogId
        if (commentDto.getBlogId() == null) {
            throw new InvalidDataException("Blog ID is required");
        }

        // Validate comment length
        String comment = commentDto.getComment();
        if (comment == null || comment.length() < 3 || comment.length() > 200) {
            throw new InvalidDataException("Comment must be between 3 and 200 characters");
        }
    }
}
