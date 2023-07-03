package com.iiht.training.blogs.testutils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iiht.training.blogs.dto.BlogDto;
import com.iiht.training.blogs.dto.CommentDto;

public class MasterData {

	public static BlogDto getBlogDto() {
		BlogDto blogDto = new BlogDto();
		blogDto.setId(1L);
		blogDto.setTitle("Machine Learning");
		blogDto.setContent("Machine Learning is in the trends now a days");
		return blogDto;
	}

	public static List<BlogDto> getBlogDtoList() {
		List<BlogDto> blogDtos = new ArrayList<>();
		BlogDto blogDto = new BlogDto();
		blogDto.setId(1L);
		blogDto.setTitle("Machine Learning");
		blogDto.setContent("Machine Learning is in the trends now a days");
		blogDtos.add(blogDto);
		blogDto = new BlogDto();
		blogDto.setId(2L);
		blogDto.setTitle("Cloud");
		blogDto.setContent("Cloud is playing an important role for industries");
		blogDtos.add(blogDto);
		return blogDtos;
	}

	public static CommentDto getCommentDto() {
		CommentDto dto = new CommentDto();
		dto.setId(1L);
		dto.setBlogId(1L);
		dto.setComment("Yes, ML is widely used");
		return dto;
	}

	public static List<CommentDto> getCommentDtoList() {
		List<CommentDto> commentDtos = new ArrayList<>();
		CommentDto dto = new CommentDto();
		dto.setId(1L);
		dto.setBlogId(1L);
		dto.setComment("Yes, ML is widely used");
		commentDtos.add(dto);
		dto = new CommentDto();
		dto.setId(2L);
		dto.setBlogId(1L);
		dto.setComment("But ML is supported by some of the languages.");
		commentDtos.add(dto);
		return commentDtos;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
