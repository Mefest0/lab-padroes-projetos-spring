package com.dio.lab_padroes_projetos_spring.service.impl;

import com.dio.lab_padroes_projetos_spring.model.Cliente;
import com.dio.lab_padroes_projetos_spring.model.ClienteRepository;
import com.dio.lab_padroes_projetos_spring.model.Endereco;
import com.dio.lab_padroes_projetos_spring.model.EnderecoRepository;
import com.dio.lab_padroes_projetos_spring.service.ClienteService;
import com.dio.lab_padroes_projetos_spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(enderecoRepository.findById(cep).get());
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Cliente cliente, Long id) {
        Optional<Cliente> clienteBusca = clienteRepository.findById(id);
        if (clienteBusca.isPresent()) {
            salvarClienteComCep(cliente);
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}