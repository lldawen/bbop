package ph.gov.bbop.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ph.gov.bbop.user.dto.UserDto;
import ph.gov.bbop.user.model.User;
import ph.gov.bbop.user.repository.UserRepository;
import ph.gov.bbop.user.util.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    public UserDto findById(String id) {
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }

    public User create(UserDto userDto) {
        if (userRepository.existsById(userDto.getId())) {
            throw new RuntimeException("User already exists.");
        }
        return save(userDto);
    }

    public UserDto update(String id, UserDto userDto) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User does not exist.");
        }
        userDto.setId(id);
        return userMapper.toDto(save(userDto));
    }

    public User save(UserDto userDto) {
        return userRepository.save(userMapper.toEntity(userDto));
    }

    public UserDto delete(String id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        UserDto userDto = userMapper.toDto(user);
        userRepository.deleteById(id);
        return userDto;
    }
}
