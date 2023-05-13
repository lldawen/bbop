package ph.gov.bbop.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.gov.bbop.application.model.Application;
import ph.gov.bbop.user.model.User;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Page<Application> findByStatusNotOrderByCreatedDateDesc(String status, PageRequest pageRequest);

    long countByStatusNot(String status);

    Page<Application> findByApplicantAndStatusNotOrderByCreatedDateDesc(User applicant, String status, PageRequest pageRequest);

    long countByApplicant(User applicant);
}
