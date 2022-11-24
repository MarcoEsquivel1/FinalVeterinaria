package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;

    public Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/veterinaria?useUnicode=true&connectionCollation=utf8_spanish_ci","root","");
            System.out.println("Conexion exitosa");
        }catch(Exception e){
            System.out.println("Error de conexion");
        }
    }

    public Connection getConnection(){
        return con;
    }
}
