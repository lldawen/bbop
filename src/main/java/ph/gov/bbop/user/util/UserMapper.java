package ph.gov.bbop.user.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ph.gov.bbop.common.util.AuditableFieldsMapper;
import ph.gov.bbop.common.util.DateTimeUtil;
import ph.gov.bbop.user.dto.UserDetailDto;
import ph.gov.bbop.user.dto.UserDto;
import ph.gov.bbop.user.model.Role;
import ph.gov.bbop.user.model.User;
import ph.gov.bbop.user.model.UserDetail;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.debug("user.getUserDetail(): {}", user.getUserDetail());
        if (user.getUserDetail() != null) {
            userDetailDto.setFirstName(user.getUserDetail().getFirstName());
            userDetailDto.setLastName(user.getUserDetail().getLastName());
            userDetailDto.setMiddleName(user.getUserDetail().getMiddleName());
            userDetailDto.setGender(user.getUserDetail().getGender());
            userDetailDto.setBirthDate(DateTimeUtil.format(user.getUserDetail().getBirthDate()));
            userDetailDto.setCivilStatus(user.getUserDetail().getCivilStatus());
            userDetailDto.setContactNo1(user.getUserDetail().getContactNo1());
            userDetailDto.setContactNo2(user.getUserDetail().getContactNo2());
            userDetailDto.setEmail(user.getUserDetail().getEmail());
            userDetailDto.setHouseBlkNo(user.getUserDetail().getHouseBlkNo());
            userDetailDto.setDistrict(user.getUserDetail().getDistrict());
            userDetailDto.setStreet(user.getUserDetail().getStreet());
            userDetailDto.setSignature(user.getUserDetail().getSignature());
        }
        auditableFieldsMapper.toDto(userDto, user);

        userDto.setUserDetail(userDetailDto);
        return userDto;
    }

    public User toEntity(UserDto userDto) {
        return toEntity(userDto, null);
    }

    public User toEntity(UserDto userDto, User user) {
        UserDetail userDetail = null;
        if (user == null) {
            user = new User();
            user.setId(userDto.getId());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setRole(Role.of(userDto.getRole()));
            user.setActive(true);
            userDetail = new UserDetail();
        } else {
            userDetail = user.getUserDetail();
        }
        if (userDto.getUserDetail() != null) {
            UserDetailDto userDetailDto = userDto.getUserDetail();
            userDetail.setUserId(userDto.getId());
            userDetail.setUser(user);
            userDetail.setFirstName(userDetailDto.getFirstName());
            userDetail.setLastName(userDetailDto.getLastName());
            userDetail.setMiddleName(userDetailDto.getMiddleName());
            userDetail.setGender(userDetailDto.getGender());
            userDetail.setBirthDate(DateTimeUtil.parse(userDetailDto.getBirthDate()));
            userDetail.setAge(calculateAge(userDetail.getBirthDate()));
            userDetail.setContactNo1(userDetailDto.getContactNo1());
            userDetail.setContactNo2(userDetailDto.getContactNo2());
            userDetail.setEmail(userDetailDto.getEmail());
            userDetail.setHouseBlkNo(userDetailDto.getHouseBlkNo());
            userDetail.setDistrict(userDetailDto.getDistrict());
            userDetail.setStreet(userDetailDto.getStreet());
            userDetail.setCivilStatus(userDetailDto.getCivilStatus());
            user.setUserDetail(userDetail);
        }
        return user;
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
