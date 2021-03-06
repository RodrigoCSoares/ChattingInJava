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
            System.out.print("Getting rooms from DB... ");
            ArrayList<Sala> salas = BD.SALAS.getTodasSalas();
            System.out.println("OK");

            for(;;){
                System.out.print("Waiting for new clients... ");
                Socket conexao = serverSocket.accept();
                System.out.println("OK");
                new TratadorDeConexao(conexao, salas);
            }
        }
        catch (Exception error){
            System.err.println(error.getMessage());
            error.printStackTrace();
        }
    }
}
