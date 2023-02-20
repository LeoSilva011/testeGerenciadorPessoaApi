package com.gerenciador.pessoa.api.controller;


import com.gerenciador.pessoa.api.exception.DataInvalidaException;
import com.gerenciador.pessoa.api.exception.EntidadeNaoEncontradaException;
import com.gerenciador.pessoa.domain.model.Pessoa;
import com.gerenciador.pessoa.domain.model.dto.PessoaDTO;
import com.gerenciador.pessoa.domain.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Endpoint para criar uma nova pessoa
    @PostMapping
    public ResponseEntity<?> salvarNovaPessoa(@RequestBody @Valid Pessoa pessoa){
        try{
            Pessoa novaPessoa = pessoaService.salvar(pessoa);

            // Retorna o objeto PessoaDTO como resposta da requisição
            return ResponseEntity.status(HttpStatus.CREATED).body(PessoaDTO.converterDto(pessoa));
        }catch (DataInvalidaException e){
            // Retorna uma resposta de erro quando a data de nascimento é inválida
            // para evitar erros na data de nascimento ela deve ser passada de seguinte forma
            // dia/mes/ano
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para buscar uma pessoa por ID
    @GetMapping("/{idPessoa}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long idPessoa){
        return pessoaService
                .buscarPorId(idPessoa)
                .map(pessoa -> ResponseEntity.ok(pessoa))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para listar todas as pessoas
    @GetMapping
    public Page<PessoaDTO> listarPessoas(@PageableDefault(page = 0, size = 15) Pageable paginacao){
        Page<Pessoa> pessoas =  pessoaService.listarPessoas(paginacao);
        // Retorna uma lista de objetos PessoaDTO paginada como resposta da requisição
        return PessoaDTO.converterDtoPaginado(pessoas);
    }

    // Endpoint para atualizar uma pessoa existente
    @PutMapping("/{idPessoa}")
    public ResponseEntity<?> atualizarCadastroPessoa(@PathVariable Long idPessoa, @RequestBody Pessoa pessoaAtualizado){

        try {
            Pessoa pessoa = pessoaService.atualizar(idPessoa, pessoaAtualizado);
            // Retorna o objeto PessoaDTO atualizado como resposta da requisição
            return ResponseEntity.ok(PessoaDTO.converterDto(pessoa));
        }catch (EntidadeNaoEncontradaException e){
            // Retorna uma resposta de erro quando a pessoa não existe
            return ResponseEntity.notFound().build();
        }catch (DataInvalidaException e){
            // Retorna uma resposta de erro quando a data de nascimento é inválida
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
