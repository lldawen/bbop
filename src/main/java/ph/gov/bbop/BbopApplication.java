package ph.gov.bbop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class BbopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbopApplication.class, args);
	}

}
