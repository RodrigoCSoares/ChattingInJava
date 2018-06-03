package Servidor;

import Servidor.DBOS.Sala;
import Cliente.Pacote;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class TratadorDeConexao extends Thread {
    private Socket conexao;
    private ObjectInputStream receptor;
    private ObjectOutputStream transmissor;
    private ArrayList<Sala> salas;

    TratadorDeConexao(Socket conexao, ArrayList<Sala> salas) throws Exception{
        this.salas = salas;
        this.conexao = conexao;
        receptor = new ObjectInputStream(conexao.getInputStream());
        transmissor = new ObjectOutputStream(conexao.getOutputStream());
    }

    @Override
    public void start() {
        try {
            transmissor.writeObject(salas);
            Pacote pacote = (Pacote) receptor.readObject();
            System.out.println("Nick escolhido: " + pacote.getNick() +"\nSala escolhida: "+pacote.getSala());

        }catch (Exception error){
            System.err.println("Erro ao tratar conexao!");
        }
    }
}
