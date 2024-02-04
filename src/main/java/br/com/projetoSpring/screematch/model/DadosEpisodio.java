package br.com.projetoSpring.screematch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonProperty("Title")String titulo,
                            @JsonProperty("Episode") String capitulo,
                            @JsonProperty("imdbRating") String avaliacao,
                            @JsonProperty("Released") String dataLancamento){

}
