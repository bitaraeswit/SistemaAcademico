/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author witally
 */
public class Universidade {

    private ArrayList<Aluno> aluno;
    private ArrayList<Curso> curso;
    private ArrayList<Matricula> matricula;
    private int codigo;
    private int cdAluno;
    private int cdCurso;
    private int cdMatricula;

    public Universidade() {
        this.codigo = 0;
        this.aluno = new ArrayList();
        this.curso = new ArrayList();
        this.matricula = new ArrayList();
        this.cdAluno = 0;
        this.cdCurso = 0;
        this.cdMatricula = 0;
    }

    public Universidade(int c) {
        this.codigo = c;
    }

    public void cadastraCurso() {
        Curso aux = new Curso();
        aux.preencher(cdCurso++);
        getCurso().add(aux);

    }

    public void cadastraAluno() {
        Aluno aux = new Aluno();
        aux.preencher(cdAluno++);
        getAluno().add(aux);
    }

    public void cadastraMatricula() {
        Scanner ler = new Scanner(System.in);
        Matricula aux = new Matricula();
        for (Curso c : getCurso()) { //percorre para mostrar todos os cursos
            System.out.println(c.getCodigo() + "  " + c.getNomeCurso());//imprime os cursos

        }
        System.out.println("Digite ID do curso: ");
        int cur = ler.nextInt();
        for (Aluno a : getAluno()) { //percorre para mostrar todos os Alunos
            System.out.println(a.getCodigoAluno() + "  " + a.getNomeAluno());// imprime os alunos

        }
        for (Curso c : getCurso()) { //percorre curso para verificar se existe
            if (c.getCodigo() == cur) {
                for (Aluno al : getAluno()) {
                    System.out.println(al.getCodigoAluno() + "  " + al.getNomeAluno());//imprime os aluno

                }
                System.out.println("Digite ID do aluno");
                int id = ler.nextInt();

                for (Aluno a : getAluno()) { //percorre para procurar o aluno
                    if (a.getCodigoAluno() == id) {
                        aux.preencher(cdMatricula++, id, cur);
                        getMatricula().add(aux);

                    }
                }
            }

        }

    }

    public void arquMatricula() {
        for (Aluno al : getAluno()) {
            FileManager.escreve("teste.txt", al.getNomeAluno() + "-" + al.getCpf() + "-", true);

        }
        for (Curso c : getCurso()) {
            FileManager.escreve("teste.txt", c.getNomeCurso() + "-" + c.getCargaHoraria() + "-" + c.getCodigoCurso() + "-", true);

        }
        for (Matricula m : getMatricula()) {
            FileManager.escreve("teste.txt", m.getNumeroMatricula() + "-" + m.getDataMatricula() + "-", true);

        }
    }

    public void ler() {
        FileManager.lerArquivo("teste.txt");
    }

    public String getNameAluno(int id) {
        String name = "";
        for (Aluno a : aluno) {
            if (a.getCodigoAluno() == id) {
                name = a.getNomeAluno();
            }
        }
        return name;
    }

    public String getNameCurso(int id) {
        String name = "";
        for (Curso c : curso) {
            if (c.getCodigo() == id) {
                name = c.getNomeCurso();
            }
        }
        return name;
    }

    public void imprimir() {
        int cda = 0, cdc = 0, cdm = 0;
        for (Matricula m : matricula) {
            cda = m.getCodigoAluno();
            cdc = m.getCodigoCurso();
            System.out.println("Nome: " + this.getNameAluno(cda));
            System.out.println("Curso: " + this.getNameCurso(cdc));
            System.out.println("Matricula: " + cdm);
            cdm++;
        }

    }

    public void menuEscolha() {
        Scanner ler = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("\t======================");
            System.out.println("\t1 - Cadastrar curso");
            System.out.println("\t2 - Cadastrar Aluno");
            System.out.println("\t3 - Matricular");
            System.out.println("\t4 - Imprimir");
            System.out.println("\t5-  Salvar   ");
            System.out.println("\t6-  ler Arquivo");
            System.out.println("\t0 - Sair");
            System.out.println("\t=======================\n");
            System.out.println("Digite uma opção: ");

            op = ler.nextInt();

            switch (op) {

                case 1:
                    cadastraCurso();
                    break;
                case 2:
                    cadastraAluno();
                    break;

                case 3:
                    System.out.println("-------------------");
                    cadastraMatricula();
                    break;

                case 0:
                    System.out.println("Voce saiu!!");
                    break;
                case 5:
                    arquMatricula();
                    System.out.println("Arquivo salvo:\n");
                    break;
                case 6:
                    System.out.println("Arquivo:");
                    ler();

                    break;
                case 4:
                    imprimir();
                    break;

                default:
                    System.out.println("Essa opção não existe");
            }

        } while (op != 0);

    }

    public void carregarAlunoBD() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = Persistencia.conexao().prepareStatement("select * from aluno");
            rs = ps.executeQuery();
            while (rs.next()) {
                Aluno aux = new Aluno();
                aux.preencher(rs.getString("nomeAluno"), rs.getString("cpf"), rs.getInt("codigoAluno"));
                aluno.add(aux);

            }
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
            while (rs.next()) {
                Curso aux = new Curso();
                aux.preencher(rs.getString("nomeCurso"), rs.getDouble("cargaHoraria"), rs.getInt("codigoCurso"));
                curso.add(aux);

            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public void carregarMatriculaBD() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = Persistencia.conexao().prepareStatement("select * from matricula");
            rs = ps.executeQuery();
            while (rs.next()) {
                Matricula mat = new Matricula();
                mat.preencher(rs.getInt("codigoMatricula"), rs.getInt("codigoAluno"), rs.getInt("codigoCurso"));
                matricula.add(mat);

            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public ArrayList<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(ArrayList<Aluno> aluno) {
        this.aluno = aluno;
    }

    public ArrayList<Curso> getCurso() {
        return curso;
    }

    public void setCurso(ArrayList<Curso> curso) {
        this.curso = curso;
    }

    public ArrayList<Matricula> getMatricula() {
        return matricula;
    }

    public void setMatricula(ArrayList<Matricula> matricula) {
        this.matricula = matricula;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
