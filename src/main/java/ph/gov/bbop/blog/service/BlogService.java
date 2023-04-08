package ph.gov.bbop.blog.service;

import org.springframework.stereotype.Service;
import ph.gov.bbop.blog.dto.BlogDto;
import ph.gov.bbop.blog.model.Blog;
import ph.gov.bbop.blog.repository.BlogRepository;
import ph.gov.bbop.blog.util.BlogMapper;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    public BlogService(BlogRepository blogRepository, BlogMapper blogMapper) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
    }

    public List<BlogDto> findAll() {
        return blogMapper.toDto(blogRepository.findAll());
    }

    public BlogDto findById(Long id) {
        return blogMapper.toDto(blogRepository.findById(id).orElse(null));
    }

    public BlogDto create(BlogDto blogDto) {
        if (blogDto.getId() != null) {
            throw new RuntimeException("Blog already exists.");
        }
        return save(blogDto);
    }

    public BlogDto update(Long id, BlogDto blogDto) {
        if (!blogRepository.existsById(id)) {
            throw new RuntimeException("Blog does not exist.");
        }
        blogDto.setId(id);
        return save(blogDto);
    }

    public BlogDto save(BlogDto blogDto) {
        return blogMapper.toDto(blogRepository.save(blogMapper.toEntity(blogDto)));
    }


    public BlogDto delete(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(RuntimeException::new);
        BlogDto blogDto = blogMapper.toDto(blog);
        blogRepository.deleteById(id);
        return blogDto;
    }
}
