package co.id.middleware.router.repository;

import co.id.middleware.router.domain.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author errykistiyanto@gmail.com 2020-03-05
 */
@Service
public class HistoryImpl implements HistoryService {

    @Autowired(required = false)
    private HistoryRepo service;

    @Override
    public void save(History resp) {
        service.save(resp);
    }

}