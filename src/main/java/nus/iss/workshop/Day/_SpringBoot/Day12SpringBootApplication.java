package nus.iss.workshop.Day._SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class Day12SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day12SpringBootApplication.class, args);

		@Controller
		@RequestMapping(path = { "/"})
		public class numGenerator {
			@GetMapping(produces = {"text.html"})
		}
	}

}
