package Servidor;

import Servidor.DAO.Salas;

import java.sql.Connection;
import java.sql.DriverManager;

public class BD {
    public static final Connection CONNECTION;
    public static final Salas SALAS;

    static {
        Connection connection = null;
        Salas salas = null;

        try{
            connection = DriverManager.getConnection("jdbc:sqlserver://poo.database.windows.net:1433;database=salas;user=gusbru@poo;password=d5uv6DoT.;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
            salas = new Salas();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        SALAS = salas;
        CONNECTION = connection;
    }

}
