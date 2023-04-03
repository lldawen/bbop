package ph.gov.bbop.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.model.BaseModel;

@Entity
@Table(name = "BBOP_USER")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

    @Id
    @Column(length = 75)
    private String id;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;

    @Column(name = "IS_ADMIN", nullable = false)
    private boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", optional = false)
    @PrimaryKeyJoinColumn(name = "USER_DETAIL")
    private UserDetail userDetail;

}
