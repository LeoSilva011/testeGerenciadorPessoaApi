package com.gerenciador.pessoa.domain.model;

import javax.persistence.*;
import lombok.Data;


@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;
    private String logradouro;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private Boolean enderecoPrincipal;
    @Column(nullable = false)
    private Long fkPessoa;


}
