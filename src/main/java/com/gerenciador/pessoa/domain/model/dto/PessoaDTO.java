package com.gerenciador.pessoa.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciador.pessoa.domain.model.Pessoa;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Optional;


@Getter
public class PessoaDTO {

    private Long idPessoa;

    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;


    public PessoaDTO(Pessoa pessoa) {
        this.idPessoa = pessoa.getIdPessoa();
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento();
    }

    public static Page<PessoaDTO> converterDtoPaginado(Page<Pessoa> pessoas) {

        return pessoas.map(PessoaDTO::new);

    }

    public static Optional<PessoaDTO> converterDto(Pessoa pessoa) {
        Optional<Pessoa> essoas = Optional.of(pessoa);
        return essoas.map(PessoaDTO::new);

    }


}
