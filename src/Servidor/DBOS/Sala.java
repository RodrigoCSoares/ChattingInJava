package Servidor.DBOS;

import java.io.Serializable;

public class Sala implements Serializable {
    public int nMax;
    public String nome;

    public Sala(String nome, int nMax){
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
}
