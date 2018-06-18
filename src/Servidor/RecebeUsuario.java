package Servidor;

import Servidor.DBO.Sala;
import Cliente.Usuario;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class RecebeUsuario extends Thread {
    private Socket conexao;
    private ObjectInputStream receptor;
    private ObjectOutputStream transmissor;
    private ArrayList<Sala> salas;
    private Sala salaDoUsuario;

    RecebeUsuario(Socket conexao, ArrayList<Sala> salas) throws Exception{
        this.salas = salas;
        this.conexao = conexao;
        receptor = new ObjectInputStream(conexao.getInputStream());
        transmissor = new ObjectOutputStream(conexao.getOutputStream());
    }

    @Override
    public void start() {
        try {
            //1. Envia as salas
            transmissor.writeObject(salas);

            //2. Recebe o usuario
            Usuario usuario = (Usuario) receptor.readObject();

            //Insere o usuario na nomeSala
            for(int i=0; i<salas.size(); i++){
                if(salas.get(i).getNome().equals(usuario.getNomeSala())) {
                    salas.get(i).addUsuario(usuario);
                    salaDoUsuario = salas.get(i);
                }
            }

            //3. Envia a Sala para o usuario
            transmissor.writeObject(salaDoUsuario);




        }catch (Exception error){
            System.err.println("Erro ao tratar conexao!");
        }
    }
}
