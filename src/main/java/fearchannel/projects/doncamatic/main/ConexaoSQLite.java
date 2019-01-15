package fearchannel.projects.doncamatic.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoSQLite {
    private Connection conexao;

    public boolean conectar() {

        try {
            Class.forName("org.sqlite.JDBC");
            String URL = "jdbc:sqlite:banco_de_dados/banco_sqlite.db";
            this.conexao = DriverManager.getConnection(URL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println("Conectou!");
        return true;
    }

    public boolean desconectar() {
        try {
            if (!conexao.isClosed()) {
                this.conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Desconectou!");
        return true;
    }

    public Statement criarStatement(){
        try{
            return this.conexao.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Connection getConexao(){
        return this.conexao;
    }
}
