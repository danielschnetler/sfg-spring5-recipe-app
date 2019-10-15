package guru.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SfgSpring5RecipeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfgSpring5RecipeAppApplication.class, args);
		log.debug("Project started");
	}

}
