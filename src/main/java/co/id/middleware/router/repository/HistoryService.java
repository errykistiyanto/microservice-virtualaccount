package co.id.middleware.router.repository;

import co.id.middleware.router.domain.History;
import org.springframework.stereotype.Component;

/**
 * @author errykistiyanto@gmail.com 2020-03-05
 */
@Component
public interface HistoryService {
    void save(History resp);
}


