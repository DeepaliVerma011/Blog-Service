package com.iiht.training.blogs.exception;

import static com.iiht.training.blogs.testutils.TestUtils.currentTest;
import static com.iiht.training.blogs.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.blogs.testutils.TestUtils.testReport;
import static com.iiht.training.blogs.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.blogs.controller.BlogController;
import com.iiht.training.blogs.dto.BlogDto;
import com.iiht.training.blogs.dto.CommentDto;
import com.iiht.training.blogs.exceptions.BlogNotFoundException;
import com.iiht.training.blogs.exceptions.ExceptionResponse;
import com.iiht.training.blogs.service.BlogService;
import com.iiht.training.blogs.service.CommentService;
import com.iiht.training.blogs.testutils.MasterData;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc
public class BlogExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BlogService blogService;
	@MockBean
	private CommentService commentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateBlogInvalidDataException() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		BlogDto savedBlogDto = MasterData.getBlogDto();

		savedBlogDto.setId(1L);
		blogDto.setTitle("Ab");

		when(this.blogService.createBlog(blogDto)).thenReturn(savedBlogDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blogs")
				.content(MasterData.asJsonString(blogDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateBlogInvalidDataException() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		BlogDto savedBlogDto = MasterData.getBlogDto();

		savedBlogDto.setId(1L);
		blogDto.setTitle("Ab");

		when(this.blogService.updateBlog(1L, savedBlogDto)).thenReturn(savedBlogDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/blogs/1")
				.content(MasterData.asJsonString(blogDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testGetBlogByIdBlogNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Blog with Id - 1 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.blogService.getBlogById(1L)).thenThrow(new BlogNotFoundException("Blog with Id - 1 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blogs/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeleteBlogByIdBlogNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Blog with Id - 1 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.blogService.deleteBlog(1L)).thenThrow(new BlogNotFoundException("Blog with Id - 1 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/blogs/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testAddCommentInvalidDataException() throws Exception {
		CommentDto commentDto = MasterData.getCommentDto();
		CommentDto savedCommentDto = MasterData.getCommentDto();
		
		savedCommentDto.setId(1L);
		commentDto.setComment("Ab");

		when(this.commentService.postComment(commentDto)).thenReturn(savedCommentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blogs/comment")
				.content(MasterData.asJsonString(commentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}
	
}
