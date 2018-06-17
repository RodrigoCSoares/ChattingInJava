package Servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Usuario implements Serializable {
    protected String nick;
    protected String nomeSala;
    private ObjectOutputStream transmissor;
    private ObjectInputStream receptor;

    public Usuario(String nick, String nomeSala){
        this.nick = nick;
        this.nomeSala = nomeSala;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNomeSala() {
        return nomeSala;
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    public ObjectOutputStream getTransmissor() {
        return transmissor;
    }

    public void setTransmissor(ObjectOutputStream transmissor) {
        this.transmissor = transmissor;
    }

    public ObjectInputStream getReceptor() {
        return receptor;
    }

    public void setReceptor(ObjectInputStream receptor) {
        this.receptor = receptor;
    }
}
