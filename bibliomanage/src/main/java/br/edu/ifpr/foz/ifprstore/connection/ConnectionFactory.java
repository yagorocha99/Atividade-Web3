package br.edu.ifpr.foz.ifprstore.connection;

import br.edu.ifpr.foz.ifprstore.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection connection;

    public static Connection getConnection(){

        String url = "jdbc:mysql://localhost/bibliomanage";
        String user = "yago";
        String pass = "123";

        try {

            Class.forName( "com.mysql.cj.jdbc.Driver" );
            connection = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void  closeConnection(){

//        try{
//            connection.close();
//        }catch (SQLException e){
//
//            throw new DatabaseException("Não foi possível encerrar a conexao: " + e.getMessage());
//
//        }

    }

}
