/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos.dado;

import br.com.ntconsult.analise_vendas.exception.AVRuntimeException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author roger.gouveia
 */
public class Dinheiro extends BigDecimal{

    private static final long serialVersionUID = 1L;
    private final String formatoEmReais = "R$ #.###,00";
    
    public Dinheiro(String val) throws AVRuntimeException {
        super(val);
        setScale(2, BigDecimal.ROUND_HALF_EVEN);
        inicializarDinheiro();
    }
    
    public Dinheiro(double val) throws AVRuntimeException {
        super(val);
        setScale(2, BigDecimal.ROUND_HALF_EVEN);
        inicializarDinheiro();
    }
    
    public Dinheiro(BigDecimal val) throws AVRuntimeException {
        this(val.doubleValue());
    }
    
    private void inicializarDinheiro() throws AVRuntimeException{
//        if (scale() > 2){
//            throw new AVRuntimeException("Dinheiro com mais de dois decimais");
//        }
        setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    public String getFormatadoEmReais(){
        DecimalFormat df = new DecimalFormat(formatoEmReais);
        return df.format(this);
    }

    
}
