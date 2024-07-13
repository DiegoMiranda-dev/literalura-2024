package com.challenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private List<String> autores;
    private List<String> lenguajes;
    private String descargas;

    public Libro(DatosResultados datosResultados) {
        this.titulo = datosResultados.resultados().get(0).titulo();
        this.autores = datosResultados.resultados().get(0).autores().stream().map(DatosResultados.DatosLibro.Autor::nombre).toList();
        this.lenguajes = datosResultados.resultados().get(0).lenguajes();
        this.descargas = datosResultados.resultados().get(0).descargas();

    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public String getDescargas() {
        return descargas;
    }

    public void setDescargas(String descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "Libro [" +
                "Titulo: '" + titulo + '\'' +
                ", Autores: '" + autores + '\'' +
                ", Lenguajes: '" + lenguajes + '\'' +
                ", Descargas: '" + descargas + '\'' +
                " ]";
    }
}
