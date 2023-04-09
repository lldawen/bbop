package ph.gov.bbop.user.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ph.gov.bbop.common.model.BaseModel;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "BBOP_USER")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel implements UserDetails {

    @Id
    @Column(length = 75, unique = true)
    private String id;

    @Column(length = 255, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 5, nullable = false)
    private Role role = Role.USER;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;

    @OneToOne(mappedBy = "user", optional = true,fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "USER_DETAIL")
    private UserDetail userDetail;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
