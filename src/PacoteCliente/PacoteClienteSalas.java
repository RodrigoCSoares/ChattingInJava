package PacoteCliente;

import Servidor.DBO.Sala;

import java.io.Serializable;
import java.util.ArrayList;

public class PacoteClienteSalas implements PacoteCliente, Serializable
{
    private ArrayList<Sala> salas;

    public PacoteClienteSalas(ArrayList<Sala> salas)
    {
        this.salas = salas;
    }

    public ArrayList<Sala> getSalas()
    {
        return salas;
    }

}
