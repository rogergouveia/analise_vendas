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
public class CPF implements CadastroPessoa{
    private final Integer tamanhoCPF = 11;
    private final Long cpf;
    
    public CPF(String cpf) throws AVSistemaArquivosException {
        this.cpf = cpf == null?
                   null:
                   Long.valueOf(cpf.replaceAll("\\D", ""));
        validarCpf();
    }
    
    public CPF(Long cpf) throws AVSistemaArquivosException {
        this.cpf = cpf;
        validarCpf();
    }

    private void validarCpf() throws AVSistemaArquivosException{
        if (cpf == null){
            throw new AVSistemaArquivosException("Falha na integridade de dados: CPF Vazio");
        }
        
        if (cpf.toString().length() > tamanhoCPF){
            throw new AVSistemaArquivosException("Falha na integridade de dados: CPF possui mais de "+tamanhoCPF+" números");
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
        String pattern = "%0"+tamanhoCPF+"d";
        return String.format(pattern, cpf);
    }

    @Override
    public Long getComoInteiro() {
        return cpf;
    }
    
}
