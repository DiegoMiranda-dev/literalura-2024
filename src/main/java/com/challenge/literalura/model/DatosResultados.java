package com.challenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResultados(@JsonAlias("results") List<DatosLibro> resultados) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DatosLibro(
            @JsonAlias("title") String titulo,
            @JsonAlias("authors") List<Autor> autores,
            @JsonAlias("languages") List<String> lenguajes,
            @JsonAlias("download_count") String descargas
    ) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Autor(
                @JsonAlias("name") String nombre,
                @JsonAlias("birth_year") Integer anoNacimiento,
                @JsonAlias("death_year") Integer anoMuerte
        ) {
        }

    }
}
