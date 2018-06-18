package Cliente.Chat;

import Cliente.Usuario;
import Servidor.DBO.Sala;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerChat {
    private Usuario usuario;
    private ObjectOutputStream transmissor;
    private ObjectInputStream receptor;
    public ListView listViewUsuarios;

    public void setUser(Usuario usuario){
        this.usuario = usuario;
        this.transmissor = usuario.getTransmissor();
        this.receptor = usuario.getReceptor();

        System.out.println(this.usuario.getNick());
        this.populaListViewUsuarios();
    }

    private void populaListViewUsuarios(){
        try {
            ObservableList<String> nickUsuarios = FXCollections.observableArrayList();

            //3. recebe o objeto Sala do servidor
            usuario.setSala((Sala)receptor.readObject());

            ArrayList<Usuario> usuarios = usuario.getSala().getUsuarios();

            for(int i=0; i<usuarios.size(); i++){
                nickUsuarios.add(usuarios.get(i).getNick());
            }

            listViewUsuarios.setItems(nickUsuarios);
        }
        catch (Exception e){
            System.out.println("Falha ao encontrar os usuarios da sala");
        }
    }
}
