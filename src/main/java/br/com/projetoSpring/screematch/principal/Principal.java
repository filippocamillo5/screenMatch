package br.com.projetoSpring.screematch.principal;

import br.com.projetoSpring.screematch.model.DadosSeries;
import br.com.projetoSpring.screematch.model.DadosTemporada;
import br.com.projetoSpring.screematch.service.ConsumoApi;
import br.com.projetoSpring.screematch.service.ConverterDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=7221847e";
    Scanner scanner = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();

    ConverterDados converterDados = new ConverterDados();

    public void exibeMenu() {
        System.out.println("Digite o titulo de uma série para iniciar a busca:");
        var dadosDigitadosNoInputDoTerminal = scanner.nextLine();
        var json = consumoApi.obterDados(ENDERECO + dadosDigitadosNoInputDoTerminal.replace(" ", "+") + API_KEY);
        DadosSeries dadosSeries = converterDados.obterDados(json, DadosSeries.class);
        System.out.println(dadosSeries);

        DadosTemporada dadosTemporada = converterDados.obterDados(json, DadosTemporada.class);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i<=dadosTemporada.totalTemporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO + dadosDigitadosNoInputDoTerminal.replace(" ", "+") +"&season=" + i + API_KEY);
            dadosTemporada = converterDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);

        }
        temporadas.forEach(temporada -> System.out.println(temporada));


    }
}