package ph.gov.bbop.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ph.gov.bbop.user.dto.UserDto;
import ph.gov.bbop.user.dto.UserSearchResultDto;
import ph.gov.bbop.user.model.Role;
import ph.gov.bbop.user.model.User;
import ph.gov.bbop.user.repository.UserRepository;
import ph.gov.bbop.user.util.UserMapper;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    public List<UserSearchResultDto> findAll(int size, int limit) {
        Page<User> pagedUsers = userRepository.findAll(PageRequest.of(size, limit));
        return userMapper.userSearchResultDto(pagedUsers.getContent());
    }

    public long getCount() {
        return userRepository.count();
    }
    public UserDto findById(String id) {
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }

    @Transactional
    public User create(UserDto userDto) {
        if (userRepository.existsById(userDto.getId())) {
            throw new RuntimeException("User already exists.");
        }
        return userRepository.save(userMapper.toEntity(userDto));
    }

    @Transactional
    public UserDto update(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto, user)));
    }

    public UserDto setAsAdmin(String id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setActive(true);
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto setAsUser(String id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setActive(true);
        user.setRole(Role.USER);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto activate(String id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setActive(true);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto deactivate(String id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setActive(false);
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}
