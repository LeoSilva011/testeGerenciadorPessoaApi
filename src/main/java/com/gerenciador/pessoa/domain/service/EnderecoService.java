package com.gerenciador.pessoa.domain.service;


import com.gerenciador.pessoa.api.exception.RequisicaoInvalidaException;
import com.gerenciador.pessoa.domain.model.Endereco;
import com.gerenciador.pessoa.domain.model.Pessoa;
import com.gerenciador.pessoa.domain.repository.EnderecoRepository;
import com.gerenciador.pessoa.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Endereco salvar(Endereco novoEndereco){

        Long idPessoa = novoEndereco.getFkPessoa();
        validarExistenciaPessoa(idPessoa);
        // verifica se o endereço é o principal e se já existe um endereço principal cadastrado para a pessoa
        if (novoEndereco.getEnderecoPrincipal()) {
            Endereco enderecoPrincipal = enderecoRepository.findByFkPessoaAndEnderecoPrincipal(idPessoa, true);
            if (enderecoPrincipal != null) {
                throw new RequisicaoInvalidaException("Já existe um endereço principal para esta pessoa.");
            }
        }

        return enderecoRepository.save(novoEndereco);
    }

    public List<Endereco> enderecosPessoa(Long idPessoa){
        validarExistenciaPessoa(idPessoa);
        return enderecoRepository.findByFkPessoa(idPessoa);
    }

    public Endereco getEnderecoPrincipal(Long idPesssoa){
        validarExistenciaPessoa(idPesssoa);
        return enderecoRepository.findByFkPessoaAndEnderecoPrincipal(idPesssoa, true);
    }


    private void validarExistenciaPessoa(Long idPessoa){
        //verifica se o ID é valido
        if (idPessoa == null) {
            throw new RequisicaoInvalidaException("ID da pessoa não pode ser nulo.");
        }
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);

        if(pessoa.isEmpty()){
            throw new RequisicaoInvalidaException(String.format("Usuário de ID %d não está cadastrado.",idPessoa));

        }

    }


}
