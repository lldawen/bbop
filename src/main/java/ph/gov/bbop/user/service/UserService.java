package ph.gov.bbop.user.service;

import org.springframework.stereotype.Service;
import ph.gov.bbop.user.dto.UserDto;
import ph.gov.bbop.user.repository.UserRepository;
import ph.gov.bbop.user.util.UserDtoMapping;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapping userDtoMapping;

    public UserService(UserRepository userRepository, UserDtoMapping userDtoMapping) {
        this.userRepository = userRepository;
        this.userDtoMapping = userDtoMapping;
    }

    public List<UserDto> findAll() {
        return userDtoMapping.fromEntity(userRepository.findAll());
    }

    public UserDto findById(String id) {
        return userDtoMapping.fromEntity(userRepository.findById(id).orElse(null));
    }

    public UserDto save(UserDto userDto) {
        userRepository.save(userDtoMapping.toEntity(userDto));
        return userDto;
    }

    public UserDto delete(String id) {
        UserDto userDto = userDtoMapping.fromEntity(userRepository.findById(id).orElse(null));
        if (userDto != null) {
            userRepository.deleteById(id);
        }
        return userDto;
    }
}
