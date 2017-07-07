/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.daschnerj.ino.data;

import static io.github.daschnerj.ino.data.InoData.l;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author justdasc
 */
public class InoConnection {
    
    private Connection connection;
    
    private String url = "jdbc:mysql://[host]:[port]/[dbname]";
    private String host;
    private Integer port = 3306;
    private String database = "";
    private String user = "";
    private String pass = "";
    
    public InoConnection(String host, String database, Integer port, String user)
    {
        newConnection(host, database, port, user, this.pass);
    }
    
    public InoConnection(String host, String database, String user)
    {
        newConnection(host, database, this.port, user, this.pass);
    }
    
    public InoConnection(String host, String database, Integer port)
    {
        newConnection(host, database, port, this.user, this.pass);
    }
    
    public InoConnection(String host, String database)
    {
        newConnection(host, database, this.port, this.user, this.pass);
    }
    
    public InoConnection(String host, String database, String user, String pass)
    {
         newConnection(host, database, 3306, user, pass);
    }
    
    public InoConnection(String host, String database, Integer port, String user, String pass)
    {
          newConnection(host, database, port, user, pass);
    }
    
    private void newConnection(String host, String database, Integer port, String user, String pass)
    {
        this.user = user;
        this.pass = pass;
        this.host = host;
        this.url = url.replaceAll("[host]", host);
        this.database = database;
        this.url = url.replaceAll("[dbname]", database);
        this.port = port;
        this.url = url.replaceAll("[port]", port.toString()); 
    }
    
    public Connection getConnection()
    {
        return connection;
    }
    
    public void createConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            this.connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            l.addSevere(ex.getMessage());
        }
    }
    
    public void closeConnection()
    {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            l.addSevere(ex.getMessage());
        }
    }
    
}
