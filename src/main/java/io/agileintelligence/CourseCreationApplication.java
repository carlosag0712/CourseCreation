package io.agileintelligence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseCreationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseCreationApplication.class, args);
	}
}

// course and a lesson

// a course can have many lesson

// a lesson maps to 1 course