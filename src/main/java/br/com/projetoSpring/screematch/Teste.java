package br.com.projetoSpring.screematch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Teste {
    public void testeDois() {

        List<Produto> teste = Arrays.asList(
                new Produto("bibi", "me ama"),
                new Produto("filippo", "nao me ama pouco"),
                new Produto("jojo", "me ama")
        );

        Map<String, String> negocio = teste.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.mapping(Produto::getNome, Collectors.joining(","))));

        System.out.println(negocio);
    }

}

class Produto {
    private String nome;
    private String categoria;

    public Produto(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }
}