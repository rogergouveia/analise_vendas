/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos;

import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.Dados;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author roger.gouveia
 */
public interface Leitor extends Serializable{
    
    public List<Dados> lerArquivosEntrada(SistemaArquivosImpl sistemaArquivos) throws AVSistemaArquivosException;
}
