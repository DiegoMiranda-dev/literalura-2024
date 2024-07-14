package com.challenge.literalura.principal;

import com.challenge.literalura.model.Autor;
import com.challenge.literalura.model.DatosResultados;
import com.challenge.literalura.model.Libro;
import com.challenge.literalura.repository.LibroRepositorio;
import com.challenge.literalura.services.ConsumoApi;
import com.challenge.literalura.services.ConversorDatos;

import java.util.List;
import java.util.Scanner;


public class Principal {
    private final Scanner sc = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    ConsumoApi consumoApi = new ConsumoApi();
    private List<Libro> libro;
    private ConversorDatos conversorDatos = new ConversorDatos();
    private LibroRepositorio repositorio;

    public Principal(LibroRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void menu() {
        int opcion = 0;
        do {
            System.out.println("------------ \uD83D\uDE80 ¡Bienvenido a Literalura! \uD83D\uDE80 ------------");
            System.out.println("Selecciona una opción \uD83D\uDC3C ");
            System.out.println("1. Buscar libro por nombre");
            System.out.println("2. Mostrar los libros buscados");
            System.out.println("3. Mostrar autores de libros buscados");
            System.out.println("4. Mostrar autores vivos en un periodo de tiempo");
            System.out.println("5. Mostrar libros por idioma");
            System.out.println("6. Salir");
            System.out.println("---------------------------------------------------");
            try {
                opcion = Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido");
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarLibroPorNombre();
                    break;
                case 2:
                    mostrarLibrosGuardados();
                    break;
                case 3:
                    mostrarAutoresDeLibrosBuscados();
                    break;
                case 4:
                    autoresVivosEnPeriodoTiempo();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 6:
                    System.out.println("Saliendo... \uD83D\uDE3C \uD83D\uDE3C");
                    break;
                default:
                    System.out.println("Opción no válida \uD83D\uDEA9 \uD83D\uDEA9");
                    break;
            }
        } while (opcion != 6);
    }

    private void mostrarLibrosPorIdioma() {
        List<Libro> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros guardados \uD83E\uDD21 \uD83E\uDD21");
            return;
        } else {
            System.out.println("-------- ✅ Libros guardados por idioma ✅ --------");
            System.out.println("Ingresa el idioma a buscar \uD83D\uDCD6");
            String idioma = sc.nextLine();

            boolean encontrado = false;

            for (Libro libro : libros) {
                if (libro.getLenguajes().contains(idioma)) {
                    System.out.println("Título: " + libro.getTitulo() +
                            "\nAutores: " + libro.getAutores() +
                            "\nLenguajes: " + libro.getLenguajes() +
                            "\nDescargas: " + libro.getDescargas() +
                            "\n-----------------------------------");
                    encontrado = true; // Se encontró al menos un libro en el idioma
                }
            }
            if (!encontrado) {
                System.out.println("No se encontraron libros en el idioma ingresado \uD83D\uDEA9");
            }

        }

    }

    private void autoresVivosEnPeriodoTiempo() {
        System.out.println("Ingresa el año de inicio del periodo a buscar \uD83D\uDC7B");
        Integer inicio = Integer.parseInt(sc.nextLine());
        System.out.println("Ingresa el año de fin del periodo a buscar \uD83D\uDC7B");
        Integer fin = Integer.parseInt(sc.nextLine());
        List<Libro> libros = repositorio.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros guardados \uD83D\uDCA9");
        } else {
            System.out.println("-------- ✅ Autores vivos de " + inicio + " a " + fin + " ✅ --------");
            libros.forEach(libro -> {
                libro.getAutores().forEach(autor -> {
                    Integer anoNacimiento = autor.getAnoNacimiento();
                    Integer anoMuerte = autor.getAnoMuerte();

                    if (anoNacimiento != null && anoNacimiento <= fin && (anoMuerte == null || anoMuerte >= inicio)) {
                        System.out.println("Autor: " + autor.getNombre() + "\n" +
                                "Año de nacimiento: " + anoNacimiento + "\n" +
                                "Año de muerte: " + (anoMuerte != null ? anoMuerte : "N/A") + "\n" +
                                "-----------------------------------");
                    }
                });
            });
        }
    }

    private void mostrarAutoresDeLibrosBuscados() {
        List<Libro> Libro = repositorio.findAll();
        if (Libro.isEmpty()) {
            System.out.println("No hay libros guardados \uD83D\uDCA9");
        } else {
            System.out.println("-------- ✅ Autores de libros guardados ✅ --------");
            Libro.forEach(l -> {
                System.out.println(
                        "Título: " + l.getTitulo() + "\n" +
                                "Autor: " + l.getAutores() +
                                "\n-----------------------------------");
            });

        }
    }

    private void mostrarLibrosGuardados() {
        List<Libro> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros guardados");

        } else {
            System.out.println("-------- ✅ Libros guardados ✅ --------");
            libros.forEach(l -> {
                System.out.println("Título: " + l.getTitulo() +
                        "\nAutores: " + l.getAutores() +
                        "\nLenguajes: " + l.getLenguajes() +
                        "\nDescargas: " + l.getDescargas() +
                        "\n-----------------------------------");
            });


        }
    }

    private void buscarLibroPorNombre() {

        DatosResultados datosResultados = obtenerDatosLibros();
        if (datosResultados.resultados().isEmpty()) {
            System.out.println("No se encontraron resultados.");
            return;
        }

        Libro libroEnBD = repositorio.findByTitulo(datosResultados.resultados().get(0).titulo()).orElse(null);
        if (libroEnBD != null) {
            System.out.println("El libro ya fue buscado previamente \uD83D\uDC37 \uD83D\uDC37");
            menu();
        } else {
            Libro libro = new Libro(datosResultados);
            for (Autor autor : libro.getAutores()) {
                autor.setLibro(libro);
            }
            repositorio.save(libro);
            System.out.println("Libro guardado exitosamente: \n" + libro);
        }


    }

    private DatosResultados obtenerDatosLibros() {
        System.out.println("Ingresa el nombre del libro que deseas buscar \uD83D\uDCD6 ");
        String nombreLibro = sc.nextLine().trim();
        DatosResultados datosResultados;
        String json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        datosResultados = conversorDatos.obtenerDatos(json, DatosResultados.class);
        return datosResultados;

    }
}


