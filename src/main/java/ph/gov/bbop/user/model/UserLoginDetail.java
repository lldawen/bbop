package ph.gov.bbop.user.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.model.BaseModel;

import java.time.LocalDateTime;

@Entity
@Table(name = "BBOP_USER_LOGIN_DETAIL")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserLoginDetail extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BBOP_USER_LOGIN_DETAIL_SEQ")
    @SequenceGenerator(name = "BBOP_USER_LOGIN_DETAIL_SEQ", sequenceName = "BBOP_USER_LOGIN_DETAIL_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(nullable = false)
    private LocalDateTime loginDateTime;

    @Column(name = "IS_LOGGED_IN", nullable = false)
    private boolean isLoggedIn;

}
