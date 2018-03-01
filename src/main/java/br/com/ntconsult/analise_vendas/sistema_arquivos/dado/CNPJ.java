/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos.dado;

import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;

/**
 *
 * @author roger.gouveia
 */
public class CNPJ implements CadastroPessoa{
    private final Integer tamanhoCNPJ = 14;
    private final Long cnpj;
    
    public CNPJ(String cnpj) throws AVSistemaArquivosException {
        this.cnpj = cnpj == null?
                    null:
                    Long.valueOf(cnpj.replaceAll("\\D", ""));
        validarCNPJ();
    }
    
    public CNPJ(Long cnpj) throws AVSistemaArquivosException {
        this.cnpj = cnpj;
        validarCNPJ();
    }

    private void validarCNPJ() throws AVSistemaArquivosException{
        if (cnpj == null){
            throw new AVSistemaArquivosException("Falha na integridade de dados: CNPJ Vazio");
        }
        
        if (cnpj.toString().length() > tamanhoCNPJ){
            throw new AVSistemaArquivosException("Falha na integridade de dados: CNPJ possui mais de "+tamanhoCNPJ+" números");
        }
            
    }
    
    @Override
    public String getComMascara() {
        String cpfString = getSemMascara();
        StringBuilder sb = new StringBuilder();
        sb.append(cpfString.substring(0,3))
          .append(".")
          .append(cpfString.substring(3, 6))
          .append(".")
          .append(cpfString.substring(6, 9))
          .append("-")
          .append(cpfString.substring(9, 11))
        ;
        return sb.toString();
    }

    @Override
    public String getSemMascara() {
        String pattern = "%0"+tamanhoCNPJ+"d";
        return String.format(pattern, cnpj);
    }

    @Override
    public Long getComoInteiro() {
        return cnpj;
    }
    
}
