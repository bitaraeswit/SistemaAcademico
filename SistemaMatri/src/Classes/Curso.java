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
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
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
