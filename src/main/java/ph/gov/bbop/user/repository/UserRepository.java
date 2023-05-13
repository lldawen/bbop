package ph.gov.bbop.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.gov.bbop.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Page<User> findAllByIsActiveTrue(PageRequest pageRequest);

}
