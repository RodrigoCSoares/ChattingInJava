package Servidor.Message;

public class MessageLogin implements Message
{
    private String userName;

    public MessageLogin(String userName)
    {
        this.userName = userName;
    }

    @Override
    public void sendMessage()
    {

    }


}
