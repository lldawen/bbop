package ph.gov.bbop.authentication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping({ "/checkProfile", "/admin/checkProfile" })
    public UserDetails checkProfile() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

}
