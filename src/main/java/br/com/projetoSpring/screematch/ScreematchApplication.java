package br.com.projetoSpring.screematch;

import br.com.projetoSpring.screematch.model.DadosSeries;f
import br.com.projetoSpring.screematch.service.ConsumoApi;
import br.com.projetoSpring.screematch.service.ConverterDados;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScreematchApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(ScreematchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=friends&apikey=7221847e");

		System.out.println(json);

		var converter= new ConverterDados();

		DadosSeries dados = converter.ObterDados(json, DadosSeries.class);

		System.out.println(dados);
	}
}
