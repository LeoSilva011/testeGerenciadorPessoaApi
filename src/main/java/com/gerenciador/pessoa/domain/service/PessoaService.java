package com.gerenciador.pessoa.domain.service;



import com.gerenciador.pessoa.api.exception.DataInvalidaException;
import com.gerenciador.pessoa.api.exception.EntidadeNaoEncontradaException;
import com.gerenciador.pessoa.domain.model.Pessoa;
import com.gerenciador.pessoa.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa novaPessoa){

        LocalDate dataInvalida = LocalDate.of(1899,12,31);

        // Verifica se a data de nascimento informada é inválida, lançando uma exceção caso seja
        if (novaPessoa.getDataNascimento().isBefore(dataInvalida)){
            throw new DataInvalidaException(String.format("A data informada é invalida"));
        }
        return pessoaRepository.save(novaPessoa);
    }

    public Optional<Pessoa> buscarPorId(Long id){

        return pessoaRepository.findById(id);
    }

    public Page<Pessoa> listarPessoas(Pageable paginacao){
        return pessoaRepository.findAll(paginacao);
    }

    public Pessoa atualizar(Long id, Pessoa pessoaAtualizado){

        Optional<Pessoa> pessoalAtual = buscarPorId(id);

        // Se a pessoa não existe, lança uma exceção
        if (pessoalAtual.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("O usuario com id: %d não esta cadastrado", id));

        }
        // Seta o id da pessoa atualizada
        pessoaAtualizado.setIdPessoa(id);

        // Mantém os endereços da pessoa atual
        pessoaAtualizado.setEnderecos(pessoalAtual.get().getEnderecos());

        // Salva as alterações e retorna a pessoa atualizada
        Pessoa pessoa = salvar(pessoaAtualizado);
        return pessoa;

    }

}
