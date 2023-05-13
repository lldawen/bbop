package ph.gov.bbop.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.model.BaseModel;

@Entity
@Table(name = "BBOP_CERTIFICATE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Certificate extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BBOP_CERTIFICATE_SEQ")
    @SequenceGenerator(name = "BBOP_CERTIFICATE_SEQ", sequenceName = "BBOP_CERTIFICATE_SEQ", allocationSize = 1, initialValue = 1000)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "APPLICATION_ID")
    private Application application;

    @Column(name = "GENERATED_FILENAME")
    private String generatedFilename;

    @Column(name = "GENERATED_FILEPATH", nullable = false)
    private String generatedFilePath;
}
