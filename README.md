# LiterAlura
Aplicación de consola desarrollada en Java para buscar libros, registrarlos y mostrarlos. 
<br />
La información de los libros se obtiene de la API Gutendex (JSON API del Proyecto Gutenberg) y se registra la información en una base de datos PostgrSQL.

## Características

Menú en consola indicando las opciones.

![Inicio](/images/1.png)

1. **Buscar libro por titulo**
2. **Listar libros registrados en la base de datos**
3. **Listar libros registrados en la base de datos**
4. **Listar autores vivos en un determinado año**
5. **Listar libros por idiomas (inglés, español, francés)**
6. **Salir**
  
## Requisitos
- Java 17 o superior.
- Spring Boot 3.2.5
- PostgreSQL.
- Maven

## Instalación
1. Clonar repositorio o descargar el código fuente.
```cmd
git clone https://github.com/juniormr/LiterAlura.git
cd LiterAlura
```

3. Descargar las dependencias del archivo pom.xml con Maven.
4. Configurar la base de datos PostgreSQL y actulizar las credenciales del archivo `application.properties`
5. Ejecutar la aplicación





