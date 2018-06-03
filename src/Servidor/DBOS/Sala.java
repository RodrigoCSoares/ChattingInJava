package Servidor.DBOS;

import Cliente.Usuario;

import java.io.Serializable;
import java.util.ArrayList;

public class Sala implements Serializable {
    public int nMax;
    public String nome;
    public ArrayList<Usuario> usuarios;

    public Sala(String nome, int nMax){
        usuarios = new ArrayList<>();
        this.nome = nome;
        this.nMax = nMax;
    }

    public int getnMax() {
        return nMax;
    }

    public void setnMax(int nMax) {
        this.nMax = nMax;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public synchronized void addUsuario (Usuario usuario){
        this.usuarios.add(usuario);
        System.out.println("\nUsuario: '"+usuario.getNick()+" 'inserido na nomeSala: '"+this.getNome()+"'");
    }

    public ArrayList<Usuario> getUsuarios(){
        System.out.println(usuarios.get(0).getNick());
        return  this.usuarios;
    }
}
