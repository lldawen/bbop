package ph.gov.bbop.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.bbop.blog.dto.BlogDto;
import ph.gov.bbop.blog.service.BlogService;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.Message;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController extends CommonRestController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll() {
        return message(blogService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return message(blogService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestBody BlogDto blogDto) {
        return message(blogService.create(blogDto));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Message> create(@PathVariable Long id, @RequestBody BlogDto blogDto) {
        return message(blogService.update(id, blogDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        return message(blogService.delete(id));
    }
}
