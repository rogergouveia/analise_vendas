/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos;

import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;
import br.com.ntconsult.analise_vendas.relatorio.Relatorio;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author roger.gouveia
 */
public class EscritorImpl implements Escritor {
    
    @Override
    public void gerarRelatorio(SistemaArquivosImpl sistemaArquivos, Relatorio relatorio) throws AVSistemaArquivosException{
        try {
            Files.write(relatorio.toPath(), relatorio.getAsLines());
            System.out.println("Processado: "+relatorio.getName());
        } catch (IOException ex) {
            throw new AVSistemaArquivosException("Não foi possível escrever arquivo de relatório",ex);
        }
    }
}
