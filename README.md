## Ejercicio 1

* Probar a empaquetar la aplicación con maven package desde Intellij IDEA
  * OK

* Desde terminal en Intellij IDEA verificar que se se ejecuta correctamente con el comando:
java -jar target/spring-deploy-1.0.jar

  * No se despliega bien porque tengo por defecto la version 8 del JDK y el proyecto está configurado con la version 17

* Crear un perfil para dev y otro para test con una propiedad nueva que carguemos en el controlador.
  * OK

## Ejercicio 2

* Desplegar el API REST de Laptops en Heroku y verificar funcionamiento desde POSTMAN.
  * No puedo crear cuenta en Heroku desde Cuba

## Ejercicio 3

* Añadir Spring Security sobre el proyecto API REST de Laptops y configurar 2 usuarios en memoria utilizando una clase WebSecurityConfig.