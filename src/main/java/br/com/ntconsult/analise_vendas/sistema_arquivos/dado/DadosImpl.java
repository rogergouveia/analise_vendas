/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos.dado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author roger.gouveia
 */
public class DadosImpl implements Dados{

    private static final long serialVersionUID = 1L;
    
    private final String nomeArquivo;
    private final List<DadoImpl> listaDados = new ArrayList<>();

    public DadosImpl(String nomeArquivo, List<DadoImpl> dados) {
        this.nomeArquivo = nomeArquivo;
        listaDados.addAll(dados);
    }
    
    

    @Override
    public Collection<Dado> getDados() {
        return new ArrayList<>(listaDados);
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }
    
    
    
}
