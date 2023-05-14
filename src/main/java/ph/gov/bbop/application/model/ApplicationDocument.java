package ph.gov.bbop.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.model.BaseModel;

@Entity
@Table(name = "BBOP_APPL_REQUIRED_DOC")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplicationDocument extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BBOP_APPL_REQUIRED_DOC_SEQ")
    @SequenceGenerator(name = "BBOP_APPL_REQUIRED_DOC_SEQ", sequenceName = "BBOP_APPL_REQUIRED_DOC_SEQ", allocationSize = 1, initialValue = 1000)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "APPLICATION_ID")
    @JsonIgnore
    private Application application;

    @Column(name = "DOCUMENT_TYPE", length = 10, nullable = false)
    private String documentType;

    @Column(name = "DOCUMENT_FILENAME", nullable = false)
    private String documentName;

    @Column(name = "DOCUMENT_PATH", nullable = false)
    private String documentPath;

}
