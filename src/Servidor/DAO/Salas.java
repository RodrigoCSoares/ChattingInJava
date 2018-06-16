package Servidor.DAO;

import Servidor.BD;
import Servidor.DBO.Sala;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static Servidor.BD.CONNECTION;

public class Salas {
    public ArrayList<Sala> getTodasSalas() throws Exception{
        try{
            ArrayList<Sala> salas = new ArrayList<>();
            String query = "SELECT * FROM Salas";
            PreparedStatement preparedStatement = BD.CONNECTION.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String nome = resultSet.getString("Nome");
                int capacidade = resultSet.getInt("Capacidade");
                Sala sala = new Sala(nome, capacidade);
                salas.add(sala);
            }

            if(salas.isEmpty())
                throw new Exception("Salas nao cadastradas");


            return salas;
        }
        catch (Exception e){
            throw new Exception("Erro ao buscar salas");
        }
    }

    public Sala getSala (String nome)throws Exception{
        try{
            Sala sala;
            String query = "SELECT * FROM SALAS WHERE NOME = ?";
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.first())
                throw new Exception("Sala nao cadastrada");

            sala = new Sala(resultSet.getString("NOME"), resultSet.getInt("CAPACIDADE"));

            return sala;
        }
        catch (Exception erro){
            throw new Exception("Erro ao buscar sala por nome");
        }
    }

}
