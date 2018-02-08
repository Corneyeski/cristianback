package com.vallve.cristianback;

import com.vallve.cristianback.repository.NotaRepository;
import entities.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableJpaRepositories("com.vallve.cristianback.repository")
@EntityScan(basePackageClasses= Nota.class)
@Controller
public class CristianbackApplication {

	@Autowired
	NotaRepository notaRepository;

	@RequestMapping("/")
	@ResponseBody
	String home() {
		notaRepository.findAll();
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(CristianbackApplication.class, args);
	}
}
