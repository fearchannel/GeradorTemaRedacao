package fearchannel.projects.doncamatic.main;

import java.sql.*;

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
        return true;
    }

    public void desconectar() {
        try {
            if (!conexao.isClosed()) {
                this.conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement criarStatement(){
        try{
            return this.conexao.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public PreparedStatement criarPreparedStatement(String sql){
        try{
            return this.conexao.prepareStatement(sql);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
