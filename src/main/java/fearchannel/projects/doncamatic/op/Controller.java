package fearchannel.projects.doncamatic.op;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Consulta_Banco consulta_banco = new Consulta_Banco();

    @FXML
    protected Button btnGerar;

    @FXML
    protected Button btnSubmeter;

    @FXML
    protected TextField txtInserirTema;

    @FXML
    protected TextField txtTemaGerado;

    @FXML
    public void gerarTema(ActionEvent event) {
        txtTemaGerado.setText(consulta_banco.buscarTermo());
    }

    @FXML
    public void inserirTema(ActionEvent event) {
        String query = getTxtInserirTema().getText().trim();
        consulta_banco.inserirTermo(query);
        txtInserirTema.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    private TextField getTxtInserirTema() {
        return txtInserirTema;
    }
}
