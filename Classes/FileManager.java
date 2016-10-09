/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author witally
 */
public class FileManager {

    static File arquivo;
    static FileWriter fw;
    static FileReader fl;

    public static void variasLinhas(String caminho, boolean append, ArrayList<Matricula> matricula) {
        int i = 0;
        while (i < matricula.size()) {
            escreve(caminho, "", append);
        }
    }

    public static void escreve(String caminho, String texto, boolean append) {
        arquivo = new File(caminho);
        if (arquivo.isFile()) {
            try {
                fw = new FileWriter(arquivo, append);
                fw.write(texto);
                fw.flush();
                fw.close();

            } catch (Exception e) {

            }
        } else {
            try {
                fw = new FileWriter(arquivo);
                fw.write(texto);
                fw.flush();
                fw.close();

            } catch (Exception e) {

            }

        }
    }

    public static void lerArquivo(String caminho) {
        String[] vetor = new String[50];
        try {
            fl = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fl);
            String linha = bufferedReader.readLine();

            vetor = linha.split("-");
            for (String mat : vetor) {
                System.out.println(mat);
            }
            fl.close();
        } catch (Exception e) {
            System.out.println("Erro" + e);

        }

    }

}
