/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos.dado;

import br.com.ntconsult.analise_vendas.utils.Parser;
import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger.gouveia
 */
public abstract class DadoImpl implements Dado {

    private static final long serialVersionUID = 1L;
    
    private String separador="ç";
    private final List<String> dados = new ArrayList<>();
    
//    @Inject
//    private Parser parser;
    
    public DadoImpl(String dado, String separador) throws AVSistemaArquivosException {
        validarDadoExiste(dado);
        if (separador != null && !separador.trim().isEmpty()){
            this.separador = separador;
        }
        inicializarDados(dado);
    }
    
    public DadoImpl(String dado) throws AVSistemaArquivosException {
        this(dado,null);
    }
    
    private void validarDadoExiste(String dado) throws AVSistemaArquivosException{
        if (dado == null || dado.trim().isEmpty()){
            throw new AVSistemaArquivosException("Falha de integridade dos dados: Dado vazio.");
        }
    }
    
    private void inicializarDados(String dado){
        Parser parser = new Parser();
        dados.addAll(parser.parse(dado, separador));
    }

    protected List<String> getDados(){
        return new ArrayList<>(dados);
    }
    
    
}
