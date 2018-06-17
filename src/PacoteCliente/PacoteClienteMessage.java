package PacoteCliente;

import java.io.Serializable;

public class PacoteClienteMessage implements PacoteCliente, Serializable
{
    private String message, recipient, sender;

    public PacoteClienteMessage(String message, String recipient, String sender) throws Exception
    {
        if (recipient.isEmpty() || sender.isEmpty())
            throw new Exception("Invalid recipient and/or sender");

        this.message = message;
        this.recipient = recipient;
        this.sender = sender;
    }

    public String getMessage()
    {
        return this.message;
    }

    public String getRecipient()
    {
        return recipient;
    }

    public String getSender()
    {
        return sender;
    }
}
