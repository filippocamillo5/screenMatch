package br.com.projetoSpring.screematch;

import br.com.projetoSpring.screematch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;


public class ScreematchApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(ScreematchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
//		Teste teste = new Teste();
//		teste.testeDois();
	}
}
