package ph.gov.bbop.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.gov.bbop.code.model.Code;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
}
