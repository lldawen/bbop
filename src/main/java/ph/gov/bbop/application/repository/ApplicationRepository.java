package ph.gov.bbop.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.gov.bbop.application.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
