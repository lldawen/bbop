package ph.gov.bbop.blog.util;

import org.springframework.stereotype.Component;
import ph.gov.bbop.blog.dto.BlogDto;
import ph.gov.bbop.blog.model.Blog;
import ph.gov.bbop.common.util.ClobConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogMapper {

    public List<BlogDto> toDto(List<Blog> blogs) {
        System.out.println(blogs);
        return blogs.stream().map(this::toDto).collect(Collectors.toList());
    }

    public BlogDto toDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        if (blog == null) {
            return blogDto;
        }
        blogDto.setId(blog.getId());
        blogDto.setAuthor(blog.getAuthor());
        blogDto.setTitle(blog.getTitle());
        blogDto.setBody(ClobConverter.toString(blog.getBody()));
        return blogDto;
    }

    public Blog toEntity(BlogDto blogDto) {
        if (blogDto == null) {
            throw new IllegalArgumentException("BlogDto must not be null.");
        }
        Blog blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setAuthor(blogDto.getAuthor());
        blog.setTitle(blogDto.getTitle());
        blog.setBody(ClobConverter.fromString(blogDto.getBody()));
        return blog;
    }
}
