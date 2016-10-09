/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.mysql.jdbc.PreparedStatement;
import com.sun.istack.internal.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;
import sun.util.logging.PlatformLogger;

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
            ps = (PreparedStatement) Persistencia.conexao().prepareStatement("insert into aluno(nomeAluno, cpf) values (?,?)");
            ps.setString(1, nomeAluno);
            ps.setString(2, cpf);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void carregarAlunoBD() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = (PreparedStatement) Persistencia.conexao().prepareStatement("select * from aluno");
            rs = ps.executeQuery();
            DefaultTableModel dtm = new DefaultTableModel(new String[]{"codigoAluno", "nomeAluno", "cpf"}, 0);
            while (rs.next()) {
                String aux = Integer.toString(rs.getInt("codigoAluno"));

                String dados[] = {aux, rs.getString("nomeAluno"), rs.getString("cpf")};
                dtm.addRow(dados);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public void Buscar() {
        java.sql.PreparedStatement ps = null;

        try {
            String name = "";
            System.out.println("Digite o nome a ser buscado:");
            name = ler.next();
            ps = Persistencia.conexao().prepareStatement("SELECT * FROM aluno Where nomeAluno =?");
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            System.out.println("-------------------------------------------");
            System.out.println("\t|codigo\t\t |Nome \t\t |cpf\t\t ");

            while (rs.next()) {
                int codigo = rs.getInt("codigoAluno");
                String nome = rs.getString("nomeAluno");
                int cpfAl = rs.getInt("cpf");

                System.out.println("\t|" + codigo + "\t\t -" + nome + "\t\t -" + cpfAl + "\t\t");
            }
            System.out.println("---------------------------------------------");
            rs.close();

        } catch (SQLException e) {
            System.out.println("ERRO" + e);

        }
    }

    public void mostraAlunoCadastrado() {
        java.sql.PreparedStatement ps = null;

        try {
            ps = Persistencia.conexao().prepareStatement("SELECT * FROM aluno");
            ResultSet rs = ps.executeQuery();
            System.out.println("-----------------------------------");
            System.out.println("-Codigo\t\t\t -Nome \t\t\t\t - cpf\t\t\t");
            while (rs.next()) {
                int cod = rs.getInt("codigoAluno");
                String nome = rs.getString("nomeAluno");
                String cpf = rs.getString("cpf");
                System.out.println("-" + cod + "\t\t\t -" + nome + "\t\t\t\t -" + cpf + "\t\t\t");
            }
            System.out.println("------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro" + e);

        }
    }

    public void alterAlunoBD() {
        if (jaExisteCodigo()) {
            mostraAlunoCadastrado();
            int cod = 0;
            System.out.println("Digite o codigo a ser alterado:");
            cod = ler.nextInt();
            java.sql.PreparedStatement ps = null;
            ResultSet rs = null;
            System.out.println("Digite o novo nome: ");
            setNomeAluno(ler.next());
            System.out.println("Digite o cpf: ");
            setCpf(ler.next());

            try {
                ps = Persistencia.conexao().prepareStatement("UPDATE  aluno set nomeAluno=?, cpf=? where codigoAluno=?");

                //rs = ps.executeQuery();
                ps.setString(1, this.nomeAluno);
                ps.setString(2, this.cpf);
                ps.setInt(3, cod);
                ps.execute();
                System.out.println("Alterado com Sucesso, Cara!!");
            } catch (SQLException e) {
                System.out.println("Erro ao alterar o aluno: " + e);

            }
        } else {
            System.out.println("Codigo nao encontrado");
        }
    }

    public boolean jaExisteCodigo() {
        try {
            java.sql.PreparedStatement ps = null;

            ps = Persistencia.conexao().prepareStatement("SELECT * FROM aluno WHERE codigoAluno=?");
            ps.setInt(1, codigoAluno);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.wasNull()) {
                return false;
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Nao foi possivel executar o comando" + e);
        }
        return true;
    }

    public void deleteAlunoBD() {
        if (jaExisteCodigo()) {
            java.sql.PreparedStatement ps = null;
            ResultSet rs = null;
            mostraAlunoCadastrado();
            try {
                int cod = 0;
                System.out.println("Digite o codigo do Aluno a ser excluido:");
                cod = ler.nextInt();
                ps = Persistencia.conexao().prepareStatement("DELETE  from aluno where codigoAluno=?");
                ps.setInt(1, cod);
                ps.executeUpdate();
                System.out.println("Aluno excluido com sucesso");
            } catch (SQLException e) {
                System.out.println("Erro.Aluno esta vinculado a matricula," + e.getMessage());

            }

        } else {
            System.out.println("Codigo nao encontrado");
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
