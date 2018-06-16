package Cliente;

import Servidor.DBO.Sala;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Usuario implements Serializable {
    protected String nick;
    protected String nomeSala;
    protected Sala sala;
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

    public Sala getSala() throws IOException, ClassNotFoundException {

        System.out.println("PASSOU");
        PacoteCliente pacoteCliente = new PacoteCliente();
        pacoteCliente.intencao = 1;
        transmissor.writeObject(pacoteCliente);
        sala = (Sala)receptor.readObject();
        System.out.println(sala.getNome());
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
