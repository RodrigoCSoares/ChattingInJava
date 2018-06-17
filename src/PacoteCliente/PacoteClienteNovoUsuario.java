package PacoteCliente;

import java.io.Serializable;

public class PacoteClienteNovoUsuario implements PacoteCliente, Serializable
{
    private String usuario;

    public PacoteClienteNovoUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getUsurio()
    {
        return this.usuario;
    }


}
