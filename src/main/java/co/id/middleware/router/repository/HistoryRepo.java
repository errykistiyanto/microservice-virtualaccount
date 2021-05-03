package co.id.middleware.router.repository;

import co.id.middleware.router.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author errykistiyanto@gmail.com 2020-03-05
 */
@Repository
public interface HistoryRepo extends JpaRepository<History, Long> {

}
