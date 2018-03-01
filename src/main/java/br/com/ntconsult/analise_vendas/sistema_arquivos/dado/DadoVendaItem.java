/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos.dado;

import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;
import java.math.BigDecimal;

/**
 *
 * @author roger.gouveia
 */
public class DadoVendaItem extends DadoImpl {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long quantidade;
    private Dinheiro precoItem;

    public DadoVendaItem(String dado) throws AVSistemaArquivosException {
        super(dado, "-");
        inicializarDados();
    }

    private void inicializarDados() throws AVSistemaArquivosException {
        if (getDados().size() != 3) {
            throw new AVSistemaArquivosException("Falha de integridade dos dados: Detalhe do Dado de venda fora do formato");
        }
        id = Long.valueOf(getDados().get(0));
        quantidade = Long.valueOf(getDados().get(1));
        precoItem = new Dinheiro(getDados().get(2));
    }

    public Long getId() {
        return id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public Dinheiro getPrecoItem() {
        return new Dinheiro(precoItem.doubleValue());
    }
    
    public Dinheiro getPrecoTotal(){
        return new Dinheiro(getPrecoItem().multiply(BigDecimal.valueOf(quantidade)));
    }

}
