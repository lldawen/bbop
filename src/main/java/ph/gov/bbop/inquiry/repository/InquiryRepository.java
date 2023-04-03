package ph.gov.bbop.inquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.gov.bbop.inquiry.model.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
