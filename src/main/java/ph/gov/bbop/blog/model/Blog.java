package ph.gov.bbop.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.model.BaseModel;

import java.sql.Clob;

@Entity
@Table(name = "BBOP_BLOG")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Blog extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BBOP_BLOG_SEQ")
    @SequenceGenerator(name = "BBOP_BLOG_SEQ", sequenceName = "BBOP_BLOG_SEQ", allocationSize = 1, initialValue = 1000)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Clob body;

}
