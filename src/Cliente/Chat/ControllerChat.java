package Cliente.Chat;

import PacoteCliente.PacoteCliente;
import PacoteCliente.PacoteClienteNovoUsuario;
import Cliente.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerChat implements Initializable
{
    @FXML
    private ListView<String> listViewUsuarios, listViewMessages;
    @FXML
    private TextField txtMessage;
    @FXML
    private Button btnSendMessage;

    private Usuario usuario;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        System.out.println("Chat view initialized :)");
        btnSendMessage.setOnAction(event -> sendMessage());

        // listener read message
        receiveMessages();
    }

    public void setUser(Usuario usuario){
        this.usuario = usuario;
        System.out.println(this.usuario.getNick());
        this.gerenciaListViewUsuarios();
    }

    private void sendMessage()
    {
        String message = txtMessage.getText();
        txtMessage.clear();
        listViewMessages.getItems().add(usuario.getNick() + ": " + message);
        try
        {
            System.out.print("Creating message...");
//            PacoteCliente pacoteCliente = new PacoteClienteMessage(message);
            System.out.println("OK");
            System.out.print("Writing object...");
//            usuario.getTransmissor().writeObject(pacoteCliente);
            usuario.getTransmissor().flush();
            System.out.println("OK");
        }
        catch (Exception e)
        {
            System.err.println("Error sending message to server");
            e.printStackTrace();
        }

    }

   private void gerenciaListViewUsuarios(){
//       Thread thread = new Thread(() -> {
//           //TODO
//            while (true){
//                populaListViewUsuarios();
//                System.out.println("thread on");
//            }
//       });
//       thread.start();
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


    private void receiveMessages()
    {
        Thread readMessages = new Thread(() -> {
            for(;;)
            {
                try
                {
                    PacoteCliente pacoteCliente = (PacoteCliente) usuario.getReceptor().readObject();
                    System.out.println("MSG recebida (ControllerChat)");
                    if (pacoteCliente instanceof PacoteClienteNovoUsuario)
                    {
                        String newUser = ((PacoteClienteNovoUsuario) pacoteCliente).getUsurio();
                        System.out.println(newUser);
                        listViewUsuarios.getItems().setAll(newUser);
                    }

                }
                catch (Exception e)
                {
                    System.err.println("this message was not about new clients");
                }
            }
        });

        readMessages.start();
    }

}
