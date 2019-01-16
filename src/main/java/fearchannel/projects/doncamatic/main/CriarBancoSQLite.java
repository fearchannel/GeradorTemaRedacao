package fearchannel.projects.doncamatic.main;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarBancoSQLite {

    private final ConexaoSQLite conexaoSQLite;

    public CriarBancoSQLite(ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }

    public void criarTabela() {

        String sql = "CREATE TABLE IF NOT EXISTS random_words" +
                "(" +
                "id integer PRIMARY KEY," +
                "palavra text NOT NULL" +
                ");";

        boolean conectou = false;

        try {
            conectou = this.conexaoSQLite.conectar();
            Statement stmt = this.conexaoSQLite.criarStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conectou) {
                this.conexaoSQLite.desconectar();
            }
        }
    }
}
