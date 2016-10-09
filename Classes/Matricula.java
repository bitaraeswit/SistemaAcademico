/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.sun.istack.internal.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author witally
 */
public class Matricula {

    private Scanner ler = new Scanner(System.in);
    private int numeroMatricula = 0;
    private String dataMatricula;
    private int codigoAluno;
    private int codigoCurso;

    public Matricula() {
        numeroMatricula = 0;
        dataMatricula = null;
        codigoAluno = 0;
        codigoCurso = 0;

    }

    public Matricula(int numeroMatricula, String dataMatricula) {
        this.numeroMatricula = numeroMatricula;
        this.dataMatricula = dataMatricula;
        this.codigoAluno = codigoAluno;
        this.codigoCurso = codigoCurso;

    }

    public void preencher(int codigoMatricula, int codigoAluno, int codigoCurso) {
        this.codigoAluno = codigoAluno;
        this.codigoCurso = codigoCurso;
        System.out.println("Digite a data:");
        dataMatricula = ler.next();

    }

    public void addData() {
        System.out.println("Digite a data da Matricula: ");
        dataMatricula = ler.next();
    }

    public void imprimir() {
        System.out.println("O numero da matricula: " + numeroMatricula);

    }

    public void alterMatriculaBD() {
        if (jaExisteNumero()) {
            java.sql.PreparedStatement ps = null;

            mostraMatriculaCadastrado();
            int num = 0;
            System.out.println("Digite o numero a ser alterado:");
            num = ler.nextInt();
            System.out.println("Digite a nova data: ");
            setDataMatricula(ler.next());

            try {
                ps = Persistencia.conexao().prepareStatement("UPDATE  matricula set dataMatricula=? where numeroMatricula=?");

                //rs = ps.executeQuery();
                ps.setString(1, this.dataMatricula);
                ps.setInt(2, num);
                ps.execute();
                System.out.println("Alterado com Sucesso, Cara!!");
            } catch (SQLException e) {
                System.out.println("Erro ao alterar a Matricula: " + e);

            }
        } else {
            System.out.println("Matriucla invalida");
        }
    }

