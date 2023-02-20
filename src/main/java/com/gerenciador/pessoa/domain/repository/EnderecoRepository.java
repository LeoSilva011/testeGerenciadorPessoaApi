package com.gerenciador.pessoa.domain.repository;

import com.gerenciador.pessoa.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByFkPessoa(Long id);

    Endereco findByFkPessoaAndEnderecoPrincipal(Long id, Boolean principal);
}
