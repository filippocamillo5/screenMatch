package br.com.projetoSpring.screematch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DadosSeries(@JsonProperty("Title") String titulo,
                          @JsonProperty("totalSeasons") Integer totalTemporadas,
                          @JsonProperty("imdbRating") String avaliacoes) {
}
