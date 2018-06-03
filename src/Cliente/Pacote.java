package Cliente;

import java.io.Serializable;

public class Pacote implements Serializable {
    protected String nick;
    protected String sala;

    public Pacote(String nick, String sala){
        this.nick = nick;
        this.sala = sala;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
}
