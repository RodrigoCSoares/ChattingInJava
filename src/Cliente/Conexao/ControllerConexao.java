package Cliente.Conexao;

import Cliente.Pacote;
import Servidor.DBOS.Sala;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerConexao implements Initializable {
    public Button btnConectar;
    public TextField txtNick;
    public ComboBox<String> cbSalas;
    private ObjectOutputStream transmissor;
    private ObjectInputStream receptor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Socket conexao = new Socket("127.0.0.1", 32123 );
            transmissor = new ObjectOutputStream(conexao.getOutputStream());
            receptor = new ObjectInputStream(conexao.getInputStream());
            btnConectar.setDisable(true);
            this.populaComboBox();
        }
        catch (Exception e){
            txtNick.setDisable(true);
            cbSalas.setDisable(true);
            btnConectar.setDisable(true);
        }

    }

    public void conectar() throws Exception{
        Pacote pacote = new Pacote(txtNick.getText(), cbSalas.getValue());
        transmissor.writeObject(pacote);
        transmissor.flush();

    }

    private void populaComboBox() throws Exception{
        ArrayList<Sala> salas = (ArrayList<Sala>) receptor.readObject();
        for(int i=0; i<salas.size(); i++)
            cbSalas.getItems().add(salas.get(i).getNome());

    }

    public void terminouDeDigitar(){
        try {
            String promptText = cbSalas.getPromptText();
            if (txtNick.getText().equals("") || cbSalas.getValue().equals(promptText))
                btnConectar.setDisable(true);
            else
                btnConectar.setDisable(false);
        }catch (Exception error){}
    }
}
