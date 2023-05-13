package ph.gov.bbop.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.gov.bbop.application.model.Application;
import ph.gov.bbop.application.model.ApplicationDocument;

import java.awt.print.Pageable;

@Repository
public interface ApplicationDocumentRepository extends JpaRepository<ApplicationDocument, Long> {

    public Page<ApplicationDocument> findByApplication(Application application, PageRequest pageRequest);

    long countByApplication(Application application);
}
