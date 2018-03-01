/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos;

import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;
import java.io.File;
import java.nio.file.Path;

/**
 *
 * @author roger.gouveia
 */
public class Diretorio extends File {
    private static final long serialVersionUID = 1L;
    
    public Diretorio(Path path) throws AVSistemaArquivosException{
        super(path.toAbsolutePath().toString());
        inicializarDiretorio(path);
    }
    
    private void inicializarDiretorio(Path path) throws AVSistemaArquivosException{
        File file = path.toAbsolutePath().toFile();
        if (!file.exists()){
            file.mkdirs();
        }
        else if (!file.isDirectory()){
            throw new AVSistemaArquivosException(path.toAbsolutePath().toFile()+" já existe e não é diretório");
            
        }
    }
    
}
