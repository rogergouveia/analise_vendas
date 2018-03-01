/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos.dado;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author roger.gouveia
 */
public interface Dados extends Serializable{
    public Collection<Dado> getDados();
}
