/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos.dado;

/**
 * Interface que representa cadastros de pessoa (CPF,CNPJ)
 * @author roger.gouveia
 */
public interface CadastroPessoa {
    
    public String getComMascara();
    
    public String getSemMascara();
    
    public Long getComoInteiro();
    
}
