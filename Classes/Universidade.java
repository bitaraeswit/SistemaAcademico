/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import View.JfrmBuscarAluno;
import View.JfrmBuscarCurso;
import View.JfrmBuscarMatricula;
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
    private JfrmBuscarAluno jfrma = new JfrmBuscarAluno();
    private JfrmBuscarCurso jfrmc = new JfrmBuscarCurso();
    private JfrmBuscarMatricula jfrmm = new JfrmBuscarMatricula();

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
        aux.gravarBancoCurso();
    }

    public void menuBusca() {
        Aluno al = new Aluno();
        Curso cur = new Curso();
        Matricula mat = new Matricula();
        Scanner ler = new Scanner(System.in);
        int op = 99;
        do {
            System.out.println("\t\t===================");
            System.out.println("\t\t1 - Aluno");
            System.out.println("\t\t2 - Curso");
            System.out.println("\t\t3 - Matricula");
            System.out.println("\t\t99- Sair");
            System.out.println("\t\t===================");
            System.out.println("\t Digite uma opção: ");
            op = ler.nextInt();

            switch (op) {
                case 1:
                    al.Buscar();
                    break;
                case 2:
                    cur.Buscar();
                    break;
                case 3:
                    mat.Buscar();
                    break;
                case 99:
                    System.out.println("Saiu do menu Busca!!");
                    break;
                default:
                    System.out.println("Opção inválida;Digite novamente:");
                    op = ler.nextInt();
                    break;

            }

        } while (op != 99);
    }

    public void menuAlterar() {
        Aluno al = new Aluno();
        Curso cur = new Curso();
        Matricula mat = new Matricula();
        Scanner ler = new Scanner(System.in);
        int op = 99;
        do {
            System.out.println("\t\t===================");
            System.out.println("\t\t1 - Aluno");
            System.out.println("\t\t2 - Curso");
            System.out.println("\t\t3 - Matricula");
            System.out.println("\t\t99- Sair");
            System.out.println("\t\t===================");
            System.out.println("\t Digite uma opção: ");
            op = ler.nextInt();

            switch (op) {
                case 1:
                    al.alterAlunoBD();
                    break;
                case 2:
                    cur.alterCursoBD();
                    break;
                case 3:
                    mat.alterMatriculaBD();
                    break;
                case 99:
                    System.out.println("Saiu do menu Alterar!!");
                    break;
                default:
                    System.out.println("Opção inválida;Digite novamente:");
                    op = ler.nextInt();
                    break;

            }

        } while (op != 99);
    }

    public void menuExcluir() {
        Aluno al = new Aluno();
        Curso cur = new Curso();
        Matricula mat = new Matricula();
        Scanner ler = new Scanner(System.in);
        int op = 99;
        do {
            System.out.println("\t\t===================");
            System.out.println("\t\t1 - Aluno");
            System.out.println("\t\t2 - Curso");
            System.out.println("\t\t3 - Matricula");
            System.out.println("\t\t99- Sair");
            System.out.println("\t\t===================");
            System.out.println("\t Digite uma opção: ");
            op = ler.nextInt();

            switch (op) {
                case 1:
                    al.deleteAlunoBD();
                    break;
                case 2:
                    cur.deleteCursoBD();
                    break;
                case 3:
                    mat.deleteMatriculaBD();
                    break;
                case 99:
                    System.out.println("Saiu do menu Excluir!!");
                    break;
                default:
                    System.out.println("Opção inválida;Digite novamente:");
                    op = ler.nextInt();
                    break;

            }

        } while (op != 99);
    }

    public void menuInterface() {

        Scanner ler = new Scanner(System.in);
        int op = 99;
        do {
            System.out.println("\t\t===================");
            System.out.println("\t\t1 - Aluno");
            System.out.println("\t\t2 - Curso");
            System.out.println("\t\t3 - Matricula");
            System.out.println("\t\t99- Sair");
            System.out.println("\t\t===================");
            System.out.println("\t Digite uma opção: ");
            op = ler.nextInt();

            switch (op) {
                case 1:
                    jfrma.setVisible(true);
                    jfrma.mostraAlunoCadastrado();
                    break;
                case 2:
                    jfrmc.setVisible(true);
                    jfrmc.mostraCursoCadastrado();
                    break;
                case 3:
                    jfrmm.setVisible(true);
                    jfrmm.mostraMatriuclaCadastrado();
                    break;

                case 99:
                    System.out.println("Saiu do menu Interface!!");
                    break;
                default:
                    System.out.println("Opção inválida;Digite novamente:");
                    op = ler.nextInt();
                    break;

            }

        } while (op != 99);
    }

    public void cadastraAluno() {
        Aluno aux = new Aluno();
        aux.preencher(cdAluno++);
        getAluno().add(aux);
        aux.gravarBancoAluno();
    }

    public void imprimeBD() {
        Aluno al = new Aluno();
        Curso cur = new Curso();
        Matricula mat = new Matricula();
        al.mostraAlunoCadastrado();
        cur.mostraCursoCadastrado();
        mat.mostraMatriculaCadastrado();

    }

    public void cadastraMatricula() {
        Scanner ler = new Scanner(System.in);
        Matricula aux = new Matricula();
        Curso c = new Curso();
        c.mostraCursoCadastrado();
        System.out.println("Digite ID do curso: ");
        int cur = ler.nextInt();
        Aluno a = new Aluno();
        a.mostraAlunoCadastrado();
        System.out.println("Digite ID do aluno");
        int id = ler.nextInt();

        aux.preencher(cdMatricula, id, cur);
        getMatricula().add(aux);

        aux.gravarBancoMatricula();
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
        Aluno al = new Aluno();
        Matricula mat = new Matricula();
        Curso cur = new Curso();

        int op = 0;
        do {
            System.out.println("\t======================");
            System.out.println("\t1 - Cadastrar Curso");
            System.out.println("\t2 - Cadastrar Aluno");
            System.out.println("\t3 - Matricular");
            System.out.println("\t4 - Buscar no Banco");
            System.out.println("\t5 - Alterar no Banco");
            System.out.println("\t6 - Excluir no Banco");
            System.out.println("\t7 - Imprimir Banco");
            System.out.println("\t8 - Salvar no Arquivo");
            System.out.println("\t9 - Ler Arquivo Salvo");
            System.out.println("\t10 - Interface");
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

                case 4:
                    menuBusca();
                    break;
                case 5:
                    menuAlterar();
                    break;
                case 6:
                    menuExcluir();
                    break;

                case 7:
                    imprimeBD();
                    break;
                case 8:
                    arquMatricula();
                    System.out.println("Arquivo salvo:\n");

                    break;

                case 9:
                    System.out.println("Arquivo:");
                    ler();
                    break;
                case 10:
                    menuInterface();
                    break;
                default:
                    System.out.println("Essa opção não existe");
            }

        } while (op != 0);

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
