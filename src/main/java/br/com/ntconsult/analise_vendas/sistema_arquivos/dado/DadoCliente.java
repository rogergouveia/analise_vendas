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
public class DadoCliente extends DadoImpl{
    private String codigo;
    private CNPJ cnpj;
    private String nome;
    private String area;

    public DadoCliente(String dado) throws AVSistemaArquivosException {
        super(dado);
        inicializarDados();
    }
    
    private void inicializarDados() throws AVSistemaArquivosException{
        if (getDados().size() != 4){
            throw new AVSistemaArquivosException("Falha de integridade dos dados: Dado de cliente fora do formato");
        }
        codigo = getDados().get(0);
        cnpj = new CNPJ(getDados().get(1));
        nome = getDados().get(2);
        area = getDados().get(3);
    }

    public String getCodigo() {
        return codigo;
    }

    public CNPJ getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getArea() {
        return area;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.codigo);
        hash = 97 * hash + Objects.hashCode(this.cnpj);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.area);
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
        final DadoCliente other = (DadoCliente) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DadoCliente{" + "codigo=" + codigo + ", cnpj=" + cnpj + ", nome=" + nome + ", area=" + area + '}';
    }
    
}
