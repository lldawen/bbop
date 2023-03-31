package ph.gov.bbop.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.model.BaseModel;
import java.sql.Blob;

import java.time.LocalDate;

@Entity
@Table(name = "BBOP_USER_DETAIL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDetail extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private String userId;

    @MapsId
    @JoinColumn(name = "userId")
    @OneToOne(optional = false)
    private User user;

    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;

    @Column(name = "MIDDLE_NAME", length = 50, nullable = false)
    private String middleName;

    @Column(length = 3, nullable = false)
    private String gender;

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Column(length = 3, nullable = false)
    private Integer age;

    @Column(name = "CIVIL_STATUS", length = 3, nullable = false)
    private String civilStatus;

    @Column(name = "CONTACT_NO_1", length = 20, nullable = false)
    private String contactNo1;

    @Column(name = "CONTACT_NO_2", length = 20, nullable = false)
    private String contactNo2;

    @Column(length = 75, nullable = false)
    private String email;

    @Column(name = "HOUSE_BLK_NO", length = 10, nullable = false)
    private String houseBlkNo;

    @Column(length = 10, nullable = false)
    private String district;

    @Column(length = 10, nullable = false)
    private String street;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;

    private Blob signature;
}
