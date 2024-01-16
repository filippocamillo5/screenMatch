package br.com.projetoSpring.screematch.service;

public interface  IConverterDados {

    public <T> T obterDados(String json, Class<T> classe);
}
