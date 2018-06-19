package Servidor.Pacote;

import Cliente.Usuario;

public class PacoteLogin implements Pacote
{
    private Usuario usuario;

    public PacoteLogin(Usuario usuario)
    {
        this.usuario = usuario;
    }

    @Override
    public Object getObject()
    {
        return this.usuario;
    }


}
