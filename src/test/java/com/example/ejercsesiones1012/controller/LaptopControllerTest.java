package com.example.ejercsesiones1012.controller;

import com.example.ejercsesiones1012.entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//Correr con coverage
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void hola() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hola", String.class);

        //Pruebas
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hola, mi gente!", response.getBody());
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/laptops", Laptop[].class);

        //Pruebas
        assertEquals(HttpStatus.OK, response.getStatusCode());//siempre debe funcionar qunque no haya nada
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/laptops/2", Laptop.class);

        //Pruebas
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());//Si no hay nada, funciona al no encontrar
    }

    @Test
    void create() {
        //Preparar contenido de peticion HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "marca": "ChromebookTest",
                    "modelo": "Chromebook 6",
                    "pulgadas": 15.0,
                    "duracion": 4
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops/new", HttpMethod.POST, request, Laptop.class);

        Laptop resultado = response.getBody();

        //Pruebas
        assertEquals(1, resultado.getId());
        assertEquals("ChromebookTest", resultado.getMarca());

    }

    @Test
    void update() {
        //Preparar contenido de peticion HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id": 2,
                    "marca": "ChromeBook",
                    "modelo": "Chromebook 6",
                    "pulgadas": 15.0,
                    "duracion": 4.5
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops/edit", HttpMethod.PUT, request, Laptop.class);

        Laptop resultado = response.getBody();

        //Pruebas
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());//si no encuentra nada, funciona el test

    }

    @Test
    void delete() {
        //Preparar contenido de peticion HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops/delete/2", HttpMethod.DELETE, request, Laptop.class);

        //Pruebas
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());// si no encuentra nada, funciona
    }

    @Test
    void deleteAll() {
        //Preparar contenido de peticion HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops/delete/all", HttpMethod.DELETE, request, Laptop.class);

        //Pruebas
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());//si se hace correctamente, devuelve NO CONTENT

    }
}