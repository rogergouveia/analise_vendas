/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ntconsult.analise_vendas.sistema_arquivos.dado;

import br.com.ntconsult.analise_vendas.exception.AVSistemaArquivosException;
import java.util.Objects;

/**
 *
 * @author roger.gouveia
 */
public class DadoVendedor extends DadoImpl{
    private String codigo;
    private CPF cpf;
    private String nome;
    private Dinheiro salario;

    public DadoVendedor(String dado) throws AVSistemaArquivosException {
        super(dado);
        inicializarDados();
    }
    
    private void inicializarDados() throws AVSistemaArquivosException{
        if (getDados().size() != 4){
            throw new AVSistemaArquivosException("Falha de integridade dos dados: Dado de vendedor fora do formato");
        }
        
        codigo = getDados().get(0);
        cpf = new CPF(getDados().get(1));
        nome = getDados().get(2);
        salario = new Dinheiro(getDados().get(3));
    }

    public String getCodigo() {
        return codigo;
    }

    public CPF getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Dinheiro getSalario() {
        return new Dinheiro(salario.doubleValue());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.codigo);
        hash = 19 * hash + Objects.hashCode(this.cpf);
        hash = 19 * hash + Objects.hashCode(this.nome);
        hash = 19 * hash + Objects.hashCode(this.salario);
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
        final DadoVendedor other = (DadoVendedor) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.salario, other.salario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DadoVendedor{" + "codigo=" + codigo + ", cpf=" + cpf + ", nome=" + nome + ", salario=" + salario + '}';
    }
    
    
}
