/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author witally
 */
public class Aluno {

    private Scanner ler = new Scanner(System.in);
    private int codigoAluno;
    private String nomeAluno;
    private String cpf;

    public Aluno() {
        codigoAluno = 0;
        nomeAluno = "";
        cpf = "";
    }

    public Aluno(int codigoAluno, String nomeAluno, String cpf) {
        this.codigoAluno = codigoAluno;
        this.nomeAluno = cpf;
        this.cpf = cpf;

    }

    public void preencher(int codAluno) {
        System.out.println("Informacoes do Aluno:");
        System.out.println("Informe o nome: ");
        setNomeAluno(getLer().next());
        System.out.println("Informe o cpf:");
        setCpf(getLer().next());
        this.setCodigoAluno(codAluno);

    }

    public void preencher(String nome, String cpf, int codigo) {
        nomeAluno = nome;
        this.cpf = cpf;
        codigoAluno = codigo;
    }

    public void gravarBancoAluno() {
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) Persistencia.conexao().prepareStatement("insert into aluno(nome, cpf) values (?,?)");
            ps.setString(1, nomeAluno);
            ps.setString(2, cpf);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void inserirAluno() {
        System.out.println("Digite o nome do aluno: ");
        setNomeAluno(getLer().next());
        System.out.println("Digite o CPF: ");
        setCpf(getLer().next());
    }

    public void imprimir() {
        System.out.println("----------------------------");
        System.out.println("Nome do Aluno: " + getNomeAluno());
        System.out.println("Cpf: " + getCpf());

    }

    /**
     * @return the ler
     */
    public Scanner getLer() {
        return ler;
    }

    /**
     * @param ler the ler to set
     */
    public void setLer(Scanner ler) {
        this.ler = ler;
    }

    /**
     * @return the codigoAluno
     */
    public int getCodigoAluno() {
        return codigoAluno;
    }

    /**
     * @param codigoAluno the codigoAluno to set
     */
    public void setCodigoAluno(int codigoAluno) {
        this.codigoAluno = codigoAluno;
    }

    /**
     * @return the nomeAluno
     */
    public String getNomeAluno() {
        return nomeAluno;
    }

    /**
     * @param nomeAluno the nomeAluno to set
     */
    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the ler
     */
}
