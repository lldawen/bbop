package ph.gov.bbop.authentication.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ph.gov.bbop.authentication.dto.AuthenticationRequest;
import ph.gov.bbop.authentication.dto.AuthenticationResponse;
import ph.gov.bbop.user.dto.UserDto;
import ph.gov.bbop.user.model.User;
import ph.gov.bbop.user.repository.UserRepository;
import ph.gov.bbop.user.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtAuthenticationService jwtAuthenticationService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationResponse register(UserDto userDto) {
        User user = userService.create(userDto);
        String token = jwtAuthenticationService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        log.info("AuthenticationService | authenticate | authenticationRequest = " + authenticationRequest);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        User user = userRepository.findById(authenticationRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        String token = jwtAuthenticationService.generateToken(user);
        log.info("AuthenticationService | authenticate | token = " + token);

        return new AuthenticationResponse(token);
    }
}
