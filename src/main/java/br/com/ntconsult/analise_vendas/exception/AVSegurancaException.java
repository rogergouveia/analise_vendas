/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.exception;

/**
 *
 * @author roger.gouveia
 */
public class AVSegurancaException extends SecurityException{

    private static final long serialVersionUID = 1L;

    public AVSegurancaException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
