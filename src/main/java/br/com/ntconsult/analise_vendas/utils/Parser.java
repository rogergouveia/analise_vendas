/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author roger.gouveia
 */
//@ApplicationScoped
public class Parser {
    
    public List<String> parse(String entrada, String separador){
        return new ArrayList<>(Arrays.asList(entrada.split(separador)));
    }
//    
//    public List<List<String>> parse(String entrada, String separador1D, String separador2D){
//        List<List<String>> retorno = new ArrayList<>();
//        List<String> linhas = parse(entrada, separador2D);
//        for (String linha: linhas){
//            retorno.add(parse(linha, separador1D));
//        }
//        
//        return retorno;
//        
//    }
}
