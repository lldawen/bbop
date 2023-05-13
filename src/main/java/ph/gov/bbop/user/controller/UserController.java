package ph.gov.bbop.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.Message;
import ph.gov.bbop.user.dto.UserDto;
import ph.gov.bbop.user.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends CommonRestController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll(Integer size, Integer limit) {
        if (size == null) {
            size = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        return message(userService.findAll(size, limit), Map.of("total", userService.getCount()));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Message> findById(@PathVariable String id) {
        return message(userService.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<Message> save(@RequestBody UserDto userDto) {
        return message(userService.create(userDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable String id, @RequestBody UserDto userDto) {
        return message(userService.update(id, userDto));
    }

    @PutMapping ("/setAsAdmin/{id}")
    public ResponseEntity<Message> setAsAdmin(@PathVariable String id) {
        return message(userService.setAsAdmin(id));
    }

    @PutMapping ("/setAsUser/{id}")
    public ResponseEntity<Message> setAsUser(@PathVariable String id) {
        return message(userService.setAsUser(id));
    }

    @PutMapping ("/activate/{id}")
    public ResponseEntity<Message> activate(@PathVariable String id) {
        return message(userService.activate(id));
    }

    @PutMapping ("/deactivate/{id}")
    public ResponseEntity<Message> deactivate(@PathVariable String id) {
        return message(userService.deactivate(id));
    }
}
