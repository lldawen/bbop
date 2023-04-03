package ph.gov.bbop.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.bbop.blog.dto.BlogDto;
import ph.gov.bbop.blog.service.BlogService;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.MessageDetail;

@RestController
@RequestMapping("/v1/blog")
public class BlogController extends CommonRestController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/all")
    public ResponseEntity<MessageDetail> findAll() {
        return responseEntityWithDetails(blogService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MessageDetail> findById(@PathVariable Long id) {
        return responseEntityWithDetails(blogService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDetail> create(@RequestBody BlogDto blogDto) {
        return responseEntityWithDetails(blogService.create(blogDto));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<MessageDetail> create(@PathVariable Long id, @RequestBody BlogDto blogDto) {
        return responseEntityWithDetails(blogService.update(id, blogDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDetail> delete(@PathVariable Long id) {
        return responseEntityWithDetails(blogService.delete(id));
    }
}
