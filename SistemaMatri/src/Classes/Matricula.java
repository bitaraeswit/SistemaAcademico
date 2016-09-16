/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

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

    public void gravarBancoMatricula() {
        PreparedStatement ps = null;
        try {
            ps = Persistencia.conexao().prepareStatement("insert into matricula(codigoAluno, codigoCurso, dataMatricula) values (?,?,?)");
            ps.setInt(1, codigoAluno);
            ps.setInt(2, codigoCurso);
            ps.setString(3, dataMatricula);
            ps.executeUpdate();
//new JfrmCadastro().setVisible(true);
//this.dispose();

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
