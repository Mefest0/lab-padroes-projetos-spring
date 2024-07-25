package com.dio.lab_padroes_projetos_spring.model;

import com.dio.lab_padroes_projetos_spring.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
