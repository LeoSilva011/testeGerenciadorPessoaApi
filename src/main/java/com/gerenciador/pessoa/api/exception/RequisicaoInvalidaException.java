package com.gerenciador.pessoa.api.exception;

public class RequisicaoInvalidaException extends RuntimeException{

    public RequisicaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
