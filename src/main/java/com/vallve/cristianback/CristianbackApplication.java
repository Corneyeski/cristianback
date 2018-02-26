package com.vallve.cristianback;

import com.vallve.cristianback.repository.NotaRepository;
import entities.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
		System.out.println("entra1");
		notaRepository.findAll();
		return "Hello World!";
	}

	@RequestMapping("/save")
	@ResponseBody
	String save(@RequestBody String nota) {
		System.out.println("entro");
		notaRepository.save(new Nota(nota));
		return "Saved!";
	}

    @RequestMapping("/save/{nota}")
    @ResponseBody
    String saveByUrl(@PathVariable String nota) {
        System.out.println("entro");
        System.out.println(nota);
        notaRepository.save(new Nota(nota));
        return "Saved!";
    }

	public static void main(String[] args) {
		SpringApplication.run(CristianbackApplication.class, args);
	}
}
