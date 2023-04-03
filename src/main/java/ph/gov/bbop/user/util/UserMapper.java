package ph.gov.bbop.user.util;

import org.springframework.stereotype.Component;
import ph.gov.bbop.user.dto.UserDetailDto;
import ph.gov.bbop.user.dto.UserDto;
import ph.gov.bbop.user.model.User;
import ph.gov.bbop.user.model.UserDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public List<UserDto> toDto(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        if (user == null) {
            return userDto;
        }
        userDto.setId(user.getId());
        userDto.setAdmin(user.isAdmin());
        userDto.setActive(user.isActive());
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setUserId(user.getId());
        userDetailDto.setFirstName(user.getUserDetail().getFirstName());
        userDetailDto.setLastName(user.getUserDetail().getLastName());
        userDetailDto.setMiddleName(user.getUserDetail().getMiddleName());
        userDto.setUserDetail(userDetailDto);
        return userDto;
    }

    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            throw new RuntimeException("UserDto must not be null.");
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setActive(true);
        user.setAdmin(true);
        user.setCreatedBy("tester");
        user.setCreatedDate(LocalDateTime.now());

        UserDetailDto userDetailDto = userDto.getUserDetail();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(userDto.getId());
        userDetail.setUser(user);
        userDetail.setFirstName(userDetailDto.getFirstName());
        userDetail.setLastName(userDetailDto.getLastName());
        userDetail.setMiddleName(userDetailDto.getMiddleName());
        userDetail.setGender(userDetailDto.getGender());
        userDetail.setBirthDate(LocalDate.parse(userDetailDto.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        userDetail.setAge(userDetailDto.getAge());
        userDetail.setContactNo1(userDetailDto.getContactNo1());
        userDetail.setContactNo2(userDetailDto.getContactNo2());
        userDetail.setEmail(userDetailDto.getEmail());
        userDetail.setHouseBlkNo(userDetailDto.getHouseBlkNo());
        userDetail.setDistrict(userDetailDto.getDistrict());
        userDetail.setStreet(userDetailDto.getStreet());
        userDetail.setCivilStatus(userDetailDto.getCivilStatus());
        userDetail.setActive(true);
        userDetail.setSignature(userDetailDto.getSignature());
        userDetail.setCreatedBy("tester");
        userDetail.setCreatedDate(LocalDateTime.now());

        user.setUserDetail(userDetail);

        return user;
    }
}
