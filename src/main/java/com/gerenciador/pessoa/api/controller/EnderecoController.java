package com.gerenciador.pessoa.api.controller;


import com.gerenciador.pessoa.api.exception.RequisicaoInvalidaException;
import com.gerenciador.pessoa.domain.model.Endereco;

import com.gerenciador.pessoa.domain.model.dto.EnderecoDTO;
import com.gerenciador.pessoa.domain.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    // Endpoint para criar um novo endereço
    @PostMapping
    public ResponseEntity<?> salvarNovaEndereco(@RequestBody @Valid Endereco novaEndereco){
        try {
            Endereco novoEndereco = enderecoService.salvar(novaEndereco);
            // retorna a resposta com o objeto salvo e status 201
            return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);

        }catch (RequisicaoInvalidaException e){
            // retorna uma resposta com status 400 (bad request) em caso de erro
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    // Endpoint para buscar endereços de uma pessoa pelo ID da pessoa
    @GetMapping("/{idPessoa}/pessoas")
    public  ResponseEntity<List<EnderecoDTO>> buscarEnderecoPorIdPessoa(@PathVariable Long idPessoa){
        try{

            List<Endereco> enderecos = enderecoService.enderecosPessoa(idPessoa);
            List<EnderecoDTO> enderecosConvertidoDto = EnderecoDTO.converter(enderecos);// converte a lista de endereços para lista de DTOs

            // retorna a lista de endereços convertidos e status 200
            return ResponseEntity.ok(enderecosConvertidoDto);

        }catch (RequisicaoInvalidaException e){

            return ResponseEntity.noContent().build();
        }
    }

    // Endpoint para buscar o endereço principal de uma pessoa pelo ID da pessoa
    @GetMapping("/{idPessoa}/endereco-principal")
    public ResponseEntity<Endereco> buscarEnderecoPrincipalPorIdPessoa(@PathVariable Long idPessoa){
        try{
            Endereco enderecoPrincipa = enderecoService.getEnderecoPrincipal(idPessoa);
            // retorna o endereço principal encontrado e status 200
            return ResponseEntity.ok(enderecoPrincipa);

        }catch (RequisicaoInvalidaException e){
            return ResponseEntity.notFound().build();
        }

    }



}
