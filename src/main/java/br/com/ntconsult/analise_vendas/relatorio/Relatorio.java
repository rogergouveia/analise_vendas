/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.relatorio;

import br.com.ntconsult.analise_vendas.sistema_arquivos.Arquivo;
import br.com.ntconsult.analise_vendas.sistema_arquivos.Diretorio;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.Dados;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author roger.gouveia
 */
public abstract class Relatorio extends Arquivo{
    private static final long serialVersionUID = 1L;
    
    private final Dados dados;
    
    public Relatorio(Diretorio dir, String nomeArquivo, Dados dados) {
        super(Paths.get(dir.toPath().toAbsolutePath().toString(), nomeArquivo));
        this.dados = dados;
    }

    public Dados getDados() {
        return dados;
    }
    
    public abstract List<String> getAsLines();
    
}
