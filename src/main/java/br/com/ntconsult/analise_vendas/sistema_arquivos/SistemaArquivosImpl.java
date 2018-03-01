/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos;

import br.com.ntconsult.analise_vendas.exception.AVSegurancaException;
import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  Abstração do sistema de arquivos dentro do contexto da aplicação
 * 
 * @author roger.gouveia
 */
//@ApplicationScoped
public class SistemaArquivosImpl implements SistemaArquivos {
    private final String VARIAVEL_AMBIENTE_HOME = "HOMEPATH";
    private final String PATH_RELATIVO_DATA_IN = "data/in";
    private final String PATH_RELATIVO_DATA_OUT = "data/out";
    
    private final Diretorio diretorioEntrada;
    private final Diretorio diretorioSaida;

    public SistemaArquivosImpl() throws AVSistemaArquivosException {
        Path in = Paths.get(getPathVariavelAmbiente(),PATH_RELATIVO_DATA_IN);
        diretorioEntrada = new Diretorio(in);
        Path out = Paths.get(getPathVariavelAmbiente(),PATH_RELATIVO_DATA_OUT);
        diretorioSaida = new Diretorio(out);
    }
    
    private String getPathVariavelAmbiente(){
        try {
            return System.getenv(VARIAVEL_AMBIENTE_HOME);
        } catch(SecurityException ex){
            throw new AVSegurancaException("Não foi possível acessar a variável de ambiente "+VARIAVEL_AMBIENTE_HOME, ex);
        }
    }

    @Override
    public Diretorio getDiretorioEntrada() {
        return diretorioEntrada;
    }

    @Override
    public Diretorio getDiretorioSaida() {
        return diretorioSaida;
    }
    
}
