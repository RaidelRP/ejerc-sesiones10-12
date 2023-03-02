package com.example.ejercsesiones1012;

import com.example.ejercsesiones1012.entity.Laptop;
import com.example.ejercsesiones1012.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EjercSesiones1012Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EjercSesiones1012Application.class, args);
        LaptopRepository laptopRepo = context.getBean(LaptopRepository.class);
        Laptop laptop = new Laptop(null, "Lenovo", "IdeaPad", 15, 2.5);
		laptopRepo.save(laptop);
    }

}
