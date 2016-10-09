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
public class Curso {

    private Scanner ler = new Scanner(System.in);
    private int codigoCurso;
    private String nomeCurso;
    private double cargaHoraria;

    public Curso() {
        codigoCurso = 0;
        nomeCurso = "";
        cargaHoraria = 0;

    }

    public Curso(int codigoCurso, String nomeCurso, double cargaHoraria) {
        this.codigoCurso = codigoCurso;
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;

    }

    public void matricularCurso() {
        System.out.println("Digite o nome do curso: ");
        nomeCurso = ler.next();
    }

    public void preencher(int codigoCurso) {
        System.out.println("Digite o nome do curso: ");
        nomeCurso = ler.next();
        System.out.println("Digite a carga horaria do curso: ");
        cargaHoraria = ler.nextDouble();
        this.codigoCurso = codigoCurso;
    }

    public void gravarBancoCurso() {
        PreparedStatement ps = null;
        try {
            ps = Persistencia.conexao().prepareStatement("insert into curso(nomeCurso, cargaHoraria) values (?,?)");
            ps.setString(1, nomeCurso);
            ps.setDouble(2, cargaHoraria);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void carregarCursoBD() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = Persistencia.conexao().prepareStatement("select * from curso");
            rs = ps.executeQuery();
            DefaultTableModel dtm = new DefaultTableModel(new String[]{"codigoCurso", "nomeCurso", "cargaHoraria"}, 0);
            while (rs.next()) {
                String aux = Integer.toString(rs.getInt("codigoCurso"));
                String dados[] = {aux, rs.getString("nomeCurso"), rs.getString("cargaHoraria")};
                dtm.addRow(dados);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public void alterCursoBD() {
        if (jaExisteCodigo()) {
            java.sql.PreparedStatement ps = null;
            ResultSet rs = null;
            mostraCursoCadastrado();
            int cod = 0;
            System.out.println("Didite o codigo a ser alterado: ");
            cod = ler.nextInt();
            System.out.println("Digite o novo nome: ");
            setNomeCurso(ler.next());
            System.out.println("Digite a carga Horaria: ");
            setCargaHoraria(ler.nextInt());

            try {
                ps = Persistencia.conexao().prepareStatement("UPDATE  curso set nomeCurso=?, cargaHoraria=? where codigoCurso=?");

                //rs = ps.executeQuery();
                ps.setString(1, this.nomeCurso);
                ps.setDouble(2, this.cargaHoraria);
                ps.setInt(3, cod);
                ps.execute();
                System.out.println("Alterado com Sucesso, Cara!!");
            } catch (SQLException e) {
                System.out.println("Erro ao alterar o Curso: " + e);

            }
        } else {
            System.out.println("Curso nao encontrado!");
        }
    }

    public void mostraCursoCadastrado() {
        java.sql.PreparedStatement ps = null;

        try {
            ps = Persistencia.conexao().prepareStatement("SELECT * FROM curso");
            ResultSet rs = ps.executeQuery();
            System.out.println("-----------------------------------");
            System.out.println("-Codigo\t -Nome \t\t\t\t - Horas\t\t\t");
            while (rs.next()) {
                int cod = rs.getInt("codigoCurso");
                String nome = rs.getString("nomeCurso");
                String hora = rs.getString("cargaHoraria");
                System.out.println("-" + cod + "\t -" + nome + "\t\t\t\t -" + hora + "\t\t\t");
            }
            System.out.println("------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro" + e);

        }
    }

    public void Buscar() {
        java.sql.PreparedStatement ps = null;

        try {
            String name = "";
            System.out.println("Digite o nome a ser buscado:");
            name = ler.next();
            ps = Persistencia.conexao().prepareStatement("SELECT * FROM curso Where nomeCurso =?");
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            System.out.println("-------------------------------------------");
            System.out.println("|codigo\t |Nome \t\t\t ");
            while (rs.next()) {
                int codigo = rs.getInt("codigoCurso");
                String nome = rs.getString("nomeCurso");

                System.out.println("-" + codigo + "\t -" + nome + "\t\t\t ");
            }
            System.out.println("---------------------------------------------");
            rs.close();

        } catch (SQLException e) {
            System.out.println("ERRO" + e);

        }
    }

    public boolean jaExisteCodigo() {
        try {
            java.sql.PreparedStatement ps = null;

            ps = Persistencia.conexao().prepareStatement("SELECT * FROM curso WHERE codigoCurso=?");
            ps.setInt(1, codigoCurso);
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

    public void deleteCursoBD() {
        if (jaExisteCodigo()) {
            java.sql.PreparedStatement ps = null;
            ResultSet rs = null;
            mostraCursoCadastrado();
            try {
                int cod = 0;
                System.out.println("Digite o codigo a ser buscado: ");
                cod = ler.nextInt();
                ps = Persistencia.conexao().prepareStatement("DELETE  from curso where codigoCurso=?");
                ps.setInt(1, cod);
                ps.executeUpdate();
                System.out.println("Curso excluido com sucesso");
            } catch (SQLException ex) {
                System.out.println("Erro,Curso vinculado a matricula!!! " + ex.getMessage());

            }

        } else {
            System.out.println("Codigo nao encontrado");
        }
    }

    public void preencher(String nomeCurso, double cargaHoraria, int codigoCurso) {
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
        this.codigoCurso = codigoCurso;
    }

    public void imprimir() {
        System.out.println("Curso: " + nomeCurso);

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

    /**
     * @return the nomeCurso
     */
    public String getNomeCurso() {
        return nomeCurso;
    }

    /**
     * @param nomeCurso the nomeCurso to set
     */
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    /**
     * @return the cargaHoraria
     */
    public double getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @param cargaHoraria the cargaHoraria to set
     */
    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
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

    public int getCodigo() {
        return codigoCurso;
    }

}
