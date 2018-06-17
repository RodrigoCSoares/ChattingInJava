package Servidor;

import PacoteCliente.PacoteCliente;
import Servidor.DBO.Sala;
import Cliente.Usuario;
import PacoteCliente.PacoteClienteSalas;
import PacoteCliente.PacoteClienteNovoUsuario;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class TratadorDeConexao implements Runnable
{
    private Socket conexao;
    private ObjectInputStream receptor;
    private ObjectOutputStream transmissor;
    private ArrayList<Sala> salas;
    private Sala salaDoUsuario;
    private Usuario usuario;
    private PacoteCliente pacoteCliente;

    TratadorDeConexao(Socket conexao, ArrayList<Sala> salas) throws Exception
    {
        this.salas = salas;
        this.conexao = conexao;
        receptor = new ObjectInputStream(conexao.getInputStream());
        transmissor = new ObjectOutputStream(conexao.getOutputStream());
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        try
        {
            //Envia as salas
            System.out.print("Sending rooms to client...");
//            transmissor.writeObject(salas);
            pacoteCliente = new PacoteClienteSalas(salas);
            transmissor.writeObject(pacoteCliente);
            transmissor.flush();
            System.out.println("OK");

            //Recebe o usuario
            usuario = (Usuario) receptor.readObject();
            usuario.setReceptor(receptor);
            usuario.setTransmissor(transmissor);
            System.out.println("User " + usuario.getNick() + " entered into room " + usuario.getNomeSala());

            //Insere o usuario na nomeSala
            for (int i = 0; i < salas.size(); i++)
            {
                if (salas.get(i).getNome().equals(usuario.getNomeSala()))
                {
                    salas.get(i).addUsuario(usuario);
                    salaDoUsuario = salas.get(i);
                    break;
                }
            }

            printUsersFromRoom(usuario.getNomeSala());

            // TODO: update the user list all users in the current room
            System.out.print("update the user list...");
            pacoteCliente = new PacoteClienteNovoUsuario(usuario.getNick());
            for (int i = 0; i < salaDoUsuario.usuarios.size(); i++)
            {
                try
                {
                    salaDoUsuario.usuarios.get(i).getTransmissor().writeObject(pacoteCliente);
                    salaDoUsuario.usuarios.get(i).getTransmissor().flush();
                }
                catch (Exception e)
                {
                    System.err.println("Error i = " + i);
                    e.printStackTrace();
                }

            }
            System.out.println("OK");


            System.out.println("Finish placing the user " + usuario.getNick() + " into room " + usuario.getNomeSala());
            receptor.close();
            transmissor.close();
            // listening for in/out messages of this user
//            for (; ; )
//            {
//                System.out.println("Waiting for message");
//                pacoteCliente = (PacoteClienteMessage) receptor.readObject();
//                System.out.println("Message received: " + ((PacoteClienteMessage) pacoteCliente).getMessage());
//            }


        }
        catch (EOFException userExit)
        {
            System.err.println("User " + usuario.getNick() + " logout");
            removeUser(usuario);
        }
        catch (Exception error)
        {
            System.err.println("Erro ao tratar conexao!");
            error.printStackTrace();
        }
    }

    private void printUsersFromRoom(String roomName)
    {
        System.out.println("Users at room " + salaDoUsuario.getNome());
        for (int i = 0; i < salaDoUsuario.usuarios.size(); i++)
        {
            System.out.println("\t" + salaDoUsuario.usuarios.get(i).getNick());
        }
    }

    private void removeUser(Usuario usuario)
    {
        // TODO: update the user list all users in the current room


        String salaString = usuario.getNomeSala();
        String nomeString = usuario.getNick();
        int salaId, nomeId;
        for (int i = 0; i < salas.size(); i++)
        {
            if (salas.get(i).getNome().equals(salaString))
            {
                salaId = i;
                for (int j = 0; j < salas.get(salaId).usuarios.size(); j++)
                {
                    if (salas.get(salaId).usuarios.get(j).getNick().equals(nomeString))
                    {
                        salas.get(salaId).usuarios.remove(j);
                        break;
                    }
                }
                break;
            }
        }

        System.out.println("Should remove " + nomeString + " from room " + salaString);


        printUsersFromRoom(salaString);
    }
}
