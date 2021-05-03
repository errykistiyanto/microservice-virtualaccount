package co.id.middleware.router;
import co.id.middleware.router.repository.HistoryService;
import org.jpos.util.NameRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="file:./config/application.properties")
public class MicroserviceVirtualAccountApplication {

	public static ApplicationContext ctx;
	public static void main(String[] args) {
		ctx = SpringApplication.run(MicroserviceVirtualAccountApplication.class, args);
		System.out.println("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();

		HistoryService historyService = (HistoryService) ctx.getBean("historyImpl");
		NameRegistrar.register("historyImpl", historyService);
	}
}