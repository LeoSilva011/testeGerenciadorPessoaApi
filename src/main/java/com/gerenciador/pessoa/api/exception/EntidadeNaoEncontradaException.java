package com.gerenciador.pessoa.api.exception;

import lombok.Getter;

import java.util.Date;

public class EntidadeNaoEncontradaException extends RuntimeException{

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }



}
