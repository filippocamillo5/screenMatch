package br.com.projetoSpring.screematch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodios {
    private Integer temporada;
    private String titulo;
    private Integer capitulo;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodios(Integer temporada, DadosEpisodio episodio) {
        this.temporada = temporada;
        this.titulo = episodio.titulo();
        this.capitulo = Integer.valueOf(episodio.capitulo());
        try {
            this.dataLancamento = LocalDate.parse(episodio.dataLancamento());
        }catch (DateTimeParseException e){
            this.dataLancamento = null;
        }
        try {
            this.avaliacao = Double.valueOf(episodio.avaliacao());
        }catch (NumberFormatException e){
            this.avaliacao = 0.0;
        }
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return capitulo;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.capitulo = numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", capitulo=" + capitulo +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento;
    }
}
