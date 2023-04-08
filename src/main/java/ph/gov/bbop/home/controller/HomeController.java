package ph.gov.bbop.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.Message;

@RestController
@RequiredArgsConstructor
public class HomeController extends CommonRestController {

    @GetMapping({ "/", "/home" })
    public ResponseEntity<Message> home() {
        return message(null);
    }
}
