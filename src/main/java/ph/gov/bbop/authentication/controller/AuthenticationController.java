package ph.gov.bbop.authentication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.gov.bbop.authentication.component.AuthenticationService;
import ph.gov.bbop.authentication.dto.AuthenticationRequest;
import ph.gov.bbop.authentication.dto.AuthenticationResponse;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.Message;
import ph.gov.bbop.user.dto.UserDto;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController extends CommonRestController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Message> register(@RequestBody UserDto userDto) {
        return message(authenticationService.register(userDto));
    }

    @PostMapping({ "/authenticate", "/admin/authenticate" })
    public ResponseEntity<Message> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authResponse = authenticationService.authenticate(authenticationRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + authResponse.getToken());
        return messageWithHeaders(authResponse, httpHeaders);
    }

}
