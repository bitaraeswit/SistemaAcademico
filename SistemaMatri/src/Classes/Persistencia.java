/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author witally
 */
public class Persistencia {

    private static Connection con = null;

    private Persistencia() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/inscricoes", "root", "17junho");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: " + e);

        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }
    }

    public static Connection conexao() {
        if (con == null) {
            new Persistencia();
        }
        return con;
    }

}
