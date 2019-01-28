package fearchannel.projects.doncamatic.op;

import fearchannel.projects.doncamatic.main.ConexaoSQLite;
import fearchannel.projects.doncamatic.main.CriarBancoSQLite;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

class ConsultaBanco {
    private Random geradorAleatorio;
    private ConexaoSQLite conexao;
    private PreparedStatement preparedStatement;

    ConsultaBanco() {
        geradorAleatorio = new Random();
        conexao = new ConexaoSQLite();
        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexao);
        criarBancoSQLite.criarTabela();
    }

    void inserirTermo(String termo) {
        String sqlInsert = "INSERT INTO random_words (" +
                "palavra" +
                ") VALUES(?)";
        conexao.conectar();
        preparedStatement = conexao.criarPreparedStatement(sqlInsert);

        try {
            preparedStatement.setString(1, termo);
            int sc = preparedStatement.executeUpdate();
            if (sc == 1) {
                JOptionPane.showConfirmDialog(null, "Termo inserido: " + termo, "Doncamatic", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conexao.desconectar();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    String buscarTermo() {

        String query = "SELECT palavra FROM random_words WHERE id = ?";
        conexao.conectar();
        preparedStatement = conexao.criarPreparedStatement(query);

        try {
            preparedStatement.setInt(1, geradorAleatorio.nextInt(limiteRandom()));
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getString("palavra");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conexao.desconectar();
            } catch (SQLException ex) {
                System.out.println("erro fechamento");
            }
        }
        return "";
    }

    private int limiteRandom() {
        conexao.conectar();
        String queryLimit = "SELECT MAX(id) FROM random_words";
        Statement statement = conexao.criarStatement();

        try {
            ResultSet resultSet1 = statement.executeQuery(queryLimit);
            return resultSet1.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conexao.desconectar();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -10;
    }
}
