/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos;

import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.DadoCliente;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.DadoImpl;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.DadoVenda;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.DadoVendedor;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.Dados;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.DadosImpl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author roger.gouveia
 */
public class LeitorArquivosVendas implements Leitor {

    private static final long serialVersionUID = 1L;

//    @Inject
//    private SistemaArquivosImpl sistemaArquivos;

    /**
     * Lê arquivos de extensão ".dat" do diretório de entrada.
     * 
     * @param sistemaArquivos
     * @return retorna um objeto {@link Dados} para cada arquivo lido
     * @throws AVSistemaArquivosException 
     */
    @Override
    public List<Dados> lerArquivosEntrada(SistemaArquivosImpl sistemaArquivos) throws AVSistemaArquivosException {
        List<Dados> retorno = new ArrayList<>();
        Diretorio dirEntrada = sistemaArquivos.getDiretorioEntrada();
        for (File arquivo : dirEntrada.listFiles()) {
            if (arquivo.isFile() && "dat".equals(FilenameUtils.getExtension(arquivo.getAbsolutePath()))) {
                List<DadoImpl> listaDados = new ArrayList<>(lerArquivo(arquivo.toPath()));
                retorno.add(new DadosImpl(arquivo.getName(), listaDados));
            }
        }
        
        return retorno;
    }

    private List<DadoImpl> lerArquivo(Path path) throws AVSistemaArquivosException {
        List<DadoImpl> retorno = new ArrayList<>();
        retorno.addAll(lerClientes(path));
        retorno.addAll(lerVendedores(path));
        retorno.addAll(lerVendas(path));
        return retorno;
    }

    private List<DadoCliente> lerClientes(Path path) throws AVSistemaArquivosException {
        List<DadoCliente> retorno = new ArrayList<>();
        for (String l : ler(path,"002")) {
            retorno.add(new DadoCliente(l));
        }
        return retorno;
    }

    private List<DadoVendedor> lerVendedores(Path path) throws AVSistemaArquivosException {
        List<DadoVendedor> retorno = new ArrayList<>();
        for (String l : ler(path,"001")) {
            retorno.add(new DadoVendedor(l));
        }
        return retorno;
    }

    private List<DadoVenda> lerVendas(Path path) throws AVSistemaArquivosException {
        List<DadoVenda> retorno = new ArrayList<>();
        for (String l : ler(path,"003")) {
            retorno.add(new DadoVenda(l));
        }
        return retorno;
    }
    
    private List<String> ler(Path path, String codigo) throws AVSistemaArquivosException{
        List<String> vendas = new ArrayList<>();
        try {
            Stream<String> stream = Files.lines(path);
            vendas.addAll(stream.filter(line -> line != null && line.length() >= 3 && line.startsWith(codigo))
                    .collect(Collectors.toList())
            );
        } catch (IOException ex) {
            throw new AVSistemaArquivosException("Ocorreu falha inesperada ao ler arquivo de vendas");
        }

        return vendas;
    }
}
