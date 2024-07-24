package com.challenge.literalura.model;

import jakarta.persistence.*;


@Entity
@Table(name = "autores")
public class Autor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Integer anoNacimiento;
        private Integer anoMuerte;
        private String nombre;
        @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "libro_id")
        private Libro libro;

    public Autor(String nombre, String anoNacimiento, String anoMuerte) {
        this.nombre = nombre;
        this.anoNacimiento = (anoNacimiento != null && !anoNacimiento.isEmpty()) ? Integer.parseInt(anoNacimiento) : null;
        this.anoMuerte = (anoMuerte != null && !anoMuerte.isEmpty()) ? Integer.parseInt(anoMuerte) : null;

    }

    public Autor(){}

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoMuerte() {
        return anoMuerte;
    }

    public void setAnoMuerte(Integer anoMuerte) {
        this.anoMuerte = anoMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return nombre;

    }
}
