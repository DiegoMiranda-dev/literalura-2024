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
    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Autor> autores;
    private List<String> lenguajes;
    private String descargas;


    public Libro(DatosResultados datosResultados) {
        if (!datosResultados.resultados().isEmpty()) {
            this.titulo = datosResultados.resultados().get(0).titulo();
            this.autores = datosResultados.resultados().get(0).autores().stream()
                    .map(autor -> {
                        Autor a = new Autor(
                                autor.nombre(),
                                autor.anoNacimiento() != null ? autor.anoNacimiento().toString() : null,
                                autor.anoMuerte() != null ? autor.anoMuerte().toString() : null
                        );
                        a.setLibro(this);
                        return a;
                    })
                    .collect(Collectors.toList());
            this.lenguajes = datosResultados.resultados().get(0).lenguajes();
            this.descargas = datosResultados.resultados().get(0).descargas();
        }
    }


    public Libro() {
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
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
        return "Autor encontrado \uD83D\uDCDA " + "\n" +
                "Titulo: " + titulo + "\n" +
                "Autores: " + autores + "\n" +
                "Lenguajes: " + lenguajes + "\n" +
                "Descargas: " + descargas;
    }
}
