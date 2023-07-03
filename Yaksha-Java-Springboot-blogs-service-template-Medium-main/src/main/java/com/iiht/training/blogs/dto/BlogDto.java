//package com.iiht.training.blogs.dto;
//
//import java.util.Objects;
//
//public class BlogDto {
//	private Long id;
//	private String title;
//	private String content;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(content, id, title);
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
//		BlogDto other = (BlogDto) obj;
//		return Objects.equals(content, other.content) && Objects.equals(id, other.id)
//				&& Objects.equals(title, other.title);
//	}
//
//}


package com.iiht.training.blogs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class BlogDto {
    private Long id;

    @NotBlank(message = "Blog title is required")
    @Size(min = 3, max = 100, message = "Blog title must have 3 to 100 characters")
    private String title;

    @NotBlank(message = "Blog content is required")
    @Size(min = 3, max = 200, message = "Blog content must have 3 to 200 characters")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, id, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BlogDto other = (BlogDto) obj;
        return Objects.equals(content, other.content) && Objects.equals(id, other.id)
                && Objects.equals(title, other.title);
    }
}