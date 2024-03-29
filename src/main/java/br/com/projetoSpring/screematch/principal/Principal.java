package br.com.projetoSpring.screematch.principal;

import br.com.projetoSpring.screematch.model.DadosEpisodio;
import br.com.projetoSpring.screematch.model.DadosSeries;
import br.com.projetoSpring.screematch.model.DadosTemporada;
import br.com.projetoSpring.screematch.model.Episodios;
import br.com.projetoSpring.screematch.service.ConsumoApi;
import br.com.projetoSpring.screematch.service.ConverterDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//        System.out.println(dadosSeries);

        DadosTemporada dadosTemporada = converterDados.obterDados(json, DadosTemporada.class);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosTemporada.totalTemporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO + dadosDigitadosNoInputDoTerminal.replace(" ", "+") + "&season=" + i + API_KEY);
            dadosTemporada = converterDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);

        }

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

//        System.out.println("\nTop 5 episódios: ");
//        dadosEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("peek do filtro " + e.titulo()))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .limit(5)
//                .map(e -> e.titulo())
//                .forEach(System.out::println);


        List<Episodios> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodios(t.numeroTemporada(), d)))
                .collect(Collectors.toList());


        System.out.println("A partir de que ano você deseja ver os episodios?");
        var ano = scanner.nextInt();
        scanner.nextLine();

        LocalDate dataBuca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBuca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() + " - Episódio: " + e.getTitulo() + " - Data de lançamento: " + e.getDataLancamento().format(formatter)
                ));

        List<Episodios> episodioFiltrado = episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBuca))
                .collect(Collectors.toList());


        System.out.println("aah, mas que épisodio que tu quer?");

        var nomeEpsodio = scanner.nextLine();
        Optional<Episodios> episodioBuscado = episodioFiltrado.stream()
                .filter(e -> e.getTitulo().toLowerCase().contains(nomeEpsodio.toLowerCase()))
                .findFirst();

       if (episodioBuscado.isPresent()){
           System.out.println("Episódio encontrado! Ma ôehh!");
           System.out.println(episodioBuscado);
       }else {
           System.out.println("Episódio não encontrado.");
       }

       Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
//               .filter(e -> e.getAvaliacao() > 9.0)
               .collect(Collectors.groupingBy(Episodios::getTemporada,
                       Collectors.averagingDouble(Episodios::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);
    }
}