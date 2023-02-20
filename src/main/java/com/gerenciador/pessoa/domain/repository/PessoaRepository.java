package com.gerenciador.pessoa.domain.repository;

import com.gerenciador.pessoa.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
