package br.com.projetoSpring.screematch.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface  IConverterDados {

    public <T> T ObterDados (String json, Class<T> classe);
}
