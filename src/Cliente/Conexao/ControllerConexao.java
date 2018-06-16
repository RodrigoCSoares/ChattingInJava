package Cliente.Conexao;

import Cliente.Chat.ControllerChat;
import Cliente.Usuario;
import Servidor.DBO.Sala;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerConexao implements Initializable {
    public Button btnConectar;
    public TextField txtNick;
    private Usuario usuario;
    public VBox vBox;
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

    public void conectar(ActionEvent event) throws Exception{
        usuario = new Usuario(txtNick.getText(), cbSalas.getValue());
        transmissor.writeObject(usuario);
        transmissor.flush();

        //Cria um fmlxLoader que carrega a proxima Scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Cliente/Chat/chat.fxml"));
        Parent borderPaneParent = fxmlLoader.load();

        //Passa os parametros necessarios para o controller da proxima Scene
        ControllerChat controller = fxmlLoader.getController();
        usuario.setReceptor(receptor);
        usuario.setTransmissor(transmissor);
        controller.setUser(usuario);

        Scene borderPaneScene = new Scene(borderPaneParent);

        //Mostra a proxima scene
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(borderPaneScene);
        window.show();
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
