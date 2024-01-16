package br.com.projetoSpring.screematch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(   @JsonProperty("Title") String titulo,
                                @JsonProperty("Episodes") List<DadosEpisodio> episodio,
                                @JsonProperty("totalSeasons") Integer totalTemporadas,
                                @JsonProperty("Season") Integer temporada)
                                                                             {
}
