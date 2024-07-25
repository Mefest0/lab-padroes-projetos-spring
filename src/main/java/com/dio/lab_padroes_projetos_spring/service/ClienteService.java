package com.dio.lab_padroes_projetos_spring.service;

import com.dio.lab_padroes_projetos_spring.model.Cliente;

public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);

    void inserir (Cliente cliente);
    void atualizar (Cliente cliente, Long id);
    void deletar (Long id);
}