    public void gravarBancoMatricula() {
        PreparedStatement ps = null;
        try {
            ps = Persistencia.conexao().prepareStatement("insert into matricula(aluno_codigoAluno,curso_codigoCurso, dataMatricula) values (?,?,?)");
            ps.setInt(1, codigoAluno);
            ps.setInt(2, codigoCurso);
            ps.setString(3, dataMatricula);
            ps.execute();
            ps.close();
//new JfrmCadastro().setVisible(true);
//this.dispose();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void mostraMatriculaCadastrado() {
        java.sql.PreparedStatement ps = null;
        java.sql.PreparedStatement psA = null;
        java.sql.PreparedStatement psC = null;

        try {
            ps = Persistencia.conexao().prepareStatement("SELECT * FROM matricula");
            ResultSet rs = ps.executeQuery(), rs2, rs3;
            System.out.println("-----------------------------------");
            System.out.println("-Nome Aluno\t\t - Nome Curso\t\t - Numero \t\t\t Data ");
            while (rs.next()) {
                int codA = rs.getInt("aluno_codigoAluno");
                int codC = rs.getInt("curso_codigoCurso");
                int num = rs.getInt("numeroMatricula");
                String date = rs.getString("dataMatricula");

                psA = Persistencia.conexao().prepareStatement("SELECT * FROM aluno where codigoAluno=" + codA);
                rs2 = psA.executeQuery();
                rs2.next();
                psC = Persistencia.conexao().prepareStatement("SELECT * FROM curso where codigoCurso=" + codC);
                rs3 = psC.executeQuery();
                rs3.next();
                String nomeAl = rs2.getString("nomeAluno");
                String nomeCur = rs3.getString("nomeCurso");

                System.out.println("-" + nomeAl + "\t\t\t - " + nomeCur + "\t\t - " + num + "\t\t\t\t" + date);
                rs2.close();
                rs3.close();

            }
            System.out.println("------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro" + e);

        }
    }

    public void Buscar() {
        java.sql.PreparedStatement ps = null;
        java.sql.PreparedStatement psA = null;
        java.sql.PreparedStatement psC = null;

        try {
            int num = 0;
            System.out.println("Digite o numero da Matricula: ");
            num = ler.nextInt();
            ps = Persistencia.conexao().prepareStatement("SELECT * FROM matricula where numeroMatricula=" + num);
            ResultSet rs = ps.executeQuery(), rs2, rs3;

            System.out.println("-----------------------------------");
            System.out.println("-Nome Aluno\t\t - Nome Curso\t\t -Numero \t\t\t");

            rs.next();
            int codA = rs.getInt("aluno_codigoAluno");
            int codC = rs.getInt("curso_codigoCurso");
            num = rs.getInt("numeroMatricula");

            psA = Persistencia.conexao().prepareStatement("SELECT * FROM aluno where codigoAluno=" + codA);
            rs2 = psA.executeQuery();
            rs2.next();
            psC = Persistencia.conexao().prepareStatement("SELECT * FROM curso where codigoCurso=" + codC);
            rs3 = psC.executeQuery();
            rs3.next();
            String nomeAl = rs2.getString("nomeAluno");
            String nomeCur = rs3.getString("nomeCurso");

            System.out.println("-" + nomeAl + "\t\t -" + nomeCur + "\t\t -" + num + "\t\t\t");
            rs2.close();
            rs3.close();

            System.out.println("---------------------------------------------");
            rs.close();

        } catch (SQLException e) {
            System.out.println("ERRO" + e);

        }
    }

    public boolean jaExisteNumero() {
        try {
            java.sql.PreparedStatement ps = null;

            ps = Persistencia.conexao().prepareStatement("SELECT * FROM matricula WHERE numeroMatricula=?");
            ps.setInt(1, numeroMatricula);
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

    public void deleteMatriculaBD() {
        if (jaExisteNumero()) {
            java.sql.PreparedStatement ps = null;
            ResultSet rs = null;
            mostraMatriculaCadastrado();
            int num = 0;
            System.out.println("Digite o numero da Matricula:");
            num = ler.nextInt();
            try {
                ps = Persistencia.conexao().prepareStatement("DELETE  from matricula where numeroMatricula=?");
                ps.setInt(1, num);
                ps.executeUpdate();
                System.out.println("Matricula excluida com sucesso");
            } catch (SQLException ex) {
                System.out.println("Erro" + ex.getMessage());

            }

        } else {
            System.out.println("Numero nao encontrado");
        }
    }

    public void carregarMatriculaBD() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = Persistencia.conexao().prepareStatement("select * from matricula");
            rs = ps.executeQuery();
            DefaultTableModel dtm = new DefaultTableModel(new String[]{"codigoCurso", "codigoAluno", "dataMatricula", "numeroMatricula"}, 0);
            while (rs.next()) {
                String aux1 = Integer.toString(rs.getInt("codigoCurso"));
                String aux2 = Integer.toString(rs.getInt("codigoAluno"));
                String aux3 = Integer.toString(rs.getInt("numeroMatricula"));

                String dados[] = {aux1, aux2, rs.getString("dataMatricula"), aux3};
                dtm.addRow(dados);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

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
     * @return the numeroMatricula
     */
    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    /**
     * @param numeroMatricula the numeroMatricula to set
     */
    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    /**
     * @return the dataMatricula
     */
    public String getDataMatricula() {
        return dataMatricula;
    }

    /**
     * @param dataMatricula the dataMatricula to set
     */
    public void setDataMatricula(String dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    /**
     * @return the codigo
     */
    public int getCodigoAluno() {
        return codigoAluno;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigoAluno(int codigoAluno) {
        this.codigoAluno = codigoAluno;
    }

    /**
     * @return the codigoCurso
     */
    public int getCodigoCurso() {
        return codigoCurso;
    }

    /**
     * @param codigoCurso the codigoCurso to set
     */
    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

}
