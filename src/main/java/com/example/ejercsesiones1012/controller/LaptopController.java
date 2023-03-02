package com.example.ejercsesiones1012.controller;

import com.example.ejercsesiones1012.entity.Laptop;
import com.example.ejercsesiones1012.repository.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private LaptopRepository repository;

    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
    }

    public LaptopRepository getRepository() {
        return repository;
    }

    public void setRepository(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/laptops")
    public List<Laptop> findAll() {
        return repository.findAll();
    }


    @GetMapping("/laptops/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {
        Optional<Laptop> resultado = repository.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get());
    }

    @PostMapping("/laptops/new")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        if (laptop.getId() != null) {//Si tiene id. Cuando se crea no deb tener id
            return ResponseEntity.badRequest().build();
        }
        Laptop nueva = repository.save(laptop);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/laptops/edit")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        if (laptop.getId() == null) {// Si no tiene id. Cuando se edita debe tener id
            return ResponseEntity.badRequest().build();
        }

        Long id = laptop.getId();
        Optional<Laptop> resultado = repository.findById(id);//Buscar el id de la laptop

        if (resultado.isEmpty()) {//Si no encuentra
            return ResponseEntity.notFound().build();
        }
        Laptop editar = repository.save(laptop);
        return ResponseEntity.ok(editar);
    }

    @DeleteMapping("/laptops/delete/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Laptop> resultado = repository.findById(id);//Buscar el id de la laptop
        if (resultado.isEmpty()) {//Si no encuentra
            return ResponseEntity.notFound().build();
        }
        Laptop eliminar = resultado.get();
        repository.deleteById(id);
        return ResponseEntity.ok(eliminar);
    }

    @DeleteMapping("/laptops/delete/all")
    public ResponseEntity<Laptop> deleteAll() {
        if (findAll().size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
