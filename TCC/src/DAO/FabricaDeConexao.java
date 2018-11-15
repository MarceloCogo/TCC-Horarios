/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hp Pavilion
 */
public class FabricaDeConexao {

    public Connection con;
    public Statement stmt;
    public ResultSet rs;

    String url = "jdbc:mysql://localhost/tcc?autoReconnect=true&useSSL=false";
    String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String password = "123";

    public void openConection(){
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, password);            
            stmt = con.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(FabricaDeConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean atualizar (String comando) 
    {   
        try 
        {
            stmt.executeUpdate(comando); 
            return true;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public ResultSet consultar(String query) 
    {   
        try 
        {
            rs=stmt.executeQuery(query);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return rs;
    }
    
    public void close()  
    {
        try 
        {
            stmt.close();
        } 
        catch (SQLException sqlEx) 
        { 
            stmt = null;
        }
        try
        {
        	con.close(); 
        } 
        catch (SQLException sqlEx) 
        { 
        	con = null;
        }   
    }

    public static void main(String[] args) {
        FabricaDeConexao fab = new FabricaDeConexao();
        fab.openConection();
    }
}
