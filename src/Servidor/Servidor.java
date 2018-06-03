package Servidor;

import Servidor.DBOS.Sala;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    public static void main (String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(32123);
            System.out.println("Servidor funcionando...\nPorta: "+serverSocket.getLocalPort());

            //ACESSAR BD
            ArrayList<Sala> salas = new ArrayList<>();
            salas.add(new Sala("Sala 1",5));
            salas.add(new Sala("Sala 2",5));
            salas.add(new Sala("Sala 3",5));

            for(;;){
                Socket conexao = serverSocket.accept();
                new TratadorDeConexao(conexao, salas).start();
            }
        }
        catch (Exception error){
            System.err.println("Erro ao inicializar servidor!");
        }
    }
}
