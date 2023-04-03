package ph.gov.bbop.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.gov.bbop.blog.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
