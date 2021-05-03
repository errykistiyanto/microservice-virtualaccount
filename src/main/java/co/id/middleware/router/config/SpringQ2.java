package co.id.middleware.router.config;

import org.jpos.q2.Q2;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author errykistiyanto@gmail.com 2020-07-12
 */
@Configuration
public class SpringQ2 extends Q2 {
    private SpringQ2 q2;

    @PostConstruct
    public void init() {
        q2 = new SpringQ2();
        q2.start();
    }

    @PreDestroy
    public void halt() {
        q2.shutdown();
    }
}