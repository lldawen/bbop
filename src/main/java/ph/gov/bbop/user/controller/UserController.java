package ph.gov.bbop.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.MessageDetail;
import ph.gov.bbop.user.dto.UserDto;
import ph.gov.bbop.user.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController extends CommonRestController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<MessageDetail> findAll() {
        return responseEntityWithDetails(userService.findAll());
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<MessageDetail> findAll(@PathVariable String id) {
        return responseEntityWithDetails(userService.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageDetail> save(@RequestBody UserDto userDto) {
        return responseEntityWithDetails(userService.create(userDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MessageDetail> update(@PathVariable String id, @RequestBody UserDto userDto) {
        return responseEntityWithDetails(userService.update(id, userDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDetail> delete(@PathVariable String id) {
        return responseEntityWithDetails(userService.delete(id));
    }
}
