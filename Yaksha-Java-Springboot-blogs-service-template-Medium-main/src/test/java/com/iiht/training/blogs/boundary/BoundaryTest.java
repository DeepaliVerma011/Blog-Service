package com.iiht.training.blogs.boundary;

import static com.iiht.training.blogs.testutils.TestUtils.boundaryTestFile;
import static com.iiht.training.blogs.testutils.TestUtils.currentTest;
import static com.iiht.training.blogs.testutils.TestUtils.testReport;
import static com.iiht.training.blogs.testutils.TestUtils.yakshaAssert;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.training.blogs.dto.BlogDto;
import com.iiht.training.blogs.dto.CommentDto;
import com.iiht.training.blogs.testutils.MasterData;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {
	private static Validator validator;

	// ----------------------------------------------------------------------------------------------
	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testBlogTitleNotNull() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		blogDto.setTitle(null);
		Set<ConstraintViolation<BlogDto>> violations = validator.validate(blogDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBlogTitleMinThree() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		blogDto.setTitle("Ab");
		Set<ConstraintViolation<BlogDto>> violations = validator.validate(blogDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBlogTitleMaxHundred() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		String title = "";
		for (int i = 0; i < 120; i++) {
			title.concat("A");
		}
		blogDto.setTitle(title);
		Set<ConstraintViolation<BlogDto>> violations = validator.validate(blogDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBlogContentNotNull() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		blogDto.setContent(null);
		Set<ConstraintViolation<BlogDto>> violations = validator.validate(blogDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBlogContentMinThree() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		blogDto.setContent("Ab");
		Set<ConstraintViolation<BlogDto>> violations = validator.validate(blogDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBlogContentMaxTwoHundred() throws Exception {
		BlogDto blogDto = MasterData.getBlogDto();
		String content = "";
		for (int i = 0; i < 210; i++) {
			content.concat("A");
		}
		blogDto.setContent(content);
		Set<ConstraintViolation<BlogDto>> violations = validator.validate(blogDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testContentBlogIdNotNull() throws Exception {
		CommentDto commentDto = MasterData.getCommentDto();
		commentDto.setBlogId(null);
		Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testCommentNotNull() throws Exception {
		CommentDto commentDto = MasterData.getCommentDto();
		commentDto.setComment(null);
		Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testCommentLengthMinThree() throws Exception {
		CommentDto commentDto = MasterData.getCommentDto();
		commentDto.setComment("Ab");
		Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testCommentLengthMaxTwoHundred() throws Exception {
		CommentDto commentDto = MasterData.getCommentDto();
		String comment = "";
		for (int i = 0; i < 210; i++) {
			comment.concat("A");
		}
		commentDto.setComment(comment);
		Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

}
