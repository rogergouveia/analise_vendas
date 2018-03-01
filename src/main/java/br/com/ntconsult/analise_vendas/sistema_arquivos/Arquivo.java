/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos;

import java.io.File;
import java.nio.file.Path;

/**
 *
 * @author roger.gouveia
 */
public class Arquivo extends File{

    private static final long serialVersionUID = 1L;
    
    public Arquivo(Path path) {
        super(path.toAbsolutePath().toString());
    }
    
}
