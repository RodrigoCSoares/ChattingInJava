package Servidor;

import Servidor.DBO.Sala;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    public static void main (String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(32123);
            System.out.println("Servidor funcionando...\nPorta: "+serverSocket.getLocalPort());

            //ACESSAR BD
            ArrayList<Sala> salas = BD.SALAS.getTodasSalas();

            for(;;){
                Socket conexao = serverSocket.accept();
                new TratadorDeConexao(conexao, salas).start();
            }
        }
        catch (Exception error){
            System.err.println(error.getMessage());
            error.printStackTrace();
        }
    }
}
