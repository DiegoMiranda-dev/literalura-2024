package com.challenge.literalura.principal;

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
            System.out.println("3. Salir");
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
                    MostrarLibrosGuardados();
                    break;
                case 3:
                    System.out.println("Gracias por usar la aplicación");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 3);
    }

    private void MostrarLibrosGuardados() {
    libro = repositorio.findAll();

    }




    private void buscarLibroPorNombre() {
        DatosResultados datosResultados = obtenerDatosLibros();
        Libro libro = new Libro(datosResultados);
        System.out.println(libro);

    }

    private DatosResultados obtenerDatosLibros() {
        System.out.println("Ingresa el nombre del libro que deseas buscar \uD83D\uDCD6 ");
        String nombreLibro = sc.nextLine().trim();
        String json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        System.out.println(json);
        DatosResultados datosResultados = conversorDatos.obtenerDatos(json, DatosResultados.class);
        System.out.println(datosResultados);
        return datosResultados;
    }

}
