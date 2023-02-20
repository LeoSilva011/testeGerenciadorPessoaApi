package com.gerenciador.pessoa.domain.model.dto;

import com.gerenciador.pessoa.domain.model.Endereco;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EnderecoDTO {

    private Long idEndereco;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Boolean enderecoPrincipal;

    public EnderecoDTO(Endereco endereco) {
        this.idEndereco = endereco.getIdEndereco();
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.enderecoPrincipal = endereco.getEnderecoPrincipal();
    }


    public static List<EnderecoDTO> converter(List<Endereco> enderecos) {

        return enderecos
                .stream()
                .map(EnderecoDTO::new)
                .collect(Collectors.toList());

    }
}
