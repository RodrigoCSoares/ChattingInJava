package Cliente.Chat;

import Cliente.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerChat {
    private Usuario usuario;
    public ListView listViewUsuarios;

    public void setUser(Usuario usuario){
        this.usuario = usuario;
        System.out.println(this.usuario.getNick());
        this.gerenciaListViewUsuarios();
    }

   private void gerenciaListViewUsuarios(){
       Thread thread = new Thread(() -> {
           //TODO
            while (true){
                populaListViewUsuarios();
                System.out.println("thread on");
            }
       });
       thread.start();
   }

    private void populaListViewUsuarios(){
        try {
            ObservableList<String> nickUsuarios = FXCollections.observableArrayList();
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
