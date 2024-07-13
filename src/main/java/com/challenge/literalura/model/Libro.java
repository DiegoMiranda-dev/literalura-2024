package com.challenge.literalura.model;

import java.util.List;

public class Libro {
    private String titulo;
    private List<String> autores;
    private List<String> lenguajes;
    private String descargas;

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
