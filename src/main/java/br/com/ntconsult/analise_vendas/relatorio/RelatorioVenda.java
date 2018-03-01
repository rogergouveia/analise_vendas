/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.relatorio;

import br.com.ntconsult.analise_vendas.sistema_arquivos.Diretorio;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.Dado;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.DadoCliente;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.DadoVenda;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.DadoVendedor;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.DadosImpl;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.Dinheiro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;

/**
 * 
 * @author roger.gouveia
 */
public class RelatorioVenda extends Relatorio{
    private static final long serialVersionUID = 1L;
    
    private Set<DadoCliente> clientes = new HashSet<>();
    private Set<DadoVendedor> vendedores = new HashSet<>();
    private DadoVenda vendaMaisCara;
    private DadoVendedor piorVendedor;
    
    private Map<String,Dinheiro> performanceVendedores = new HashMap<>();
    
    public RelatorioVenda(Diretorio dir, String nomeArquivo, DadosImpl dados) {
        super(dir,getNomeArquivoFormatado(nomeArquivo),dados);
        inicializarDados();
        
    }
    
    private static String getNomeArquivoFormatado(String nomeArquivo){
        String ext = FilenameUtils.getExtension(nomeArquivo);
        String baseName = FilenameUtils.getBaseName(nomeArquivo);
        return baseName+".done."+ext;
    }
    
    private void inicializarDados(){
        for(Dado d: getDados().getDados()){
            if (d instanceof DadoCliente){
                clientes.add((DadoCliente)d);
            }
            else if (d instanceof DadoVendedor){
                DadoVendedor v = (DadoVendedor)d;
                vendedores.add(v);
                performanceVendedores.putIfAbsent(v.getNome(), new Dinheiro(0.0));
            }
            else if (d instanceof DadoVenda){
                DadoVenda venda = ((DadoVenda)d);
                if (vendaMaisCara == null || venda.getPrecoTotal().compareTo(vendaMaisCara.getPrecoTotal()) > 0){
                    vendaMaisCara = venda;
                }
                Dinheiro valorAtual = performanceVendedores.get(venda.getNomeVendedor());
                Dinheiro novoValor = valorAtual == null?
                                     venda.getPrecoTotal():
                                     new Dinheiro(valorAtual.add(venda.getPrecoTotal()));
                performanceVendedores.put(venda.getNomeVendedor(), novoValor);
            }
        }
        
        for (DadoVendedor d: vendedores){
            if (piorVendedor == null || performanceVendedores.get(d.getNome()).compareTo(performanceVendedores.get(piorVendedor.getNome())) < 0){
                piorVendedor = d;
            }
        }
    }

    @Override
    public DadosImpl getDados() {
        return (DadosImpl)super.getDados();
    }

    public Set<DadoCliente> getClientes() {
        return clientes;
    }

    public Set<DadoVendedor> getVendedores() {
        return vendedores;
    }

    public DadoVenda getVendaMaisCara() {
        return vendaMaisCara;
    }

    public DadoVendedor getPiorVendedor() {
        return piorVendedor;
    }

    @Override
    public List<String> getAsLines() {
        List<String> retorno = new ArrayList<>();
        retorno.add("Quantidade clientes: "+clientes.size());
        retorno.add("Quantidade vendedores: "+vendedores.size());
        if (vendaMaisCara != null){
            retorno.add("Id venda mais cara: "+vendaMaisCara.getId());
        }
        if (piorVendedor != null){
            retorno.add("Pior Vendedor (Nome, CPF): "+piorVendedor.getNome()+", "+piorVendedor.getCpf().getComMascara());
        }
        return retorno;
    }
}
