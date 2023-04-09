package ph.gov.bbop.user.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ph.gov.bbop.common.util.AuditableFieldsMapper;
import ph.gov.bbop.common.util.DateTimeUtil;
import ph.gov.bbop.user.dto.UserDetailDto;
import ph.gov.bbop.user.dto.UserDto;
import ph.gov.bbop.user.model.Role;
import ph.gov.bbop.user.model.User;
import ph.gov.bbop.user.model.UserDetail;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuditableFieldsMapper auditableFieldsMapper;

    public UserMapper(BCryptPasswordEncoder bCryptPasswordEncoder, AuditableFieldsMapper auditableFieldsMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.auditableFieldsMapper = auditableFieldsMapper;
    }
    public List<UserDto> toDto(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        if (user == null) {
            return userDto;
        }
        userDto.setId(user.getId());
        userDto.setRole(user.getRole().name());
        userDto.setActive(user.isActive());
        auditableFieldsMapper.toDto(userDto, user);

        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setUserId(user.getId());
        userDetailDto.setFirstName(user.getUserDetail().getFirstName());
        userDetailDto.setLastName(user.getUserDetail().getLastName());
        userDetailDto.setMiddleName(user.getUserDetail().getMiddleName());
        auditableFieldsMapper.toDto(userDto, user);

        userDto.setUserDetail(userDetailDto);
        return userDto;
    }

    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            throw new RuntimeException("UserDto must not be null.");
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.of(userDto.getRole()));
        user.setActive(true);

        if (userDetailsExist(userDto)) {
            UserDetailDto userDetailDto = userDto.getUserDetail();
            UserDetail userDetail = new UserDetail();
            userDetail.setUserId(userDto.getId());
            userDetail.setUser(user);
            userDetail.setFirstName(userDetailDto.getFirstName());
            userDetail.setLastName(userDetailDto.getLastName());
            userDetail.setMiddleName(userDetailDto.getMiddleName());
            userDetail.setGender(userDetailDto.getGender());
            userDetail.setBirthDate(DateTimeUtil.parse(userDetailDto.getBirthDate()));
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

            user.setUserDetail(userDetail);
        }
        return user;
    }

    private boolean userDetailsExist(UserDto userDto) {
        return userDto.getUserDetail() != null && StringUtils.isNotEmpty(userDto.getUserDetail().getUserId());
    }
}
