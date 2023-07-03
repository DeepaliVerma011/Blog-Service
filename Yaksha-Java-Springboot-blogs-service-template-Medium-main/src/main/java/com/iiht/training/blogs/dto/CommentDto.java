//package com.iiht.training.blogs.dto;
//
//import java.util.Objects;
//
//public class CommentDto {
//
//	private Long id;
//	private Long blogId;
//	private String comment;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Long getBlogId() {
//		return blogId;
//	}
//
//	public void setBlogId(Long blogId) {
//		this.blogId = blogId;
//	}
//
//	public String getComment() {
//		return comment;
//	}
//
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(blogId, comment, id);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		CommentDto other = (CommentDto) obj;
//		return Objects.equals(blogId, other.blogId) && Objects.equals(comment, other.comment)
//				&& Objects.equals(id, other.id);
//	}
//
//}

package com.iiht.training.blogs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CommentDto {
    private Long id;

    @NotNull(message = "Blog ID is required")
    private Long blogId;

    @NotBlank(message = "Comment is required")
    @Size(min = 3, max = 200, message = "Comment must have 3 to 200 characters")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogId, comment, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CommentDto other = (CommentDto) obj;
        return Objects.equals(blogId, other.blogId) && Objects.equals(comment, other.comment)
                && Objects.equals(id, other.id);
    }
}