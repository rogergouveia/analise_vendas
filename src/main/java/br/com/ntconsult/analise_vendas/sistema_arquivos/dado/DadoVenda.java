/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos.dado;

import br.com.ntconsult.analise_vendas.utils.Parser;
import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author roger.gouveia
 */
public class DadoVenda extends DadoImpl{
    private static final long serialVersionUID = 1L;
    
    private String codigo;
    private Integer id;
    private final List<DadoVendaItem> listaVendaItem = new ArrayList<>();
    private String nomeVendedor;
    
//    @Inject
//    private Parser parser;

    public DadoVenda(String dado) throws AVSistemaArquivosException {
        super(dado);
        inicializarDados();
    }
    
    private void inicializarDados() throws AVSistemaArquivosException{
        if (getDados().size() != 4){
            throw new AVSistemaArquivosException("Falha de integridade dos dados: Dado de venda fora do formato");
        }
        
        codigo = getDados().get(0);
        id = Integer.valueOf(getDados().get(1));
        listaVendaItem.addAll(getListaVendaItem());
        nomeVendedor = getDados().get(3);
    }
    
    private List<DadoVendaItem> getListaVendaItem() throws AVSistemaArquivosException{
        List<DadoVendaItem> retorno = new ArrayList<>();
        String tempDado = getDados().get(2).replaceAll("\\[", "").replaceAll("\\]", "");
        Parser parser = new Parser();
        for (String vendaItem: parser.parse(tempDado, ",")){
            retorno.add(new DadoVendaItem(vendaItem));
        }
        return retorno;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public Integer getId() {
        return id;
    }

    public List<DadoVendaItem> getDetalhesVenda() {
        return listaVendaItem;
    }
    
    public Dinheiro getPrecoTotal(){
        BigDecimal retorno = BigDecimal.ZERO.setScale(2);
        for(DadoVendaItem v:getDetalhesVenda()){
            retorno = retorno.add(v.getPrecoTotal());
        }
        return new Dinheiro(retorno);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.codigo);
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.listaVendaItem);
        hash = 29 * hash + Objects.hashCode(this.nomeVendedor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DadoVenda other = (DadoVenda) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.nomeVendedor, other.nomeVendedor)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.listaVendaItem, other.listaVendaItem)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DadoVenda{" + "codigo=" + codigo + ", id=" + id + ", listaVendaItem=" + listaVendaItem + ", nomeVendedor=" + nomeVendedor + '}';
    }

    
}
