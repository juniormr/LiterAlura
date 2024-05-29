package com.junior.literalura;

import com.junior.literalura.main.Principal;
import com.junior.literalura.repository.AuthorRepository;
import com.junior.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private AuthorRepository repositoryA;
	@Autowired
	private BookRepository repositoryB;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositoryA, repositoryB);
		principal.start();

	}

}
