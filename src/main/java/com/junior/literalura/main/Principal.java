package com.junior.literalura.main;

import com.junior.literalura.model.Author;
import com.junior.literalura.model.AuthorData;
import com.junior.literalura.model.Book;
import com.junior.literalura.model.BookData;
import com.junior.literalura.repository.AuthorRepository;
import com.junior.literalura.repository.BookRepository;
import com.junior.literalura.service.APIHandler;
import com.junior.literalura.service.DataConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    String menu = """
            Seleccione:
            1- Buscar libro por titulo
            2- Listar libros registrados
            3- Listar autores registrados
            4- Listar autores vivos en un determinado año
            5- Listar libros por idioma
            6- Salir
            """;
    public static final String URL = "https://gutendex.com/books/?search=";
    public static final List<String> allowedLanguages = List.of(new String[]{"en", "es", "fr"});
    Scanner scan = new Scanner(System.in);
    String op = "0";
    static String title;
    private List<Author> authors;

    private final APIHandler apiHandler = new APIHandler();
    private final DataConverter converter = new DataConverter();

    private final AuthorRepository repositoryA;
    private final BookRepository repositoryB;

    public Principal(AuthorRepository repositoryA, BookRepository repositoryB) {
        this.repositoryA = repositoryA;
        this.repositoryB = repositoryB;
    }

    public void start() {
        while (!op.equals("6")) {
            System.out.println(menu);
            op = scan.nextLine();
            switch (op) {
                case "1" -> searchBook();
                case "2" -> showLibrary();
                case "3" -> showAuthors();
                case "4" -> showAuthorsInEspecifiedYear();
                case "5" -> showBooksByLanguage();
                default -> op = "6";

            }
        }
        scan.close();
        System.out.println("saliendo");
    }

    public void searchBook() {
        System.out.println("Ingrese titulo: ");
        title = scan.nextLine().toLowerCase().replaceAll(" ", "%20");
        var json = apiHandler.getData(URL + title);
        //Check if book was found in gutendex db
        if (converter.bookExist(json)) {
            BookData newBookData = converter.getInfo(json, BookData.class);
            AuthorData newAuthorData = newBookData.authors().get(0);
            //Inserting data into clases
            Book newBook = new Book(newBookData);
            Author newAuthor = new Author(newAuthorData);
            authors = repositoryA.findAll();
            //Check if author already exists in db
            Optional<Author> searchedAuthor = authors.stream()
                    .filter(author -> author.getName().equals(newAuthor.getName()))
                    .findAny();
            if (searchedAuthor.isPresent()) {
                List<Book> newBooks = searchedAuthor.get().getBooks();
                newBooks.add(newBook);
                searchedAuthor.get().setBooks(newBooks);
                repositoryA.save(searchedAuthor.get());
                System.out.println(searchedAuthor.get());

            } else {
                List<Book> newBooks = new ArrayList<>();
                newBooks.add(newBook);
                newAuthor.setBooks(newBooks);
                repositoryA.save(newAuthor);
                System.out.println(newAuthor);
                System.out.println(newAuthor.getBooks());
            }

        } else {
            System.out.println("Libro no encontrado");
        }
        System.out.println("Presione enter para continuar o ingrese 6 para salir");
        op = scan.nextLine();
    }

    public void showLibrary() {
        authors = repositoryA.findAll();
        System.out.println("Libros registrados: ");
        authors.forEach(author -> author.getBooks().forEach(System.out::println));
        System.out.println("Presione enter para continuar o ingrese 6 para salir");
        op = scan.nextLine();
    }

    public void showAuthors() {
        authors = repositoryA.findAll();
        authors.forEach(System.out::println);
        System.out.println("Presione enter para continuar o ingrese 6 para salir");
        op = scan.nextLine();
    }

    public void showAuthorsInEspecifiedYear() {
        System.out.println("Ingrese año en el que desea buscar autores:");
        Double year = Double.valueOf(scan.nextLine());
        Optional<List<Author>> authorsT = repositoryA.findAllByBirthLessThanEqualAndDeathGreaterThanEqual(year, year);
        if (authorsT.isPresent()) {
            if (!authorsT.get().isEmpty()) {
                authorsT.get().forEach(System.out::println);
            } else {
                System.out.println("No se han encontrado autores vivos en ese año");
            }
        }
        System.out.println("Presione enter para continuar o ingrese 6 para salir");
        op = scan.nextLine();
    }

    public void showBooksByLanguage() {
        System.out.println("Ingrese el idioma de los libros que desee buscar:");
        System.out.println("(en, es, fr)");
        String language = checkInput(scan.nextLine().toLowerCase(), allowedLanguages);
        List<Book> books = repositoryB.findAllByLanguageIs(language);
        if (books.isEmpty()) {
            System.out.println("No hay libros en ese idioma");
        } else {
            books.forEach(System.out::println);
        }
        System.out.println("Presione enter para continuar o ingrese 6 para salir");
        op = scan.nextLine();
    }

    public String checkInput(String language, List<String> allowedLanguages) {
        boolean validOption = false;
        while (!validOption) {
            if (!allowedLanguages.contains(language)) {
                System.out.println("Ingrese una opción válida:");
                language = scan.nextLine().toLowerCase();
            } else {
                validOption = true;
            }
        }
        return language;
    }

}
