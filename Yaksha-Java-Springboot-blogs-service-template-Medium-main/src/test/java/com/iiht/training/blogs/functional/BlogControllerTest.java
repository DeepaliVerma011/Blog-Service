package com.iiht.training.blogs.functional;

import static com.iiht.training.blogs.testutils.TestUtils.businessTestFile;
import static com.iiht.training.blogs.testutils.TestUtils.currentTest;
import static com.iiht.training.blogs.testutils.TestUtils.testReport;
import static com.iiht.training.blogs.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.blogs.controller.BlogController;
import com.iiht.training.blogs.dto.BlogDto;
import com.iiht.training.blogs.dto.CommentDto;
import com.iiht.training.blogs.service.BlogService;
import com.iiht.training.blogs.service.CommentService;
import com.iiht.training.blogs.testutils.MasterData;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc
public class BlogControllerTest {
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
	public void testCreateBlog() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		BlogDto savedBlogDto = MasterData.getBlogDto();

		savedBlogDto.setId(1L);

		when(this.blogService.createBlog(blogDto)).thenReturn(savedBlogDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blogs")
				.content(MasterData.asJsonString(blogDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedBlogDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testCreateBlogIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		BlogDto blogDto = MasterData.getBlogDto();
		BlogDto savedBlogDto = MasterData.getBlogDto();

		savedBlogDto.setId(1L);
		when(this.blogService.createBlog(blogDto)).then(new Answer<BlogDto>() {

			@Override
			public BlogDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedBlogDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blogs")
				.content(MasterData.asJsonString(blogDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetBlogById() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		blogDto.setId(1L);
		when(this.blogService.getBlogById(1L)).thenReturn(blogDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blogs/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(blogDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetBlogByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		BlogDto blogDto = MasterData.getBlogDto();
		when(this.blogService.getBlogById(1L)).then(new Answer<BlogDto>() {

			@Override
			public BlogDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return blogDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blogs/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateBlog() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		BlogDto savedBlogDto = MasterData.getBlogDto();

		savedBlogDto.setId(1L);

		when(this.blogService.updateBlog(1L, blogDto)).thenReturn(savedBlogDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/blogs/1")
				.content(MasterData.asJsonString(blogDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedBlogDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateBlogIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		BlogDto blogDto = MasterData.getBlogDto();
		BlogDto savedBlogDto = MasterData.getBlogDto();

		savedBlogDto.setId(1L);
		when(this.blogService.updateBlog(1L, blogDto)).then(new Answer<BlogDto>() {

			@Override
			public BlogDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedBlogDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/blogs/1")
				.content(MasterData.asJsonString(blogDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteBlog() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		blogDto.setId(1L);
		when(this.blogService.deleteBlog(1L)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/blogs/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteBlogIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		BlogDto blogDto = MasterData.getBlogDto();
		blogDto.setId(1L);
		when(this.blogService.deleteBlog(1L)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/blogs/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testPostComment() throws Exception {
		CommentDto commentDto = MasterData.getCommentDto();
		CommentDto savedCommentDto = MasterData.getCommentDto();

		savedCommentDto.setId(1L);

		when(this.commentService.postComment(commentDto)).thenReturn(savedCommentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blogs/comment")
				.content(MasterData.asJsonString(commentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedCommentDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testPostCommentIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		CommentDto commentDto = MasterData.getCommentDto();
		CommentDto savedCommentDto = MasterData.getCommentDto();

		savedCommentDto.setId(1L);
		when(this.commentService.postComment(commentDto)).then(new Answer<CommentDto>() {

			@Override
			public CommentDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedCommentDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blogs/comment")
				.content(MasterData.asJsonString(commentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
