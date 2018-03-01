/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas;

import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;
import br.com.ntconsult.analise_vendas.relatorio.RelatorioVenda;
import br.com.ntconsult.analise_vendas.sistema_arquivos.EscritorImpl;
import br.com.ntconsult.analise_vendas.sistema_arquivos.LeitorArquivosVendas;
import br.com.ntconsult.analise_vendas.sistema_arquivos.SistemaArquivosImpl;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.Dados;
import br.com.ntconsult.analise_vendas.sistema_arquivos.dado.DadosImpl;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Classe principal. Ela dispara uma Thread por intervalo para processar
 * os arquivos e gerar os relatórios
 * 
 * @author roger.gouveia
 */
public class Processador implements Runnable{

    private static final int NUM_SEG_INTERVALO_THREAD = 5;
    
    public static void main(String[] args) {

        System.out.println("Inicializando execução do Processador de Vendas");
        System.out.println("Intervalo de processamento: "+NUM_SEG_INTERVALO_THREAD+" segundos");
        Processador p = new Processador();
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(p, 1, NUM_SEG_INTERVALO_THREAD, TimeUnit.SECONDS);
        executor.submit(p);
//        
//        p.run();
    }

    
    @Override
    public void run(){
        try {
            SistemaArquivosImpl s = new SistemaArquivosImpl();
            LeitorArquivosVendas leitor = new LeitorArquivosVendas();
            EscritorImpl escritor = new EscritorImpl();
            List<Dados> dados = leitor.lerArquivosEntrada(s);
            for (Dados d:dados){
                DadosImpl toWrite = (DadosImpl)d;
                RelatorioVenda relatorio = new RelatorioVenda(s.getDiretorioSaida(), toWrite.getNomeArquivo(), toWrite);
                escritor.gerarRelatorio(s, relatorio);
            }
        } catch (AVSistemaArquivosException ex) {
            Logger.getLogger(Processador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
