package lk.weerathunga.denimcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class DenimcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DenimcenterApplication.class, args);
	}

}
