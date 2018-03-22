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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("com.vallve.cristianback.repository")
@EntityScan(basePackageClasses= Nota.class)
@Controller
@EnableWebMvc
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
	int save(@RequestBody String nota) {
		System.out.println("salvar");
		System.out.println(nota);

		String decode = "";
		String clean = "";
		try {
			decode = URLDecoder.decode(nota, "UTF-8");
			clean = decode.replace("nota=","");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println(clean);

		return notaRepository.save(new Nota(clean)).getId();
	}

	@RequestMapping("/update/{id}")
	@ResponseBody
	int update(@RequestBody String nota, @PathVariable int id) {
		System.out.println(nota);

		String decode = "";
		String clean = "";
		try {
			decode = URLDecoder.decode(nota, "UTF-8");
			clean = decode.replace("nota=","");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Nota notaOb = notaRepository.findOne(id);
		notaOb.setNota(nota);

		return notaRepository.save(notaOb).getId();
	}

	@RequestMapping("/recover")
	@ResponseBody
	List<String> recover() {
		System.out.println("recuperar");

		List<String> response = new ArrayList<>();

		List<Nota> result = notaRepository.findAll();

		result.forEach(n -> response.add(n.getNota() + n.getId()));

		return response;
	}

	@RequestMapping("/remove/{id}")
	@ResponseBody
	boolean remove(@PathVariable int id) {
		System.out.println("remove");

		notaRepository.delete(id);

		return true;
	}

	public static void main(String[] args) {
		SpringApplication.run(CristianbackApplication.class, args);
	}
}
