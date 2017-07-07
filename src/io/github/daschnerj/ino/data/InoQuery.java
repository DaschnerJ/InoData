/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.daschnerj.ino.data;

import static io.github.daschnerj.ino.data.InoData.l;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author justdasc
 */
public class InoQuery {
    
    private String query;
    private ArrayList<InoConnection> connections = new ArrayList<>();
    private ArrayList<ResultSet> results = new ArrayList<>();
    
    public InoQuery(String query, InoConnection connection)
    {
        this.query = query;
        this.connections.add(connection);
    }
    
    public InoQuery(String query, ArrayList<InoConnection> connections)
    {
        this.query = query;
        this.connections = connections;
    }
    
    public InoQuery(String query)
    {
        this.query = query;
    }
    
    public void clearConnections()
    {
        this.connections.clear();
    }
    
    public void addConnection(InoConnection connection)
    {
        this.connections.add(connection);
    }
    
    public void addConnection(ArrayList<InoConnection> connections)
    {
        connections.forEach((ic) -> {
            this.connections.add(ic);
        });
    }
    
    public void removeConnection(InoConnection connection)
    {
        this.connections.remove(connection);
    }
    
    public void removeConnections(ArrayList<InoConnection> connections)
    {
        connections.forEach((ic) -> {
            this.connections.add(ic);
        });
    }
    
    public boolean hasConnection()
    {
        return !this.connections.isEmpty();
    }
    
    public void setQuery(String query)
    {
        this.query = query;
    }
    
    public void runQuery()
    {
        if(hasConnection())
        {
            results.clear();
            connections.forEach((ic) -> {
                try {
                    results.add(ic.getConnection().createStatement().executeQuery(query));
                } catch (SQLException ex) {
                    results.add(null);
                    l.addSevere("The query failed to run on this connection and the connection has been skipped.");
                }
            });
        }
        else
        {
            l.addInfo("There was no connection to run the query on, process has stopped.");
        }
    }
    
    public void clearResults()
    {
        results.clear();
    }
    
    public boolean hasResult(InoConnection ic)
    {
        if(connections.contains(ic))
            if(results.size() >= connections.indexOf(ic))
                return results.get(connections.indexOf(ic)) != null;
        else
                return false;
        else
            return false;
                
    }
    
    public ResultSet getResult(InoConnection ic)
    {
        return results.get(connections.indexOf(ic));
    }
    
    public ArrayList<ResultSet> getResults()
    {
        return results;
    }
    
}
